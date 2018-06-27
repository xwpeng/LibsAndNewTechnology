package com.android.xwpeng.tbug;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Looper;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

import static io.reactivex.BackpressureStrategy.DROP;

/**
 * 当程序发生Uncaught异常的时候,由该类来接管程序,并记录发送错误报告.
 * 需要在Application中注册，为了要在程序启动器就监控整个程序。
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {
    //TAG
    public static final String TAG = "CrashHandler";
    //自定义Toast
    private static Toast mCustomToast;
    //提示文字
    private static String mCrashTip = "很抱歉,程序出现异常,即将退出.";
    //系统默认的UncaughtException处理类
    private Thread.UncaughtExceptionHandler mDefaultHandler;
    //CrashHandler实例
    private static CrashHandler mCrashHandler;
    //程序的App对象
    public Application mApplication;
    //生命周期监听
    MyActivityLifecycleCallbacks mMyActivityLifecycleCallbacks = new MyActivityLifecycleCallbacks();
    //用来存储设备信息和异常信息
    private Map<String, String> infos = new HashMap();
    //用于格式化日期,作为日志文件名的一部分
    private DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
    //是否是Debug模式
    private boolean mIsDebug;
    //是否重启APP
    private boolean mIsRestartApp;
    //重启APP时间
    private long mRestartTime;
    //重启后的第一个Activity class文件
    private Class mClassOfFirstActivity;
    //是否已经toast
    private boolean hasToast;

    /**
     * 私有构造函数
     */
    private CrashHandler() {

    }

    /**
     * 获取CrashHandler实例 ,单例模式
     *
     * @return
     * @since V1.0
     */
    public static CrashHandler getInstance() {
        if (mCrashHandler == null)
            mCrashHandler = new CrashHandler();
        return mCrashHandler;
    }

    public static void setCloseAnimation(int closeAnimation) {
        MyActivityLifecycleCallbacks.sAnimationId = closeAnimation;
    }

    public static void setCustomToast(Toast customToast) {
        mCustomToast = customToast;
    }

    public static void setCrashTip(String crashTip) {
        mCrashTip = crashTip;
    }

    public void init(Application application, boolean isDebug, boolean isRestartApp, long restartTime, Class classOfFirstActivity) {
        mIsRestartApp = isRestartApp;
        mRestartTime = restartTime;
        mClassOfFirstActivity = classOfFirstActivity;
        initCrashHandler(application, isDebug);
    }

    public void init(Application application, boolean isDebug) {
        initCrashHandler(application, isDebug);
    }

    /**
     * 初始化
     *
     * @since V1.0
     */
    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    private void initCrashHandler(Application application, boolean isDebug) {
        mIsDebug = isDebug;
        mApplication = application;
        mApplication.registerActivityLifecycleCallbacks(mMyActivityLifecycleCallbacks);
        // 设置该CrashHandler为程序的默认处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
        // 获取系统默认的UncaughtException处理器
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();

    }

    /**
     * 当UncaughtException发生时会转入该函数来处理
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        boolean isHandle = handleException(ex);
        if (!isHandle && mDefaultHandler != null) {
            // 如果我们没有处理则让系统默认的异常处理器来处理
            mDefaultHandler.uncaughtException(thread, ex);
        } else {
            try {
                //给Toast留出时间
                Thread.sleep(2800);
            } catch (InterruptedException e) {
                Log.e(TAG, "uncaughtException() InterruptedException:" + e);
            }

            if (mIsRestartApp) {
                //利用系统时钟进行重启任务
                AlarmManager mgr = (AlarmManager) mApplication.getSystemService(Context.ALARM_SERVICE);
                try {
                    Intent intent = new Intent(mApplication, mClassOfFirstActivity);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    PendingIntent restartIntent = PendingIntent.getActivity(mApplication, 0, intent, PendingIntent.FLAG_ONE_SHOT);
                    mgr.set(AlarmManager.RTC, System.currentTimeMillis() + mRestartTime, restartIntent); // x秒钟后重启应用
                } catch (Exception e) {
                    Log.e(TAG, "first class error:" + e);
                }
            }

            mMyActivityLifecycleCallbacks.removeAllActivities();
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
            System.gc();

        }
    }

    /**
     * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成.
     *
     * @param ex
     * @return true:如果处理了该异常信息;否则返回false.
     */
    private boolean handleException(Throwable ex) {
        if (!hasToast) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Looper.prepare();
                        Toast toast;
                        if (mCustomToast == null) {
                            toast = Toast.makeText(mApplication, mCrashTip, Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.CENTER, 0, 0);
                        } else {
                            toast = mCustomToast;
                        }
                        toast.show();
                        Looper.loop();
                        hasToast = true;
                    } catch (Exception e) {
                        Log.e(TAG, "handleException Toast error" + e);
                    }

                }
            }).start();
        }
        if (ex == null) return false;
        // 收集设备参数信息
        collectDeviceInfo();
        // 保存日志文件
        saveCatchInfo2File(ex);
        return true;
    }

    /**
     * 收集设备参数信息
     */
    public void collectDeviceInfo() {
        try {
            PackageManager pm = mApplication.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(mApplication.getPackageName(), PackageManager.GET_ACTIVITIES);
            if (pi != null) {
                String versionName = pi.versionName == null ? "null" : pi.versionName;
                String versionCode = pi.versionCode + "";
                infos.put("versionName", versionName);
                infos.put("versionCode", versionCode);
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "collectDeviceInfo() an error occured when collect package info NameNotFoundException:");
        }
        Field[] fields = Build.class.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                infos.put(field.getName(), field.get(null).toString());
                Log.i(TAG, field.getName() + " : " + field.get(null));
            } catch (Exception e) {
                Log.e(TAG, "collectDeviceInfo() an error occured when collect crash info Exception:");
            }
        }
    }

    /**
     * 保存错误信息到文件中
     */
    private void saveCatchInfo2File(Throwable ex) {
        StringBuffer sb = new StringBuffer();
        sb.append("------------------------start------------------------------\n");
        long timestamp = System.currentTimeMillis();
        String time = formatter.format(new Date());
        time = "time:" + time + ",timestamp:" + timestamp + "\n";
        sb.append(time);
        for (Map.Entry<String, String> entry : infos.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            sb.append(key + "=" + value + "\n");
        }
        sb.append(getCrashInfo(ex));
        sb.append("\n------------------------end------------------------------\n");
        saveFile(sb.toString(), mApplication.getFilesDir() + File.separator + "crash.txt");
        saveFile(sb.toString(), mApplication.getFilesDir() + File.separator + "waiting_upload_crash.txt");
        Intent intent  = new Intent(mApplication, UploadCrashService.class);
        intent.putExtra("path", mApplication.getFilesDir() + File.separator + "waiting_upload_crash.txt");
        mApplication.startService(intent);
    }



    private void saveFile(String content, String path) {
        FileOutputStream fos = null;
        try {
            File file = new File(path);
            if (!file.exists()) file.createNewFile();
            fos = new FileOutputStream(file, true);
            fos.write(content.getBytes());
//                // log日志到控制台
//                if (mIsDebug) LogcatCrashInfo(path);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "saveCatchInfo2File() an error occured while writing file... Exception:");
        } finally {
            if (fos != null) try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void LogcatCrashInfo(String fileName) {
        if (!new File(fileName).exists()) {
            Log.e(TAG, "LogcatCrashInfo() 日志文件不存在");
            return;
        }
        FileInputStream fis = null;
        BufferedReader reader = null;
        String s = null;
        try {
            fis = new FileInputStream(fileName);
            reader = new BufferedReader(new InputStreamReader(fis, "GBK"));
            while (true) {
                s = reader.readLine();
                if (s == null) break;
                Log.e(TAG, s);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally { // 关闭流
            try {
                reader.close();
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 得到程序崩溃的详细信息
     */
    public String getCrashInfo(Throwable ex) {
        Writer result = new StringWriter();
        PrintWriter printWriter = new PrintWriter(result);
        ex.setStackTrace(ex.getStackTrace());
        ex.printStackTrace(printWriter);
        printWriter.close();
        return result.toString();
    }

}
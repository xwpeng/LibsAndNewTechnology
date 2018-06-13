package com.android.xwpeng.tsharesdk;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.share).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShare();
            }
        });
        findViewById(R.id.loginQQ).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLoginQQ();
            }
        });
        findViewById(R.id.loginWechat).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLoginWechat();
            }
        });
    }

    private void showShare() {
        OnekeyShare oks = new OnekeyShare();
        //需要完全自定义UI设置此属性
        oks.setPlatform(Wechat.NAME);
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        oks.setTitle("aa");
        oks.setTitleUrl("https://www.baidu.com");
        oks.setText("我是分享文本");
        //must getExternalCacheDir, if sdCard unvailable,share qq disable
        try {
            File iconFile = new File(getApplicationContext().getExternalCacheDir().getAbsolutePath(), "share_icon");
            Drawable drawable = getDrawable(R.mipmap.ic_launcher_round);
            saveBitmapToFile(iconFile, drawableToBitmap(drawable));
            oks.setImagePath(iconFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        oks.show(this);
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        // 取 drawable 的长宽
        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();
        // 取 drawable 的颜色格式
        Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                : Bitmap.Config.RGB_565;
        // 建立对应 bitmap
        Bitmap bitmap = Bitmap.createBitmap(w, h, config);
        // 建立对应 bitmap 的画布
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, w, h);
        // 把 drawable 内容画到画布中
        drawable.draw(canvas);
        return bitmap;
    }


    public static void saveBitmapToFile(File file, Bitmap bm) throws IOException {
        if (bm == null || !IsCanUseSdCard()) return;
        if (!file.exists()) file.createNewFile();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] data = stream.toByteArray();
        try {
            saveByteToFile(file, data);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            stream.close();
        }
    }

    //sdcard是否可读写
    public static boolean IsCanUseSdCard() {
        try {
            return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 把byte[]数据存入文件
     */
    public static void saveByteToFile(File file, byte[] data)
            throws IOException {
        FileOutputStream outStream = null;
        try {
            outStream = new FileOutputStream(file);
            outStream.write(data);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (outStream != null) outStream.close();
        }
    }


    private void showLoginQQ() {
        Platform plat = ShareSDK.getPlatform(QQ.NAME);
        plat.removeAccount(true);
        if (plat.isAuthValid()) {
//判断是否已经存在授权状态，可以根据自己的登录逻辑设置
            Toast.makeText(this, "已经授权过了", 0).show();
            return;
        }
        plat.showUser(null);
        plat.setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                hashMap.isEmpty();
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {

            }

            @Override
            public void onCancel(Platform platform, int i) {

            }
        });
    }

    private void showLoginWechat() {
        Platform plat = ShareSDK.getPlatform(Wechat.NAME);
        plat.removeAccount(true);
        if (plat.isAuthValid()) {
//判断是否已经存在授权状态，可以根据自己的登录逻辑设置
            Toast.makeText(this, "已经授权过了", 0).show();
            return;
        }
        plat.showUser(null);
        plat.setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                hashMap.isEmpty();
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {

            }

            @Override
            public void onCancel(Platform platform, int i) {

            }
        });
    }


}

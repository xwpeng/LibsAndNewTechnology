package com.android.xwpeng.tbug;

import android.Manifest;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.tbruyelle.rxpermissions2.RxPermissions;
import com.tencent.bugly.crashreport.CrashReport;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new RxPermissions(this).requestEachCombined(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE).subscribe();
        findViewById(R.id.java_crash).setOnClickListener(this);
        findViewById(R.id.native_crash).setOnClickListener(this);
        findViewById(R.id.anr).setOnClickListener(this);
        findViewById(R.id.anr2).setOnClickListener(this);
        findViewById(R.id.read_crash_log).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.java_crash:
                throw new RuntimeException("oh, break");
            case R.id.native_crash:
                CrashReport.testNativeCrash();
                break;
            case R.id.anr:
                try {
                    Thread.sleep(30000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.anr2:
                Toast.makeText(this, "anr ...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.read_crash_log:
                readCrashLog(getApplication().getFilesDir() + File.separator + "crash.txt");
                break;
        }
    }

    private void readCrashLog(String path) {
        int i = 0;
        StringBuffer sb = new StringBuffer();
        File file = new File(path);
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
                i++;
                if (i >= 200) break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ((TextView) findViewById(R.id.crash_log_tv)).setText(sb.toString());
    }
}

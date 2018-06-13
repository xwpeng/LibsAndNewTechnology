package com.android.xwpeng.trxpermissions;

import android.Manifest;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.tbruyelle.rxpermissions2.RxPermissions;

import static com.android.xwpeng.trxpermissions.IntentUtils.gotoPermissionSetting;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.single).setOnClickListener(v -> t4());
    }

    private void t1() {
        //成功了就不会再询问了
        new RxPermissions(this).request(Manifest.permission.CAMERA)
                .subscribe(t -> Toast.makeText(getApplicationContext(), "权限" + (t ? "成功" : "失败"), Toast.LENGTH_SHORT).show());
    }

    private void t2() {
        //成功了其中一个就不会再询问了
        new RxPermissions(this).request(Manifest.permission.CAMERA, Manifest.permission.SEND_SMS)
                .subscribe(t -> Toast.makeText(getApplicationContext(), "权限" + (t ? "成功" : "失败"), Toast.LENGTH_SHORT).show());
    }

    private void t3() {
        //成功了其中一个就不会再询问了
        new RxPermissions(this).requestEach(Manifest.permission.CAMERA, Manifest.permission.SEND_SMS)
                .subscribe(permission -> {
                    switch (permission.name) {
                        case Manifest.permission.CAMERA:
                            Toast.makeText(getApplicationContext(), "相机权限" + (permission.granted ? "成功" : "失败"), Toast.LENGTH_SHORT).show();
                            if (!permission.shouldShowRequestPermissionRationale)
                                Toast.makeText(getApplicationContext(), "相机不再询问", Toast.LENGTH_SHORT).show();
                            break;
                        case Manifest.permission.SEND_SMS:
                            Toast.makeText(getApplicationContext(), "短信权限" + (permission.granted ? "成功" : "失败"), Toast.LENGTH_SHORT).show();
                            if (!permission.shouldShowRequestPermissionRationale)
                                Toast.makeText(getApplicationContext(), "短信不再询问", Toast.LENGTH_SHORT).show();
                            break;
                    }
                });
    }

    private void t4() {
        new RxPermissions(this).requestEachCombined(Manifest.permission.CAMERA, Manifest.permission.SEND_SMS)
                .subscribe(permission -> {
                    if (permission.granted)
                        Toast.makeText(getApplicationContext(), "权限成功", Toast.LENGTH_SHORT).show();
                    else if (permission.shouldShowRequestPermissionRationale) {
                        Toast.makeText(getApplicationContext(), "权限失败", Toast.LENGTH_SHORT).show();
                        //弹框询问，重复此流程
                    } else {
                        //全部不再询问才会走这里，不再询问是要拒绝过一次才会出现
                        Toast.makeText(getApplicationContext(), "请开启必要权限，去权限设置页", Toast.LENGTH_SHORT).show();
                        gotoPermissionSetting(MainActivity.this);
                    }
                });
    }


    private void t5() {
//        new RxPermissions(this).shouldShowRequestPermissionRationale()
    }
}

package com.android.xwpeng.tsticky;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapp.MyEventBusIndex;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    private TextView mSendStickyBtn1,mSendStickyBtn2,mSendStickyBtn3,mRegisterBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //加快register的速度
        EventBus.builder().addIndex(new MyEventBusIndex()).installDefaultEventBus();
        mSendStickyBtn1 = findViewById(R.id.send_sticky_message1);
        mSendStickyBtn2 = findViewById(R.id.send_sticky_message2);
        mSendStickyBtn3 = findViewById(R.id.send_sticky_message3);
        mRegisterBtn = findViewById(R.id.register_event_bus);
        mSendStickyBtn1.setOnClickListener(this);
        mSendStickyBtn2.setOnClickListener(this);
        mSendStickyBtn3.setOnClickListener(this);
        mRegisterBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.send_sticky_message1:
                EventBus.getDefault().postSticky("粘性事件1");
                Log.d(TAG,"发送粘性事件1...");
                break;
            case R.id.send_sticky_message2:
                EventBus.getDefault().postSticky("粘性事件2");
                Log.d(TAG, "发送粘性事件2...");
                break;
            case R.id.send_sticky_message3:
                EventBus.getDefault().postSticky("粘性事件3");
                Log.d(TAG, "发送粘性事件3...");
                break;
            case R.id.register_event_bus:
                EventBus.getDefault().register(this);
                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void accpte(String message) {
        Log.d(TAG, "accpte: " + message);
    }
}

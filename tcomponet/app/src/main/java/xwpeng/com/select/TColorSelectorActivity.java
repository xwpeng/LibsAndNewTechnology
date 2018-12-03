package xwpeng.com.select;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import xwpeng.com.tcomponent.R;

/**
 * Created by xwpeng on 2018/12/3.
 */
public class TColorSelectorActivity extends AppCompatActivity {
    private TextView mLoginTv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t_color_selector);
        mLoginTv = findViewById(R.id.login_tv);
        mLoginTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        findViewById(R.id.change_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLoginTv.setEnabled(!mLoginTv.isEnabled());
            }
        });
    }
}

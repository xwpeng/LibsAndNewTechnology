package xwpeng.com.tandroiddagger.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.TextView;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import xwpeng.com.tandroiddagger.BaseActivity;
import xwpeng.com.tandroiddagger.R;
import xwpeng.com.tandroiddagger.data.Student;

public class MainActivity extends BaseActivity implements HasSupportFragmentInjector {
    @Inject
    Context mContext;
    @Inject
    Student mStudent;

    private TextView mContentTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContentTv = findViewById(R.id.tv_content);
        mContentTv.setText(mContext.toString() + "\n"
         + mStudent.toString() + "\n"
        );
        FristFragment fristFragment = new FristFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.framelayout_fragment, fristFragment)
        .addToBackStack(null)
        .commit();
    }

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentInjector;

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentInjector;
    }
}

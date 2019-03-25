package xwpeng.com.tlazy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import javax.inject.Inject;
import javax.inject.Provider;

import dagger.Lazy;

public class MainActivity extends AppCompatActivity {

    @Inject
    public Lazy<Pot> mLazyPot;
//    @Inject
//    public Provider<Pot> mProviderPot;
//    @Inject
//    public Pot mPot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DaggerMainActivityComponent.create().inject(this);
        Log.e("xwpeng16", mLazyPot.get().hashCode() + "");
        Log.e("xwpeng16", mLazyPot.get().hashCode() + "");
//        Log.e("xwpeng16", mProviderPot.get().hashCode() + "");
//        Log.e("xwpeng16", mProviderPot.get().hashCode() + "");
    }
}

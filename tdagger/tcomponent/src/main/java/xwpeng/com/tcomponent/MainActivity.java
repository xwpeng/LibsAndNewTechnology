package xwpeng.com.tcomponent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import javax.inject.Inject;

import xwpeng.com.tcomponent.bean.Pot;

/**
 * Created by xwpeng on 2019/1/16.
 */
public class MainActivity extends AppCompatActivity {
    @Inject
    Pot pot;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_component_main);
        DaggerMainActivityComponent.builder()
                .potComponent(
                        DaggerPotComponent.builder()
                                .flowerComponent(DaggerFlowerComponent.create())
                                .build()
                )
                .build()
                .inject(this);
        Toast.makeText(this, pot.show(), Toast.LENGTH_SHORT).show();
    }
}

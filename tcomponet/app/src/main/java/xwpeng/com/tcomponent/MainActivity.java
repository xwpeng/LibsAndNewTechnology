package xwpeng.com.tcomponent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {
    @Inject
    Pot pot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        DaggerMainActivityComponent.builder()
//                .build().inject(this);
//        DaggerMainActivityComponent.builder()
//                .potComponent(DaggerPotComponent.builder()
//                        .flowerComponent(DaggerFlowerComponent.create())
//                        .build())
//                .build().inject(this);
        DaggerFlowerComponent.create().plus(new PotModule()).plus().inject(this);
        String show = pot.show();
        Toast.makeText(MainActivity.this, show, Toast.LENGTH_SHORT).show();
    }
}

package xwpeng.com.tcomponent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import javax.inject.Inject;

public class TComponetActivity extends AppCompatActivity {
//    @Inject
//    Pot pot;
    @Inject
    String content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tcomponent);
//        DaggerMainActivityComponent.builder()
//                .build().inject(this);
//        DaggerMainActivityComponent.builder()
//                .potComponent(DaggerPotComponent.builder()
//                        .flowerComponent(DaggerFlowerComponent.create())
//                        .build())
//                .build().inject(this);
//        DaggerFlowerComponent.create().getSub().getSub().inject(this);
//        String show = pot.show();
        DaggerAppComponent.create().getSub().inject(this);
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
    }
}

package xwpeng.com.tdragger;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.LinkedHashMap;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {
    @Inject
    Student mStudent;
    TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTv = findViewById(R.id.tv);
        DaggerStudentComponent.create().inject(this);
        getStudentInfo();
        LinkedHashMap<String, String> a = new LinkedHashMap<>();
    }

    public void getStudentInfo() {
        mTv.setText(mStudent.toString());
    }
}

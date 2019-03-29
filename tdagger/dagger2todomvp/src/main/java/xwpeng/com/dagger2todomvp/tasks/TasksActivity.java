package xwpeng.com.dagger2todomvp.tasks;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import javax.inject.Inject;

import xwpeng.com.dagger2todomvp.App;
import xwpeng.com.dagger2todomvp.R;


public class TasksActivity extends AppCompatActivity{
    @Inject TasksPresenter tasksPresenter;
    @Inject Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TasksFragment tasksFragment = new TasksFragment();
        ((App)getApplication())
                .getAppComponent()
                .tasksActivityComponent()
                .view(tasksFragment)
                .build()
                .inject(this);
    }

    @Inject
    public void aa(TasksContract.View view){
        Log.e("xwpeng16", view.hashCode() + "");
        view.setPresenter(tasksPresenter);
    }

}

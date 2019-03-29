package xwpeng.com.tandroiddagger.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import javax.inject.Inject;

import dagger.android.DaggerActivity;
import dagger.android.DaggerApplication;
import dagger.android.support.AndroidSupportInjection;
import xwpeng.com.tandroiddagger.R;
import xwpeng.com.tandroiddagger.data.Student;

public class FristFragment extends Fragment {
    @Inject Context mContext;
    @Inject Student mStudent;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView textView = view.findViewById(R.id.tv_content);
        textView.setText(mContext.toString()  + "\n"
         + mStudent.toString() + "\n");
    }

    @Override
    public void onAttach(Context context) {
        Log.e("xwpeng16", "onAttach");
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.e("xwpeng16", "onCreate");
        super.onCreate(savedInstanceState);
    }

}

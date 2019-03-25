package xwpeng.com.tlazy;

import android.util.Log;

import javax.inject.Inject;

public class Pot {

    @Inject
    public Pot(){
        Log.e("xwpeng16", "pot init");
    }

    public String show(){
        return "lily";
    }
}

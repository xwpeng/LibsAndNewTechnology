package com.example.aac.databinding;

import android.util.Log;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

public class SUtil {
    public static String up(String str){
        return str.toUpperCase();
    }

    @BindingAdapter({"url"})
    public static void loadImage(ImageView iv, String url){
        Log.e("xwpeng16", "load url: " + url + " to iv");
    }
}

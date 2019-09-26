package com.xwpeng.tkotlin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        aa(new AA<String>());
    }


    public static void aa (AA<? extends Object> a) {

    }

     public class AA<T>{

    }
    interface A {
        int get();
    }

    interface B {
        String get(int a);
    }

    class  C implements A , B{


        @Override
        public int get() {
            return 0;
        }

        @Override
        public String get(int a) {
            return null;
        }

    }




}

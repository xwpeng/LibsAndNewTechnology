package com.android.lambda;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("Zhang", "San", 19));
        personList.add(new Person("Li", "Si", 19));
        personList.add(new Person("Wang", "Wu", 19));
        findViewById(R.id.main_button).setOnClickListener(v -> checkAndShow(personList,
                name -> name.startsWith("Z"),
                this::show));
    }

    private static void checkAndShow(List<Person> Persons, NameChecker c, Shower shower){
        for (Person p : Persons) {
            if (c.check(p.getFirstName())) shower.show(p);
        }
    }

    private void show(Person p){
        Toast.makeText(this, p.toString(), Toast.LENGTH_SHORT).show();
    }



}

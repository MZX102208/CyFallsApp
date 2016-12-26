package com.example.sriramhariharan.cyfallsapp2016;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import org.joda.time.DateTime;

import java.lang.*;
import java.lang.Class;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Home.OnFragmentInteractionListener, Grades.OnFragmentInteractionListener, Schedule.OnFragmentInteractionListener, Attendance.OnFragmentInteractionListener, ReportCard.OnFragmentInteractionListener{


    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String current = "";
        String lunch = Values.PKG.lunch;
        DateTime dt = new DateTime();
        int min = dt.getMinuteOfDay();




        if(min < 440)
            current = ("Before School");
        else if(min >= 440 && min < 493)
            current =("1st Period");
        else if(min >= 499 && min < 555)
            current =("2nd Period");
        else if(min >= 561 && min < 614)
            current =("3rd Period");
        else if(lunch.equals("A") && min >= 614 && min < 644)
            current =("A lunch");
        else if(lunch.equals("A") && min >= 650 && min < 703)
            current =("4th Period");
        else if(lunch.equals("A") && min >= 709 && min < 762)
            current =("5th Period");
        else if(lunch.equals("B") && min >= 620 && min < 673)
            current =("4th Period");
        else if(lunch.equals("B") && min >= 673 && min < 703)
            current =("B lunch");
        else if(lunch.equals("B") && min >= 709 && min < 762)
            current =("5th Period");
        else if(lunch.equals("C") && min >= 620 && min < 673)
            current ="4th Period";
        else if(lunch.equals("C") && min >= 679 && min < 732)
            current ="5th Period";
        else if(lunch.equals("C") && min >= 732 && min < 762)
            current =("C lunch");
        else if(min >= 768 && min < 821)
            current =("6th Period");
        else if(min >= 827 && min < 880)
            current =("7th Period");
        else if(min >= 880)
            current =("After School");
        else
            current =("Passing Period");
        Values.current = current;
        ArrayList<TimSched> timesch = new ArrayList();
        timesch.add(new TimSched(Values.courses.get(0).getName(), "7:20 – 8:13",Values.courses.get(0).getRoomnum()));
        timesch.add(new TimSched(Values.courses.get(1).getName(), "8:19 – 9:15",Values.courses.get(1).getRoomnum()));
        timesch.add(new TimSched(Values.courses.get(2).getName(), "9:21 – 10:14",Values.courses.get(2).getRoomnum()));

        if (lunch.equals("A")) {
            timesch.add(new TimSched("A Lunch", "10:14 – 10:44","Cafeteria"));
            timesch.add(new TimSched(Values.courses.get(3).getName(), "10:50 – 11:43",Values.courses.get(3).getRoomnum()));
            timesch.add(new TimSched(Values.courses.get(4).getName(), "11:59 – 12:42",Values.courses.get(4).getRoomnum()));
        }
        else if (lunch.equals("B")) {
            timesch.add(new TimSched(Values.courses.get(3).getName(), "10:20 – 11:13",Values.courses.get(3).getRoomnum()));
            timesch.add(new TimSched("B Lunch", "11:13 – 11:43","Cafeteria"));
            timesch.add(new TimSched(Values.courses.get(4).getName(), "11:49 – 12:42",Values.courses.get(4).getRoomnum()));
        }
        else if (lunch.equals("C")) {
            timesch.add(new TimSched(Values.courses.get(3).getName(), "10:20 – 11:13",Values.courses.get(3).getRoomnum()));
            timesch.add(new TimSched(Values.courses.get(4).getName(), "11:19 – 12:12",Values.courses.get(4).getRoomnum()));
            timesch.add(new TimSched("C Lunch", "12:12 – 12:42","Cafeteria"));
        }
        timesch.add(new TimSched(Values.courses.get(5).getName(), "12:48 – 1:41",Values.courses.get(5).getRoomnum()));
        timesch.add(new TimSched(Values.courses.get(6).getName(), "1:47 – 2:40",Values.courses.get(6).getRoomnum()));
        Values.schedule = timesch;




        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Fragment fragment = null;
        Class Home = Home.class;

        try {
            fragment = (Fragment) Home.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        FragmentManager fragmentManager = getSupportFragmentManager();

        for(int i = 0;i<Values.courses.size();i++){
            if(Values.courses.get(i).getAssignments().size() == 0){
                Values.courses.remove(i);
            }
        }

        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
        Toast.makeText(this,"Welcome "+Values.PKG.name+"!",Toast.LENGTH_LONG);
        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                Fragment fragment = null;
                Class fragmentClass;
                switch(tabId){
                    case R.id.tab_schedule:
                        fragmentClass = Schedule.class;
                        break;
                    case R.id.tab_grades:
                        fragmentClass = Grades.class;
                        break;
                    case R.id.tab_reportcard:
                        fragmentClass = ReportCard.class;
                        break;
                    case R.id.tab_attendance:
                        fragmentClass = Attendance.class;
                        break;
                    default:
                        fragmentClass = Grades.class;
                }
                try {
                    fragment = (Fragment) fragmentClass.newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

            }
        });
    }

    public void logOut() {
        SharedPreferences settings = MainActivity.this.getSharedPreferences("Login", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.clear();
        editor.commit();
        Intent intent = new Intent(this, Login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    public void onFragmentInteraction(Uri uri) {
        //you can leave it empty
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.bugreport:
                Uri uri = Uri.parse("https://goo.gl/forms/gNEJ3RQAw6vVCFeO2"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                return true;
            case R.id.action_settings:
                new AlertDialog.Builder(this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Logging Out")
                        .setMessage("Are you sure you want to Log out of the app?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                logOut();
                            }

                        })
                        .setNegativeButton("No", null)
                        .show();
                /*
                Intent in = new Intent(this,Settings.class);
                startActivity(in);
                return true;
                */
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actionbutton, menu);
        return true;
    }
}

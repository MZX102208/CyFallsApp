package com.example.sriramhariharan.cyfallsapp2016;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import org.joda.time.DateTime;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Home.OnFragmentInteractionListener, Grades.OnFragmentInteractionListener, Schedule.OnFragmentInteractionListener, Attendance.OnFragmentInteractionListener, ReportCard.OnFragmentInteractionListener{


    private Toolbar toolbar;


    @Override
    protected void onResume() {
        int lunch = 0;
        DateTime dt = new DateTime();

        if(dt.getMonthOfYear() >= 8) {
            int second = 0;
            for (String s : Values.PKG.schedulesem1) {
                if (s.equals("Lunch")) {
                    lunch = Values.PKG.schedulesem1.indexOf(s);

                    Values.lunch1 = lunch;
                    Log.e("LUNCH",Values.lunch1+"");

                }
            }
            for(String s: Values.PKG.schedulesem2){
                if(s.equals("Lunch")){
                    second = Values.PKG.schedulesem2.indexOf(s);
                    Values.lunch2 = second;
                }
            }
            if(lunch != 0 && second != 0) {
                Values.PKG.schedulesem1.remove(lunch);
                Values.PKG.schedulesem2.remove(second);
            }
            Log.e("SC",Values.PKG.schedulesem1.toString());

        }
        if(dt.getMonthOfYear() >=1 && dt.getMonthOfYear() < 8) {
            Log.e("HELLO","hello");
            int second = 0;

            for (String s : Values.PKG.schedulesem2) {

                if (s.equals("Lunch")) {
                    lunch = Values.PKG.schedulesem2.indexOf(s);

                    Values.lunch2 = lunch;
                }
            }
            for(String s: Values.PKG.schedulesem1){
                if(s.equals("Lunch")){
                    second = Values.PKG.schedulesem1.indexOf(s);
                    Values.lunch1 = second;
                }
            }
            if(lunch != 0 && second != 0) {
                Values.PKG.schedulesem1.remove(second);
                Values.PKG.schedulesem2.remove(lunch);
            }
        }
        int min = dt.getMinuteOfDay();
        String current = "";
        if(min < 440)
            current = ("Before School");
        else if(min >= 440 && min < 493)
            current =("1st Period");
        else if(min >= 499 && min < 555)
            current =("2nd Period");
        else if(min >= 561 && min < 614)
            current =("3rd Period");
        else if(lunch == 3 && min >= 614 && min < 644)
            current =("A lunch");
        else if(lunch == 3  && min >= 650 && min < 703)
            current =("4th Period");
        else if(lunch == 3 && min >= 709 && min < 762)
            current =("5th Period");
        else if(lunch == 4 && min >= 620 && min < 673)
            current =("4th Period");
        else if(lunch == 4 && min >= 673 && min < 703)
            current =("B lunch");
        else if(lunch == 4 && min >= 709 && min < 762)
            current =("5th Period");
        else if(lunch == 5 && min >= 620 && min < 673)
            current ="4th Period";
        else if(lunch == 5 && min >= 679 && min < 732)
            current ="5th Period";
        else if(lunch == 5 && min >= 732 && min < 762)
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
        if(dt.getDayOfWeek() == 6 || dt.getDayOfWeek() == 7){
            Values.current = ("No School");
        }
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String current = "";
        int lunch = 0;
        DateTime dt = new DateTime();
        if(dt.getMonthOfYear() >= 8) {
            int second = 0;
            for (String s : Values.PKG.schedulesem1) {
                if (s.equals("Lunch")) {
                    lunch = Values.PKG.schedulesem1.indexOf(s);

                    Values.lunch1 = lunch;
                    Log.e("LUNCH",Values.lunch1+"");

                }
            }
            for(String s: Values.PKG.schedulesem2){
                if(s.equals("Lunch")){
                    second = Values.PKG.schedulesem2.indexOf(s);
                    Values.lunch2 = second;
                }
            }
            if(lunch != 0 && second != 0) {
                Values.PKG.schedulesem1.remove(lunch);
                Values.PKG.schedulesem2.remove(second);
            }
            Log.e("SC",Values.PKG.schedulesem1.toString());

        }
         if(dt.getMonthOfYear() >=1 && dt.getMonthOfYear() < 8) {
            Log.e("HELLO","hello");
            int second = 0;

            for (String s : Values.PKG.schedulesem2) {

                if (s.equals("Lunch")) {
                    lunch = Values.PKG.schedulesem2.indexOf(s);

                    Values.lunch2 = lunch;
                }
            }
            for(String s: Values.PKG.schedulesem1){
                if(s.equals("Lunch")){
                    second = Values.PKG.schedulesem1.indexOf(s);
                    Values.lunch1 = second;
                }
            }
             if(lunch != 0 && second != 0) {
                 Values.PKG.schedulesem1.remove(second);
                 Values.PKG.schedulesem2.remove(lunch);
             }
        }

        /*
        for(int i = 0; i<Values.PKG.schedulesem1.size()-1;i++){
            if(Values.PKG.schedulesem1.get(i).equals(Values.PKG.schedulesem1.get(i+1))){
                Values.PKG.schedulesem1.remove(i);
            }
        }
        for(int i = 0; i<Values.PKG.schedulesem2.size()-1;i++){
            if(Values.PKG.schedulesem2.get(i).equals(Values.PKG.schedulesem2.get(i+1))){
                Values.PKG.schedulesem2.remove(i);
            }
        }

        */

        int min = dt.getMinuteOfDay();

        if(min < 440)
            current = ("Before School");
        else if(min >= 440 && min < 493)
            current =("1st Period");
        else if(min >= 499 && min < 555)
            current =("2nd Period");
        else if(min >= 561 && min < 614)
            current =("3rd Period");
        else if(lunch == 3 && min >= 614 && min < 644)
            current =("A lunch");
        else if(lunch == 3  && min >= 650 && min < 703)
            current =("4th Period");
        else if(lunch == 3 && min >= 709 && min < 762)
            current =("5th Period");
        else if(lunch == 4 && min >= 620 && min < 673)
            current =("4th Period");
        else if(lunch == 4 && min >= 673 && min < 703)
            current =("B lunch");
        else if(lunch == 4 && min >= 709 && min < 762)
            current =("5th Period");
        else if(lunch == 5 && min >= 620 && min < 673)
            current ="4th Period";
        else if(lunch == 5 && min >= 679 && min < 732)
            current ="5th Period";
        else if(lunch == 5 && min >= 732 && min < 762)
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
        if(dt.getDayOfWeek() == 6 || dt.getDayOfWeek() == 7){
            Values.current = ("No School");
        }


        ArrayList<TimSched> timesch = new ArrayList<>();
        timesch.add(new TimSched(Values.PKG.schedulesem1.get(0), "7:20 – 8:13"));
        timesch.add(new TimSched(Values.PKG.schedulesem1.get(1), "8:19 – 9:15"));
        timesch.add(new TimSched(Values.PKG.schedulesem1.get(2), "9:21 – 10:14"));

        if (Values.lunch1 == 3) {
            timesch.add(new TimSched("A Lunch", "10:14 – 10:44"));
            timesch.add(new TimSched(Values.PKG.schedulesem1.get(3), "10:50 – 11:43"));
            timesch.add(new TimSched(Values.PKG.schedulesem1.get(4), "11:59 – 12:42"));
        }
        else if (Values.lunch1 == 4) {
            timesch.add(new TimSched(Values.PKG.schedulesem1.get(3), "10:20 – 11:13"));
            timesch.add(new TimSched("B Lunch", "11:13 – 11:43"));
            timesch.add(new TimSched(Values.PKG.schedulesem1.get(4), "11:49 – 12:42"));
        }
        else if (Values.lunch1 == 5) {
            timesch.add(new TimSched(Values.PKG.schedulesem1.get(3), "10:20 – 11:13"));
            timesch.add(new TimSched(Values.PKG.schedulesem1.get(4), "11:19 – 12:12"));
            timesch.add(new TimSched("C Lunch", "12:12 – 12:42"));
        }

        Log.e("JET SCHEDULE",Values.PKG.schedulesem1.toString());
        Log.e("JET SCHEDULE",Values.PKG.schedulesem2.toString());

        try {
            timesch.add(new TimSched(Values.PKG.schedulesem1.get(5), "12:48 – 1:41"));
            timesch.add(new TimSched(Values.PKG.schedulesem1.get(6), "1:47 – 2:40"));
        }
        catch (Exception e){

        }


        Values.firstsem = timesch;

        ArrayList<TimSched> timesch2 = new ArrayList<>();
        timesch2.add(new TimSched(Values.PKG.schedulesem2.get(0), "7:20 – 8:13"));
        timesch2.add(new TimSched(Values.PKG.schedulesem2.get(1), "8:19 – 9:15"));
        timesch2.add(new TimSched(Values.PKG.schedulesem2.get(2), "9:21 – 10:14"));

        if (Values.lunch2 == 3) {
            timesch2.add(new TimSched("A Lunch", "10:14 – 10:44"));
            timesch2.add(new TimSched(Values.PKG.schedulesem2.get(3), "10:50 – 11:43"));
            timesch2.add(new TimSched(Values.PKG.schedulesem2.get(4), "11:59 – 12:42"));
        }
        else if (Values.lunch2 == 4) {
            timesch2.add(new TimSched(Values.PKG.schedulesem2.get(3), "10:20 – 11:13"));
            timesch2.add(new TimSched("B Lunch", "11:13 – 11:43"));
            timesch2.add(new TimSched(Values.PKG.schedulesem2.get(4), "11:49 – 12:42"));
        }
        else if (Values.lunch2 == 5) {
            timesch2.add(new TimSched(Values.PKG.schedulesem2.get(3), "10:20 – 11:13"));
            timesch2.add(new TimSched(Values.PKG.schedulesem2.get(4), "11:19 – 12:12"));
            timesch2.add(new TimSched("C Lunch", "12:12 – 12:42"));
        }
        try {
            timesch2.add(new TimSched(Values.PKG.schedulesem2.get(5), "12:48 – 1:41"));
            timesch2.add(new TimSched(Values.PKG.schedulesem2.get(6), "1:47 – 2:40"));
        }
        catch (Exception e){

        }

        Values.seconsem = timesch2;






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
                        Log.e("Courses currently",Values.PKG.classes.toString());
                        fragmentClass = Grades.class;
                        break;
                    case R.id.tab_reportcard:
                        Log.e("Courses currently",Values.PKG.classes.toString());
                        fragmentClass = ReportCard.class;
                        break;
                    case R.id.tab_attendance:
                        fragmentClass = Attendance.class;
                        break;
                    default:
                        Log.e("Courses currently",Values.PKG.classes.toString());
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

package com.example.sriramhariharan.cyfallsapp2016;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BackgroundService extends Service {

    public Context context = this;
    public Handler handler = null;
    public static Runnable runnable = null;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {

        handler = new Handler();
        runnable = new Runnable() {
            public void run() {
                //Insert action code here
                //      Toast.makeText(getApplicationContext(), "ran!", Toast.LENGTH_SHORT).show();

                UserLoginTask mAuthTask = new UserLoginTask();
                mAuthTask.execute((Void) null);


//60,000
                handler.postDelayed(runnable, 60000);
                //1800000 is 30 mins

            }
        };

        handler.postDelayed(runnable, 15000);
    }

    @Override
    public void onDestroy() {
        /* IF YOU WANT THIS SERVICE KILLED WITH THE APP THEN UNCOMMENT THE FOLLOWING LINE */
        //handler.removeCallbacks(runnable);
        //Toast.makeText(this, "Service stopped", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onStart(Intent intent, int startid) {
        //     Toast.makeText(this, "Service started by user.", Toast.LENGTH_LONG).show();
    }
    public class UserLoginTask extends AsyncTask<Void, Void, String> {


        UserLoginTask() {


        }




        @Override
        protected String doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.
            try {

                SharedPreferences preferences = getSharedPreferences("Login", Context.MODE_PRIVATE);
                SharedPreferences.Editor infoedit = preferences.edit();
                String muser = preferences.getString("Username", "");
                String mPassword = preferences.getString("Password", "");


                int sem = 0;
                DateTime dt = new DateTime();
                if(dt.getMonthOfYear() >= 8 && dt.getMonthOfYear() < 12){
                    sem = 1;
                }
                if(dt.getMonthOfYear() >= 1 && dt.getMonthOfYear() < 8){
                    sem = 2;
                }
                ClssPkg p = ClssPkg.getFromServer(muser,mPassword,2);
                Values.PKG = p;
                Values.courses = p.classes;
                Values.PKGcopy = ClssPkg.parse(p.toString());
                return "success";

            }
            catch(Exception e){
                return "Exception";
            }

        }


        @Override
        protected void onPostExecute(final String success) {

            Log.e("SUCCESS",success);

            if (success.equals("success")) {
                SharedPreferences preferences = getSharedPreferences("Userinfo", Context.MODE_PRIVATE);
                SharedPreferences.Editor infoedit = preferences.edit();
                String muser = preferences.getString("Username", "");
                String mPassword = preferences.getString("Password", "");
                String courses = preferences.getString("Courses", "");
                List<Course> courses1 = ClssPkg.parse(courses).classes;
                ArrayList<String> adds = new ArrayList<String>();
                ArrayList<String> changes = new ArrayList<String>();

                Log.e("BACKGROUND",courses1.toString());
                Log.e("BACKGROUND",Values.PKG.classes.toString());

                ClssPkg p = Values.PKG;
                String stringedpkg = p.toString();
                infoedit.putString("Courses", stringedpkg);
                infoedit.commit();

                //            Log.e("HELLO", p.toString());

                for (int i = 0; i < p.classes.size(); i++) {
                    if (p.classes.get(i).name.equals(courses1.get(i).name)) {

                        if(!p.classes.get(i).assignments.toString().equals(courses1.get(i).assignments.toString())){
                            if(p.classes.get(i).assignments.size() > courses1.get(i).assignments.size()){
                                adds.add(p.classes.get(i).name);
                            }
                            else {

                                for (int j = 0;j<p.classes.get(i).assignments.size();j++){
                                    if(!p.classes.get(i).assignments.get(j).toString().equals(courses1.get(i).assignments.get(j).toString())){
                                        changes.add(p.classes.get(i).name);
                                    }
                                }
                            }
                        }
                    }
                }
                Log.e("HELLLLO",changes.toString());

                for (String s : changes) {
                    Log.e("HELLLLO",s);
                    NotificationCompat.Builder mBuilder =
                            new NotificationCompat.Builder(getApplicationContext())
                                    .setSmallIcon(R.drawable.ic_school_black_24dp)
                                    .setContentTitle("Cypress Falls App")
                                    .setContentText( "A grade in "+ s +" was edited.");
                    Intent resultIntent = new Intent(getApplicationContext(), Login.class);
                    TaskStackBuilder stackBuilder = TaskStackBuilder.create(getApplicationContext());
                    stackBuilder.addParentStack(MainActivity.class);
                    stackBuilder.addNextIntent(resultIntent);
                    PendingIntent resultPendingIntent =
                            stackBuilder.getPendingIntent(
                                    0,
                                    PendingIntent.FLAG_UPDATE_CURRENT
                            );
                    mBuilder.setContentIntent(resultPendingIntent);
                    NotificationManager mNotificationManager =
                            (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                    mNotificationManager.notify((int) ((new Date().getTime() / 1000L) % Integer.MAX_VALUE), mBuilder.build());
                }
                for (String s : adds) {
                    Log.e("HELLLLO",s);
                    NotificationCompat.Builder mBuilder =
                            new NotificationCompat.Builder(getApplicationContext())
                                    .setSmallIcon(R.drawable.ic_school_black_24dp)
                                    .setContentTitle("Cypress Falls App")
                                    .setContentText("A grade in " + s + " was added");
                    Intent resultIntent = new Intent(getApplicationContext(), Login.class);
                    TaskStackBuilder stackBuilder = TaskStackBuilder.create(getApplicationContext());
                    stackBuilder.addParentStack(MainActivity.class);
                    stackBuilder.addNextIntent(resultIntent);
                    PendingIntent resultPendingIntent =
                            stackBuilder.getPendingIntent(
                                    0,
                                    PendingIntent.FLAG_UPDATE_CURRENT
                            );
                    mBuilder.setContentIntent(resultPendingIntent);
                    NotificationManager mNotificationManager =
                            (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                    mNotificationManager.notify((int) ((new Date().getTime() / 1000L) % Integer.MAX_VALUE), mBuilder.build());
                }




            }



        }


    }
}
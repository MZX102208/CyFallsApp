package com.example.sriramhariharan.cyfallsapp2016;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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
                SharedPreferences preferences = getApplicationContext().getSharedPreferences("Userinfo", Context.MODE_PRIVATE);
                SharedPreferences.Editor infoedit = preferences.edit();
                String muser = preferences.getString("Username", "");
                String mPassword = preferences.getString("Password", "");
                String courses = preferences.getString("Courses", "");
                Gson gson = new Gson();
                java.lang.reflect.Type type = new TypeToken<List<Course>>() {
                }.getType();
                List<Course> courses1 = gson.fromJson(courses, type);
                ArrayList<String> adds = new ArrayList<String>();
                ArrayList<String> changes = new ArrayList<String>();



                try {
                    ClssPkg p = ClssPkg.getFromServer(muser, mPassword);
                    if (!(p.toString().equals("Wrong login"))) {
                        Values.PKG = p;
                        Values.courses = p.classes;
                        Values.PKGcopy = ClssPkg.parse(p.toString());

                        //            Log.e("HELLO", p.toString());

                        for (int i = 0; i < p.classes.size(); i++) {
                            if (p.classes.get(i).name.equals(courses1.get(i).name)) {

                                if (p.classes.get(i).assignments.size() != courses1.get(i).assignments.size()) {
                                    adds.add(p.classes.get(i).getName());
                                } else {
                                    for (Assignment a : p.classes.get(i).assignments) {
                                        courses1.get(i).assignments.remove(a);
                                    }
                                    if (courses1.get(i).assignments.size() > 0) {
                                        changes.add(p.classes.get(i).name);
                                    }
                                }
                            }
                        }
                        for(String s: adds) {
                            NotificationCompat.Builder mBuilder =
                                    new NotificationCompat.Builder(getApplicationContext())
                                            .setSmallIcon(R.drawable.delete)
                                            .setContentTitle("Cypress Falls App")
                                            .setContentText("A grade was added in "+s+".");
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
                        for(String s: changes) {
                            NotificationCompat.Builder mBuilder =
                                    new NotificationCompat.Builder(getApplicationContext())
                                            .setSmallIcon(R.drawable.delete)
                                            .setContentTitle("Cypress Falls App")
                                            .setContentText("A grade was changed in "+s+".");
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

                        courses1 = Values.courses;
                        gson = new Gson();
                        String jsoncourses = gson.toJson(courses);
                        infoedit.putString("Courses", jsoncourses);
                        infoedit.commit();
                    }

                } catch (Exception e) {
                    Log.e("THE ERROR", e.toString());
                }
                handler.postDelayed(runnable, 10000);
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
}
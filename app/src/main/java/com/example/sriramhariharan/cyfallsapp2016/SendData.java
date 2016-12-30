package com.example.sriramhariharan.cyfallsapp2016;

import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by User1 on 12/30/2016.
 */
public class SendData extends AsyncTask<Void, Void, String> {

    String data;

    public SendData(String d){
        data =d;
    }


    @Override
    protected String doInBackground(Void... params) {
        // TODO: attempt authentication against a network service.
        try {
            Values.PKG.sendActivityData(data);
        } catch (Exception e) {
            Log.e("THE ERROR",e.toString());
        }
        return "";
    }


    @Override
    protected void onPostExecute(final String success) {

    }

    @Override
    protected void onCancelled() {

    }

}


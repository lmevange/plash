package com.example.leo.plash.service;

import android.os.AsyncTask;

import com.example.leo.plash.WeatherActivity;

/**
 * Created by Joseph on 11/12/15.
 */
public class Weatherundergroundservice {
    private WeatherServiceCallback _cl;
    private String Location;

    public Weatherundergroundservice(WeatherServiceCallback cl){
        this._cl = cl;

    }



    public String getLocation(){
        return Location;
    }

    public void refreash(String l){
        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... params) {
                return null;
            }
            @Override
            protected void onPostExecute(String s){
                super.onPostExecute(s);
            }

        }.execute(l);

    }
}

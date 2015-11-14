package com.example.leo.plash.service;

import android.net.Uri;
import android.os.AsyncTask;

import com.example.leo.plash.Data.CurrentObservation;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Joseph on 11/12/15.
 */
public class Weatherundergroundservice {
    private WeatherServiceCallback _cl;
    private String location;
    private Exception error;

    public Weatherundergroundservice(WeatherServiceCallback cl){
        this._cl = cl;

    }



    public String getLocation(){
        return location;
    }

    public void refreash(final String location){
        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... params) {
                String q = String.format("select location\"@s\"",location);
                String endPoint = String.format("http://api.wunderground.com/api/3cf16a40152a4b1f/conditions/forecast/q/@s.json", Uri.encode(q));

               try{
                   URL url = new URL(endPoint);

                   URLConnection con = url.openConnection();

                   InputStream im = con.getInputStream();

                   BufferedReader read = new BufferedReader( new InputStreamReader(im));
                   StringBuilder sb = new StringBuilder();

                   String line;
                   while((line = read.readLine()) != null){
                       sb.append(line);
                   }

                   return sb.toString();

               } catch (Exception e) {
                   error = e;
               }

                return null;
            }
            @Override
            protected void onPostExecute(String s){

                if(s == null  && error != null)
                {
                    _cl.serviceFailure(error);
                    return;
                }
                try {
                    JSONObject jo = new JSONObject(s);
                    JSONObject queryResults = jo.optJSONObject("query");
                   String type = queryResults.getString("type");
                    if(type == "querynotfound"){
                        _cl.serviceFailure(new LocationWeatherException("No cities match your search query:"+location));
                        return;

                    }

                    CurrentObservation currentObservation = new CurrentObservation();
                    currentObservation.JSONpopulate(queryResults.optJSONObject("features").optJSONObject("currentObservation"));
                    _cl.serviceSuccess(currentObservation);
                } catch (JSONException e) {
                    _cl.serviceFailure(e);
                }

            }

        }.execute(location);

    }

    public class LocationWeatherException extends Exception{
        public LocationWeatherException(String detailMessage) {
            super(detailMessage);
        }
    }
}

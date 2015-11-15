package com.example.leo.plash.service;

import android.net.Uri;
import android.os.AsyncTask;

import com.example.leo.plash.Data.Channel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;


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

    public void refreash( String loca){

        this.location = loca;

        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... strings) {
<<<<<<< HEAD
                String q = String.format(" where text=\"@s\"",strings[0]);
                String endPoint = String.format("http://api.wunderground.com/api/3cf16a40152a4b1f/conditions/forecast/q/@s.json", Uri.encode(q));
=======
                String q = String.format("select location\"%    s\"",strings[0]);
                String endPoint = String.format("http://api.wunderground.com/api/3cf16a40152a4b1f/conditions/forecast/q/%s.json", Uri.encode(q));
>>>>>>> origin/master

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
                    if(type == "error"  ){
                        _cl.serviceFailure(new LocationWeatherException("No cities match your search query:"+location));
                        return;

                    }

                    Channel channel = new Channel();
                    channel.JSONpopulate(queryResults.optJSONObject("results").optJSONObject("currentObservation"));
                    _cl.serviceSuccess(channel);
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


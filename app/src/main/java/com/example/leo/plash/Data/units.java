package com.example.leo.plash.Data;


import org.json.JSONObject;

/**
 * Created by Joseph on 11/12/15.
 */
public class Units implements JSONPopulator {
    private String temperature;

    public String getTemperature() {
        return temperature;
    }

    @Override
    public void populate(JSONObject data) {
        temperature = data.optString("temperature");
    }
}

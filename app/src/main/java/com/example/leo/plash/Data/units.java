package com.example.leo.plash.Data;


import org.json.JSONObject;

/**
 * Created by Joseph on 11/12/15.
 */
public class Units implements JSONpopulate {

    private String _temp;

    public String get_temp() {
        return _temp;
    }

    @Override
    public void JSONpopulate(JSONObject Data) {

        _temp = Data.optString("Temprature");
    }
}

package com.example.leo.plash.Data;

import org.json.JSONObject;

/**
 * Created by Joseph on 11/12/15.
 */
public class Condition implements JSONpopulate {
    private int _it;
    private int _temp;
    private String _descrip;

    public int get_it() {
        return _it;
    }

    public int get_temp() {
        return _temp;
    }

    public String get_descrip() {
        return _descrip;
    }

    @Override
    public void JSONpopulate(JSONObject Data) {

        _it = Data.optInt("it");
        _temp = Data.optInt("Temp");
        _descrip= Data.optString("Descrip");

    }
}

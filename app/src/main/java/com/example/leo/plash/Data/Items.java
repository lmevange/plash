package com.example.leo.plash.Data;

import org.json.JSONObject;

/**
 * Created by Joseph on 11/12/15.
 */
public class Items implements JSONpopulate {

    private Condition _con;

    @Override
    public void JSONpopulate(JSONObject Data) {

        _con = new Condition();
        _con.JSONpopulate(Data.optJSONObject("conditions"));
    }
}

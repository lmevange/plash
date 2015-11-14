package com.example.leo.plash.Data;

import org.json.JSONObject;

/**
 * Created by Joseph on 11/12/15.
 */
public class CurrentObservation implements JSONpopulate {

    private Units _units;
    private Items _items;

    public Units get_units() {
        return _units;
    }

    public Items get_items() {
        return _items;
    }

    @Override
    public void JSONpopulate(JSONObject Data) {
        _units = new Units();
        _units.JSONpopulate(Data.optJSONObject("Units"));

        _items = new Items();
        _items.JSONpopulate(Data.optJSONObject("Items"));

    }
}

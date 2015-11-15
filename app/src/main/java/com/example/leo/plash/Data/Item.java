package com.example.leo.plash.Data;

import org.json.JSONObject;

/**
 * Created by Joseph on 11/12/15.
 */
public class Item implements JSONPopulator {
    private Condition condition;

    public Condition getCondition() {
        return condition;
    }

    @Override
    public void populate(JSONObject data) {
        condition = new Condition();
        condition.populate(data.optJSONObject("condition"));
    }
}
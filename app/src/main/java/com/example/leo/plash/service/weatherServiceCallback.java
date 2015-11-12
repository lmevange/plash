package com.example.leo.plash.service;

import com.example.leo.plash.Data.Channel;

/**
 * Created by Joseph on 11/12/15.
 */
public interface WeatherServiceCallback {
    void serviceSuccess(Channel channel);
    void serviceFailure(Exception e);
}

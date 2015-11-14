package com.example.leo.plash;

import android.app.ProgressDialog;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.leo.plash.Data.CurrentObservation;
import com.example.leo.plash.service.WeatherServiceCallback;
import com.example.leo.plash.service.Weatherundergroundservice;


public class WeatherActivity extends ActionBarActivity implements WeatherServiceCallback {
    private ImageView _weatherIcon;
    private TextView _tvTemp;
    private TextView _tvCond;
    private TextView _tvLoca;

    private Weatherundergroundservice _service;
    private ProgressDialog _pd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        _weatherIcon = (ImageView) findViewById(R.id.weatherIconImageView);
        _tvTemp = (TextView)findViewById(R.id.temperatureTextView);
        _tvCond = (TextView)findViewById(R.id.conditionTextView);
        _tvLoca = (TextView)findViewById(R.id.locationTextView);

        _service = new Weatherundergroundservice(this);

        _pd = new ProgressDialog(this);
        _pd.setMessage("Loading...");
        _pd.show();

        _service.refreash("Buffalo,NY");



    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_weather, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void serviceSuccess(CurrentObservation channel) {

    }

    @Override
    public void serviceFailure(Exception e) {

        Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
    }
}

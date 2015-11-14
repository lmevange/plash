package com.example.leo.plash;

import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.content.pm.PackageInfo;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.leo.plash.Data.CurrentObservation;
import com.example.leo.plash.Data.Items;
import com.example.leo.plash.service.WeatherServiceCallback;
import com.example.leo.plash.service.Weatherundergroundservice;


public class WeatherActivity extends ActionBarActivity implements WeatherServiceCallback {
    private ImageView _weatherIcon;
    private TextView _tvTemp;
    private TextView _tvCond;
    private TextView _tvLoca;

    private Weatherundergroundservice _service;

    private ProgressDialog _pd;

<<<<<<< Updated upstream

    static final int REQUEST_IMAGE_CAPTURE =1;
    ImageView weatherImage;
=======
    Button btnTakePhoto;
    private static final int REQUEST_IMAGE_CAPTURE =1313;
    ImageView weatherImage;

>>>>>>> Stashed changes

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
        btnTakePhoto = (Button) findViewById(R.id.weatherButton);


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
        _pd.hide();

        Items items = channel.get_items();
        int resource = getResources().getIdentifier("drawable/icon_" + items.get_con().get_it(), null, getPackageName());

        Drawable weatherIcon = getResources().getDrawable(resource);

        _weatherIcon.setImageDrawable(weatherIcon);
        _tvTemp.setText(items.get_con().get_temp()+"\u00b0"+channel.get_units().get_temp());
        _tvCond.setText(items.get_con().get_descrip());
        _tvLoca.setText(_service.getLocation());

    }

    @Override
    public void serviceFailure(Exception e) {
        _pd.hide();
        Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
    }
}

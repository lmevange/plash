package com.example.leo.plash;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.leo.plash.Data.CurrentObservation;
import com.example.leo.plash.Data.Items;
import com.example.leo.plash.service.WeatherServiceCallback;
import com.example.leo.plash.service.Weatherundergroundservice;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Leo on 11/14/15.
 */
public class CameraActivity extends Activity {

        private static final int ACTIVITY_START_CAMERA_APP = 0;
        private ImageView mPhotoCapturedImageView;
        private String mImageFileLocation = "";

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_weather);


            mPhotoCapturedImageView = (ImageView) findViewById(R.id.weatherImage);

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


        public void takePhoto(View view){
            Intent callCameraApplicationIntent = new Intent();
            callCameraApplicationIntent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);

            File photoFile = null;
            try{
                photoFile = createImageFileSave();

            } catch (IOException e){
                e.printStackTrace();
            }
            callCameraApplicationIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));

            startActivityForResult(callCameraApplicationIntent, ACTIVITY_START_CAMERA_APP);


        }

        protected  void onActivityResult (int requestCode, int resultCode,Intent data){
            if(requestCode == ACTIVITY_START_CAMERA_APP && resultCode == RESULT_OK){
                //Bundle extras = data.getExtras();
                //Bitmap photoCapturedBitmap = (Bitmap) extras.get("data");
                //mPhotoCapturedImageView.setImageBitmap(photoCapturedBitmap);
                // Bitmap photoCapturedBitmap = BitmapFactory.decodeFile(mImageFileLocation);
                // mPhotoCapturedImageView.setImageBitmap(photoCapturedBitmap);
                setReducedImageSize();

            }

        }

        File createImageFileSave() throws IOException{

            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String imageFileName = "IMAGE_" + timeStamp + "_";
            File storageDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

            File image = File.createTempFile(imageFileName, ".jpg", storageDirectory);
            mImageFileLocation = image.getAbsolutePath();

            return image;
        }

        void setReducedImageSize(){
            int targetImageViewWidth = mPhotoCapturedImageView.getWidth();
            int targetImageViewHeight = mPhotoCapturedImageView.getHeight();

            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
            bmOptions.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(mImageFileLocation, bmOptions);
            int cameraImageWidth = bmOptions.outWidth;
            int cameraImageHeight = bmOptions.outHeight;

            int scaleFactor = Math.min(cameraImageWidth/targetImageViewWidth, cameraImageHeight/targetImageViewHeight);
            bmOptions.inSampleSize = scaleFactor;
            bmOptions.inJustDecodeBounds = false;

            Bitmap photoReducedSizeBitmap = BitmapFactory.decodeFile(mImageFileLocation, bmOptions);
            mPhotoCapturedImageView.setImageBitmap(photoReducedSizeBitmap);
        }


    }




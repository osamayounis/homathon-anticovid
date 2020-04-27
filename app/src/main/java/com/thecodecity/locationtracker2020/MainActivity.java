package com.thecodecity.locationtracker2020;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.Toast;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;



import static android.widget.Toast.LENGTH_SHORT;
import static com.thecodecity.locationtracker2020.R.drawable.shape2;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {


    SupportMapFragment mapFragment;
    GoogleMap mMap;
    Marker marker;
    Marker m1;
    LocationBroadcastReceiver receiver;

    //All of this data will be get from database
    LatLng latLngDB = new LatLng(26.416429,50.016765);

    LatLng latlangDBOm = new LatLng(21.5188989,39.2264335);

    LatLng latLngDB1 = new LatLng(26.416147,50.017399 );//eqtelat3
    /*
    LatLng latLngDB2 = new LatLng(26.4161492,50.0173718);//eqtelat2
    LatLng latLngDB3 = new LatLng(26.4155985,50.0175714);//motawaa
    LatLng latLngDB4 = new LatLng(26.4160993,50.0166903);//somran
    LatLng latLngDB5 = new LatLng(26.4157473,50.0164432);//kalf somran

     */


    Marker m2;
    Marker m3;
    Marker m4;
    //Marker m5;



    int isCir = 0;
    int isCir2 = 0;
    int isCir3=0;
    Button isInHome;
   // Button Test;
    TextView infarea;
  //  EditText UserName;
   // String User;





    //code to conect to database
//    private static final String DB_URL = "jdbc:mysql://127.0.0.1/data_base_name_here";
  //  private static final String USER = "root";
    //private static final String PASS = "123456";
    //double dis;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_map_activity);
        isInHome = (Button) findViewById(R.id.Stuation2);
        //Test = (Button) findViewById(R.id.Test);
        infarea = (TextView) findViewById(R.id.infarea);
        isInHome.setEnabled(false);
        //UserName = (EditText) findViewById(R.id.UserName);

      //  User = UserName.getText().toString();




        receiver = new LocationBroadcastReceiver();
//chek you sdk version
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            } else {
                //Req Location Permission
                startLocService();
            }
        } else {
            //Start the Location Service
            startLocService();
        }
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapFrag);
        mapFragment.getMapAsync(this);



    }

    //option menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.Help:
                Intent HelpIntent = new Intent(this, Activity_Help.class);
                startActivity(HelpIntent);
                return true;

            case R.id.Profile:
                Intent ProfileIntent = new Intent(this, Activity_Profile.class);
                startActivity(ProfileIntent);
                return true;

            case R.id.Settings:
                Intent SettingsIntent = new Intent(this, Activity_settings.class);
                startActivity(SettingsIntent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
        /*
        if(item.getItemId() == R.id.Help){
            Intent HelpIntent = new Intent(this,Activity_Help.class);
            startActivity(HelpIntent);
            //Toast.makeText(this, "help", Toast.LENGTH_SHORT).show();Activity_Help.class
        }

        else if(item.getItemId() == R.id.Profile){
            Intent ImproveIntent = new Intent(this,Activity_Profile.class);
            startActivity(ImproveIntent);
            // Toast.makeText(this, "Improve", Toast.LENGTH_SHORT).show();
        }
        else if(item.getItemId() == R.id.Settings){
            Intent SettingsIntent = new Intent(this,Activity_settings.class);
            startActivity(SettingsIntent);
            // Toast.makeText(this, "Improve", Toast.LENGTH_SHORT).show();
        }else {
            return super.onOptionsItemSelected(item);
        }
        return true;

         */

    void startLocService() {
        IntentFilter filter = new IntentFilter("ACT_LOC");
        registerReceiver(receiver, filter);
        Intent intent = new Intent(MainActivity.this, LocationService.class);
        startService(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //startLocService();
                } else {
                    Toast.makeText(this, "Give me permissions", Toast.LENGTH_LONG).show();
                }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
       // m1 = new googleMap;

    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }

    public class LocationBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {

            if (intent.getAction().equals("ACT_LOC")) {
                double lat = intent.getDoubleExtra("latitude", 0f);
                double longitude = intent.getDoubleExtra("longitude", 0f);
                float[] result = new float[0];
                float[] re1 = new float[0];
                float[] re2 = new float[0];
                float[] omho = new float[0];

                //float[] result1 = new float[0];
               // float[] result2 = new float[0];
               // float[] result3 = new float[0];
               // float[] result4 = new float[0];
               // float[] result5 = new float[0];

                if (mMap != null) {
                    LatLng latLng = new LatLng(lat, longitude);
                    MarkerOptions markerOptions = new MarkerOptions();
                    markerOptions.position(latLng);
                   // LatLng latLngDB1 = new LatLng(26.416147,50.017399 );//eqtelat3
                    LatLng latLngDB2 = new LatLng(26.4161492,50.0173718);//eqtelat2
                    LatLng latLngDB3 = new LatLng(26.4155985,50.0175714);//motawaa
                    LatLng latLngDB4 = new LatLng(26.4160993,50.0166903);//somran

                    MarkerOptions mo = new MarkerOptions();
                    MarkerOptions mo2 = new MarkerOptions();
                    MarkerOptions mo3 = new MarkerOptions();
                    MarkerOptions mo4 = new MarkerOptions();
                   // MarkerOptions mo5 = new MarkerOptions();

                    mo.position(latLngDB1);
                    mo2.position(latLngDB2);
                    mo3.position(latLngDB3);
                    mo4.position(latLngDB4);
                    //mo5.position(latLngDB1);

                    if (marker != null) {
                        marker.setPosition(latLng);
                        m1.setPosition(latLngDB1);
                        m2.setPosition(latLngDB2);
                        m3.setPosition(latLngDB3);
                        m4.setPosition(latLngDB4);
                    }
                    else {
                        marker = mMap.addMarker(markerOptions);
                        mMap.animateCamera(
                                CameraUpdateFactory
                                        .newLatLngZoom(latLng, 18)
                        );

                        m1 = mMap.addMarker(mo);
                        m2 = mMap.addMarker(mo);
                        m3 = mMap.addMarker(mo);
                        m4 = mMap.addMarker(mo);
                    }


                  // Marker m1 = googleMap.addMarker(new MarkerOptions().position(new LatLng(26.0,50.0))







                    if (isCir == 1) {

                    } else {

                        mMap.addCircle(

                                new CircleOptions()

                                        .center(latLngDB)
                                        .radius(20.0)
                                        .strokeWidth(1f)
                                        .strokeColor(Color.GREEN)
                                        .fillColor(Color.argb(70, 0, 50, 0))
                        );
                    }

                    if(isCir3==1){

                    }else{

                        mMap.addCircle(

                                new CircleOptions()

                                        .center(latlangDBOm)
                                        .radius(20.0)
                                        .strokeWidth(1f)
                                        .strokeColor(Color.GREEN)
                                        .fillColor(Color.argb(70, 0, 50, 0))
                        );

                    }

                    isCir = 1;
                    isCir3 = 1;

                    result = new float[10];
                    Location.distanceBetween(latLngDB.latitude, latLngDB.longitude, latLng.latitude, latLng.longitude, result);

                    omho = new float[10];
                    Location.distanceBetween(latlangDBOm.latitude,latlangDBOm.longitude, latLng.latitude, latLng.longitude,omho);

                    re1 = new float[10];
                    Location.distanceBetween(latLngDB1.latitude, latLngDB1.longitude, latLng.latitude, latLng.longitude, re1);

                    re2 = new float[10];//latLngDB1
                    Location.distanceBetween(latLngDB1.latitude, latLngDB1.longitude, latLngDB2.latitude, latLngDB2.longitude, re2);


/*
                    result2 = new float[10];
                    Location.distanceBetween(latLngDB2.latitude, latLngDB2.longitude, latLng.latitude, latLng.longitude, result2);

                    result3 = new float[10];
                    Location.distanceBetween(latLngDB3.latitude, latLngDB3.longitude, latLng.latitude, latLng.longitude, result3);

                    result4 = new float[10];
                    Location.distanceBetween(latLngDB4.latitude, latLngDB4.longitude, latLng.latitude, latLng.longitude, result4);

                    result5 = new float[10];
                    Location.distanceBetween(latLngDB5.latitude, latLngDB5.longitude, latLng.latitude, latLng.longitude, result5);

*/

                }// end of if


                if(re1[0]<5.0){

                    //infarea.setBackgroundColor(drawable/shape2.xml);
                   //int x = R.drawable.shape2;
                    infarea.setBackgroundResource(shape2);
                    infarea.setText("You Are In A Gathering Area");
                    Toast.makeText(MainActivity.this,"You Are Out of the Home At The Time Of Curfew", LENGTH_SHORT).show();

                }else{
                    infarea.setBackgroundResource(R.drawable.shape);
                    infarea.setText("You Are Not In A Gathering Area");

                }

                if(re2[0]<3)
                    {

                     if(isCir2==0) {

                         mMap.addCircle(

                                 new CircleOptions()

                                         .center(latLngDB1)
                                         .radius(10.0)
                                         .strokeWidth(1f)
                                         .strokeColor(Color.RED)
                                         .fillColor(Color.argb(70, 50, 0, 0))
                         );

                         isCir2 = 1;
                     }
                    } else {


                    }



                //this part know if you are out of home or not

                if(result[0]>20.0 ){
                    isInHome.setBackgroundResource(shape2);
                    //isInHome.setBackgroundColor(Color.RED);
                   // isInHome.setTextColor(Color.BLACK);
                    isInHome.setText("OUT HOME");
                    Toast.makeText(MainActivity.this,"You Are Out of the Home At The Time Of Curfew", LENGTH_SHORT).show();
                    //Stuation2;
                }else{
                    isInHome.setBackgroundResource(R.drawable.shape);
                    //isInHome.setBackgroundColor(Color.GREEN);
                  //  isInHome.setTextColor(Color.BLACK);
                    isInHome.setText("IN HOME");
                }

                Toast.makeText(MainActivity.this, "Latitude is: " + lat + ", Longitude is " + longitude + " , Destance is" +  result[0], Toast.LENGTH_LONG).show();


            }

        }
    }


}//end of main activity



//



//End of the project (0)(0)







//










































































































































//My Name Is Mohamed Hassan ALobaid
//Iam Programmer From Sudan ^_*

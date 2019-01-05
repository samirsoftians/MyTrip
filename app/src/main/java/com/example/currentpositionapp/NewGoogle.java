package com.example.currentpositionapp;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Timer;
import java.util.TimerTask;

public class NewGoogle extends FragmentActivity implements LocationListener
{

    double flat,tall;



    LatLng loc2;
    double la;
    double lo;

    MyDBHelper dbHelper;
    SQLiteDatabase db;
    private GoogleMap googleMap;
    private double latitude;
    private double longitude;
    private String date;
    private String time;
    private String vname;
    private String name,speed;
 
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Log.i("CPA","In the googlemap");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_google);
        Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		 latitude = Double.parseDouble(bundle.getString("lat"));

		 longitude = Double.parseDouble(bundle.getString("lng"));
		 date = bundle.getString("date");
        Log.e("newGoogle",date);
		 time = bundle.getString("time");
        Log.e("newGoogle",time);
		 vname = bundle.getString("vname");
        Log.e("newGoogle",vname);
        name=bundle.getString("location");
        Log.e("newGoogle",name);
        speed=bundle.getString("speed");
        Log.e("newGoogle",speed);
        /*SharedPreferences sharedPreferences=getSharedPreferences("map",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        name = sharedPreferences.getString("name", "");
        Log.e("ss",name);
        editor.clear();
        editor.commit();*/

        // Getting Google Play availability status
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getBaseContext());
 
        // Showing status
        if(status!=ConnectionResult.SUCCESS){ // Google Play Services are not available
 
            int requestCode = 10;
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, this, requestCode);
            dialog.show();
 
        }
        else
        { // Google Play Services are available
 /*
            // Getting reference to the SupportMapFragment of activity_main.xml
            SupportMapFragment fm = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
 
            // Getting GoogleMap object from the fragment
            googleMap = fm.getMap();
 
            // Enabling MyLocation Layer of Google Map
           // googleMap.setMyLocationEnabled(true);
 */
        	googleMap = ((SupportMapFragment) getSupportFragmentManager()
					.findFragmentById(R.id.map)).getMap();

            googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
            // Creating a LatLng object for the current location
            LatLng loc = new LatLng(latitude, longitude);
            LatLng loc2 = new LatLng(la, lo);
        //    Log.e("gmap",name);


            //************************************Shivankchi coding here *********************************

          /*  CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(loc)
                    .build();
            CameraUpdate cu = CameraUpdateFactory.newCameraPosition(cameraPosition);
            googleMap.animateCamera(cu);*/



            //*************************************Ends here *********************************************
            countdown();

//***************************Editing starts here *******************************
            /*new Timer().scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {

                    //**************************Getting data from sqlite database**********



                    dbHelper = new MyDBHelper(getApplicationContext());
                    db = dbHelper.getReadableDatabase();

                        String[] selectionArgs = {
                                vname




                        };

                    Log.e("VEHICLE",vname);

                        Cursor cursor = db.query("onlineInfo", null, "vNum=?", selectionArgs,
                                null, null, null);
                        // mt("tushar");
                        // mt("name" +cursor.getString(1));
                        cursor.move(0);
                        while (cursor.moveToNext()) {
                           *//* date.add(cursor.getString(2));
                            time.add(cursor.getString(3));
                            location.add(cursor.getString(6));*//*
                            la= Double.parseDouble(cursor.getString(4));
                            lo= Double.parseDouble(cursor.getString(5));
                           *//* speed.add(cursor.getString(8));
                            statusArray.add(cursor.getInt(7));*//*
                            cursor.moveToFirst();
                            // mt(cursor.getString(7) + " " +cursor.getString(8));
                        }
                        cursor.close();
                  //  plot();




                    }





                    //*************************Ends here ***********************************

                   *//* LatLng loc2 = new LatLng(la, lo);this was there before hand*//*


                *//**
                 *
                 *//*



            }, 0, 1000);



            *//*plot();*//*


        //******************************Commented this section************************
*/



         /*   Marker kiel = googleMap.addMarker(new MarkerOptions()
    		.position(loc)
    		.title(vname)
    		.snippet(date + "   "  +time)
    		.icon(BitmapDescriptorFactory
    				.fromResource(R.drawable.one)));
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(loc, 15));
            googleMap.animateCamera(CameraUpdateFactory.zoomTo(15));
*/



       //******************** Editing  Ends here ********************************************************







           // googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            googleMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

                @Override
                public View getInfoWindow(Marker arg0) {
                    return null;
                }

                @Override
                public View getInfoContents(Marker marker) {

                    LinearLayout info = new LinearLayout(getApplicationContext());
                    info.setOrientation(LinearLayout.VERTICAL);

                    TextView title = new TextView(getApplicationContext());
                    title.setTextColor(Color.BLACK);
                    title.setGravity(Gravity.CENTER);
                    title.setTypeface(null, Typeface.BOLD);
                    title.setText(marker.getTitle());

                    TextView snippet = new TextView(getApplicationContext());
                    snippet.setTextColor(Color.GRAY);
                    snippet.setGravity(Gravity.CENTER);
                    snippet.setText(date+"  "+time);

                    TextView snippet1 = new TextView(getApplicationContext());
                    snippet1.setTextColor(Color.GRAY);
                    snippet.setGravity(Gravity.CENTER);
                    snippet1.setText(name);

                    TextView snippet2 = new TextView(getApplicationContext());
                    snippet2.setTextColor(Color.GRAY);
                    snippet.setGravity(Gravity.CENTER);
                    snippet2.setText(speed+"KMPH");

                    info.addView(title);
                    info.addView(snippet);
                    info.addView(snippet1);
                    info.addView(snippet2);

                    return info;
                }
            });
 
          /*  // Getting LocationManager object from System Service LOCATION_SERVICE
            LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
 
            // Creating a criteria object to retrieve provider
            Criteria criteria = new Criteria();
 a
            // Getting the name of the best provider
            //String provider = locationManager.getBestProvider(criteria, true);
 
            // Getting Current Location
            Location location = locationManager.getLastKnownLocation(provider);
 
            if(location!=null){
                onLocationChanged(location);
            }
            locationManager.requestLocationUpdates(provider, 20000, 0, this);
            */






        }






    }
    @Override
    public void onLocationChanged(Location location)
    {
        Log.i("CPA","GoogleMap: Onlocation changed");


        // Getting latitude of the current location
        double latitude = 15.763544;
 
        // Getting longitude of the current location
        double longitude = 18.346343;
 
        // Creating a LatLng object for the current location
        LatLng loc = new LatLng(latitude, longitude);
        
        Marker kiel = googleMap.addMarker(new MarkerOptions()
		.position(loc)
		.title("Vehicle")
		.snippet("This is your vehicle")
		.icon(BitmapDescriptorFactory
				.fromResource(R.drawable.blue_marker)));
        
//googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(loc, 15));
 

   googleMap.animateCamera(CameraUpdateFactory.zoomTo(15));
 
        // Setting latitude and longitude in the TextView tv_location
     //   tvLocation.setText("Latitude:" +  latitude  + ", Longitude:"+ longitude );
 
    }
 
    @Override
    public void onProviderDisabled(String provider)
    {

          //TODO Auto-generated method stub

    }
 
    @Override
    public void onProviderEnabled(String provider)
    {

        //TODO Auto-generated method stub

    }
 
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras)
    {

        // TODO Auto-generated method stub
    }


    public void plot()
    {

        LatLng loc2 = new LatLng(la, lo);
        Marker kiel = googleMap.addMarker(new MarkerOptions()
                .position(loc2)
                .title(vname)
                .snippet(date + "   "  +time)
                .icon(BitmapDescriptorFactory
                        .fromResource(R.drawable.one)));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(loc2, 15));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(15));
    }

    public void countdown()
    {

         new CountDownTimer(900000,1000) {

            @Override
            public void onTick(long millisUntilFinished) {


                dbHelper = new MyDBHelper(getApplicationContext());
                db = dbHelper.getReadableDatabase();

                String[] selectionArgs = {
                        vname
                };
//38 40

                Cursor cursor = db.query("onlineInfo", null, "vNum=?", selectionArgs,
                        null, null, null);
                // mt("tushar");
                // mt("name" +cursor.getString(1));
                cursor.move(0);
                while (cursor.moveToNext()) {
                    la= Double.parseDouble(cursor.getString(4));
                    lo= Double.parseDouble(cursor.getString(5));
                    cursor.moveToFirst();



                    Toast.makeText(NewGoogle.this, String.valueOf(la), Toast.LENGTH_SHORT).show();

                    Toast.makeText(NewGoogle.this, String.valueOf(lo), Toast.LENGTH_SHORT).show();

                    Log.e("GANPATI", String.valueOf(la))   ;
                 Log.e("GANPATI2", String.valueOf(lo));
                    // mt(cursor.getString(7) + " " +cursor.getString(8));
                }
                cursor.close();


                Toast.makeText(NewGoogle.this, "map is called ", Toast.LENGTH_SHORT).show();

                flat=la;
                tall=lo;

                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(flat+.1000,tall+.1000), 12));

                    googleMap.addMarker(new MarkerOptions().position(new LatLng(flat+.25,tall+.25)).title("Info")
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.one)));


            }

            @Override
            public void onFinish() {
                // TODO Auto-generated method stub
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(la,lo), 12));
            }
        }.start();
    }

  
}
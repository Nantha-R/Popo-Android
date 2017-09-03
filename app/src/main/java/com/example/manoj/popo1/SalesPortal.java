package com.example.manoj.popo1;

import android.Manifest;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class SalesPortal extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,LocationListener {




    Boolean gps_enable=true;
    Boolean network_enable=true;
    Location location;
    public double latitude;
    public double longitude;
    Geocoder geocoder;
    List<Address> addresses;
    LocationManager locationManager;
    StringBuffer s1;
    String sid;
    productlist pro1=new productlist();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_portal);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent=getIntent();
        sid=intent.getStringExtra("id");
        Bundle bundle=new Bundle();
        bundle.putString("sid",sid);
        pro1.setArguments(bundle);
        FragmentManager fragmentManager1=getFragmentManager();
        fragmentManager1.beginTransaction().replace(R.id.content_frame,pro1).commit();
       // Intent intent=getIntent();
       /// id=intent.getStringExtra("id");
      //  Toast.makeText(SalesPortal.this,id,Toast.LENGTH_SHORT).show();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Updating location...", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();




                locationManager=(LocationManager)getSystemService(Context.LOCATION_SERVICE);
                if (ActivityCompat.checkSelfPermission(SalesPortal.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(SalesPortal.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(),"no permission",Toast.LENGTH_SHORT).show();
                    return;
                }


                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,SalesPortal.this);
               location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

                latitude = location.getLatitude();
                longitude = location.getLongitude();

                //Toast.makeText(SalesPortal.this,String.valueOf(latitude),Toast.LENGTH_SHORT).show();
               // Toast.makeText(SalesPortal.this,String.valueOf(longitude),Toast.LENGTH_SHORT).show();


                geocoder = new Geocoder(SalesPortal.this, Locale.getDefault());

                try {
                    addresses = geocoder.getFromLocation(latitude, longitude, 1);
                    String address0 = addresses.get(0).getAddressLine(0);
                    String address1 = addresses.get(0).getAddressLine(1);// If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                    String city = addresses.get(0).getLocality();
                    String state = addresses.get(0).getAdminArea();
                    String country = addresses.get(0).getCountryName();
                    String postalCode = addresses.get(0).getPostalCode();
                    String knownName = addresses.get(0).getFeatureName();
                    String address= address0+" ,"+address1;

                    //Toast.makeText(SalesPortal.this,address,Toast.LENGTH_SHORT).show();

                    String lat=String.valueOf(latitude);
                    String lon=String.valueOf(longitude);

                    HashMap post=new HashMap();
                    post.put("latitude",lat);
                    post.put("longitude",lon);
                    post.put("address",address);
                    post.put("sid",sid);


                    PostResponseAsyncTask task=new PostResponseAsyncTask(SalesPortal.this, post, new AsyncResponse() {
                        @Override
                        public void processFinish(String s) {
                            if(s.contains("success"))
                            {
                                Toast.makeText(SalesPortal.this,"updated successfully",Toast.LENGTH_SHORT).show();

                            }



                        }
                    });
                     task.execute("http://itkalam.cf/popo/packetapp.php");


                    // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                } catch (Exception e) {

                }









            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sales_portal, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(  MenuItem item1) {
        // Handle navigation view item clicks here.
        FragmentManager fragmentManager=getFragmentManager();
        int id = item1.getItemId();
        Bundle bundle=new Bundle();
        bundle.putString("sid",sid);
        salesaccount sa=new salesaccount();
        sa.setArguments(bundle);
        message_all msg=new message_all();
        msg.setArguments(bundle);
        productlist pro=new productlist();
        pro.setArguments(bundle);
        status sta=new status();
        sta.setArguments(bundle);


        if (id == R.id.nav_first)
        {

            fragmentManager.beginTransaction().replace(R.id.content_frame,pro).commit();
        }
        else if (id == R.id.nav_second)
        {
            fragmentManager.beginTransaction().replace(R.id.content_frame,sta).commit();
        }
        else if (id == R.id.nav_third)
        {
            fragmentManager.beginTransaction().replace(R.id.content_frame,sa).commit();
        }
        else if (id == R.id.nav_fourth)
        {
            fragmentManager.beginTransaction().replace(R.id.content_frame,msg).commit();

        }
        else if (id == R.id.nav_five)
        {
            fragmentManager.beginTransaction().replace(R.id.content_frame,new about()).commit();
        }
        else if (id == R.id.nav_six)
        {
            Intent intent=new Intent(this,Home1.class);
            startActivity(intent);
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}

package com.example.manoj.popo1;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ActivityChooserView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import org.json.JSONObject;

import java.util.HashMap;
//import java.util.jar.Manifest;

public class ProductDetails extends AppCompatActivity {

   String id;
TextView trname,temail,tphn,taddr,tdate;
    String rname,email,phn,addr,date,scity,rcity;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        TextView pid=(TextView)findViewById(R.id.textViewPID);
        trname=(TextView)findViewById(R.id.textViewRNaame);
        temail=(TextView)findViewById(R.id.textViewEmail);
        tphn=(TextView)findViewById(R.id.textViewPhn);
        taddr=(TextView)findViewById(R.id.textViewAddr);
        tdate=(TextView)findViewById(R.id.textViewDate);



        Intent in=getIntent();
         id=in.getStringExtra("pid");
        //Toast.makeText(ProductDetails.this,id,Toast.LENGTH_LONG).show();
        pid.setText(id);

        HashMap postdata=new HashMap();
        postdata.put("pid",id);


        PostResponseAsyncTask task1=new PostResponseAsyncTask(this, postdata, new AsyncResponse() {
            @Override
            public void processFinish(String s) {

                //Toast.makeText(ProductDetails.this,s,Toast.LENGTH_SHORT).show();

                try {
                    JSONObject json = new JSONObject(s);
                    JSONObject a = json.getJSONObject("data");
                    rname=(String)a.get("r_name");
                    email=(String)a.get("r_mail");
                    phn=(String)a.get("r_number");
                    addr=((String)a.get("flat")+","+(String)a.get("street")+","+(String)a.get("area")+","+(String)a.get("city")+","+(String)a.get("state"));
                    rcity=(String)a.get("city");
                    date=(String)a.get("d_a");
                    scity=(String)a.get("s_city");

                 //   Toast.makeText(ProductDetails.this,s,Toast.LENGTH_LONG).show();


                }
                catch(Exception e)
                {

                }




                trname.setText(rname);
                temail.setText(email);
                tphn.setText(phn);
                taddr.setText(addr);
                tdate.setText(date);

            }
        });
        task1.execute("http://itkalam.cf/popo/productdetailsapp.php");



    }
    public void updateStatus(View view)
    {
        //pass the product id and status to productstatus.class
        Intent in=new Intent(this,ProductStatus.class);
        Bundle bundle=new Bundle();
        bundle.putString("pid",id);
        bundle.putString("rcity",rcity);
        bundle.putString("scity",scity);

        in.putExtras(bundle);
        startActivity(in);
    }
    public void map(View view)
    {

        Uri mapUri = Uri.parse("geo:0,0?q=" + Uri.encode(addr));
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, mapUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);




        //create a map for connecting two locations
        //get two address pass it as bundled intent
        //start next intent

    }
    public void call(View view)
    {
        //get phoneno
        //make a call
        Intent i=new Intent(Intent.ACTION_CALL);
        i.setData(Uri.parse("tel:"+phn));

        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE)!=PackageManager.PERMISSION_GRANTED)
        {
            Toast.makeText(ProductDetails.this,"PLEASE GRANT PERMISSION TO CALL",Toast.LENGTH_LONG).show();
        }
        else
        {
          startActivity(i);
        }



    }
}

package com.example.manoj.popo1;

/**
 * Created by Manoj on 3/29/2017.
 */
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.StringTokenizer;

public class utab2time extends Fragment{

    Double lat,lon;
    String sid,raddress,pid;
    String temp;
    TextView tv,dist;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.utab2map, container, false);
        tv=(TextView)rootView.findViewById(R.id.textView14);
        String s=this.getArguments().getString("s");
        dist=(TextView)rootView.findViewById(R.id.textView41);
        s=s.substring(7);

        try {


            JSONObject json = new JSONObject(s);
            JSONObject a = json.getJSONObject("data");
            sid = (String) a.get("sid");
            pid= (String) a.get("pid");
            Toast.makeText(getActivity(),pid,Toast.LENGTH_LONG).show();
           raddress = (String) a.get("flat") + "," + (String) a.get("street") + "," + (String) a.get("area") + "," + (String) a.get("city");
          //  raddress = (String) a.get("city")+ "," + (String) a.get("state");

        }

        catch(Exception e)
        {

        }



        HashMap postdata=new HashMap();
        postdata.put("sid",sid);
        postdata.put("pid",pid);

        PostResponseAsyncTask task1=new PostResponseAsyncTask(getActivity(), postdata, new AsyncResponse() {
            @Override
            public void processFinish(String s) {
                temp=s;
                tv.setText(s);


                HashMap a=new HashMap();
                a.put("sales",temp);
              //  Toast.makeText(getActivity(),temp,Toast.LENGTH_LONG).show();
                a.put("receiver",raddress);
                PostResponseAsyncTask tsk1=new PostResponseAsyncTask(getActivity(),a, new AsyncResponse() {
                    @Override
                    public void processFinish(String s) {

                       // Toast.makeText(getActivity(),s,Toast.LENGTH_LONG).show();
                        dist.setText(s);

                    }
                });

                tsk1.execute("http://itkalam.cf/popo/utab2time2app.php");


//https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins=Washington,DC&destinations=New+York+City,NY&key=YOUR_API_KEY
            }
        });
            task1.execute("http://itkalam.cf/popo/utab2timeapp.php");


        Button b1=(Button)rootView.findViewById(R.id.buttonmap);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Uri mapUri = Uri.parse("geo:0,0?q=" + Uri.encode(tv.getText().toString()));
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, mapUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
                            }
        });







        return rootView;
    }
}

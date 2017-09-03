package com.example.manoj.popo1;

/**
 * Created by Manoj on 3/29/2017.
 */
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import org.json.JSONObject;
import org.json.JSONException;
import java.util.HashMap;

public class utab3time extends Fragment{



    String sid;
    TextView name,number,location;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.utab3details, container, false);

        name=(TextView)rootView.findViewById(R.id.textView29);
        number=(TextView)rootView.findViewById(R.id.textView30);
        location=(TextView)rootView.findViewById(R.id.textView32);

        String s=this.getArguments().getString("s");
        //Toast.makeText(getContext(),s,Toast.LENGTH_SHORT).show();
        s=s.substring(7);

        try {


            JSONObject json=new JSONObject(s);
            JSONObject a=json.getJSONObject("data");
            sid=(String)a.get("sid");



            HashMap postdata=new HashMap();
            postdata.put("sid",sid);

            PostResponseAsyncTask task1=new PostResponseAsyncTask(getContext(), postdata, new AsyncResponse() {
                @Override
                public void processFinish(String s) {
                    try {
                        JSONObject json = new JSONObject(s);
                        JSONObject a=json.getJSONObject("data");
                        String name1,num1,location1;
                        name1=(String)a.get("name");
                        num1=(String )a.get("num");
                        location1=(String)a.get("location");

                        name.setText(name1);
                        number.setText(num1);
                        location.setText(location1);


                    }
                    catch (Exception e)
                    {

                    }



                }
            });
            task1.execute("http://itkalam.cf/popo/utab3timeapp.php");



        }
        catch(Exception e)
        {

        }


        return rootView;
    }
}

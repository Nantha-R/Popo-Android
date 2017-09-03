package com.example.manoj.popo1;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by Manoj on 3/26/2017.
 */

public class salesaccount extends Fragment {
    View view;
    TextView name,number,location;
    String name1,num1,location1,id;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        id=this.getArguments().getString("sid");

        view=inflater.inflate(R.layout.fourthlayout,container,false);
        Button edit=(Button)view.findViewById(R.id.button);
        TextView sid=(TextView)view.findViewById(R.id.textView11);
        name=(TextView)view.findViewById(R.id.textView10);
        number=(TextView)view.findViewById(R.id.textView12);
        location=(TextView)view.findViewById(R.id.textView13);


        sid.setText(""+id);

        HashMap postdata=new HashMap();
        postdata.put("sid",id);

        PostResponseAsyncTask task1=new PostResponseAsyncTask(getActivity(), postdata, new AsyncResponse() {
            @Override
            public void processFinish(String s) {
                try {
                    JSONObject json = new JSONObject(s);
                    JSONObject a=json.getJSONObject("data");

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



        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle=new Bundle();
                bundle.putString("name",name1);
                bundle.putString("id",id);
                bundle.putString("num",num1);
                bundle.putString("location",location1);


                Intent in=new Intent(getActivity(),EditAccount.class);
                in.putExtra("key",bundle);
                startActivity(in);
            }
        });
        return view;
    }
}

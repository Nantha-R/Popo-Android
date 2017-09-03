package com.example.manoj.popo1;

/**
 * Created by Manoj on 3/29/2017.
 */
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import org.json.JSONObject;

import java.util.HashMap;

public class utab1time extends Fragment {

    int pos,pos1;

    private Button confirm,d;

    String date,time,id;
    Spinner sp;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.utab1time, container, false);
        String s=this.getArguments().getString("s");
        //Toast.makeText(getContext(),s,Toast.LENGTH_SHORT).show();

        sp=(Spinner)rootView.findViewById(R.id.spinner);
        final TextView date1=(TextView)rootView.findViewById(R.id.editText9);
        confirm=(Button)rootView.findViewById(R.id.button4);


        s=s.substring(7);
        try {
            JSONObject json = new JSONObject(s);
            JSONObject a = json.getJSONObject("data");
             date = (String)a.get("d_a");
            time=(String)a.get("t_a");
            id=(String)a.get("pid");


            date1.setText(date);
            pos=Integer.parseInt(time);

            pos-=9;
            sp.setSelection(pos);


        }
        catch(Exception e)
        {

        }

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pos1=sp.getSelectedItemPosition();
                pos1+=9;
                String t=String.valueOf(pos1);

                HashMap post=new HashMap();
                post.put("id",id);
                post.put("date",date1.getText().toString().trim());
                post.put("time",t);

                PostResponseAsyncTask task1=new PostResponseAsyncTask(getContext(), post, new AsyncResponse() {
                    @Override
                    public void processFinish(String s) {

                            Toast.makeText(getContext(),"Your delivery details have been updated successfully",Toast.LENGTH_SHORT).show();
                    }
                });
                task1.execute("http://itkalam.cf/popo/utab1timeapp.php");


            }
        });
        



        return rootView;
    }
}

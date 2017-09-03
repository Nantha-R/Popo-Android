package com.example.manoj.popo1;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * Created by Manoj on 3/26/2017.
 */

public class status extends Fragment {
    View view;
    String[] prod1=new String[100];
  //  String[] productArray=new String[100];
    int i,j;
    String p;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        view=inflater.inflate(R.layout.secondlayout,container,false);
        String id1=getArguments().getString("sid");
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("dd-MM-yyyy");
        String strDate = mdformat.format(calendar.getTime());

    HashMap postdata=new HashMap();

        postdata.put("sid",id1);

        postdata.put("date",strDate);
        PostResponseAsyncTask task=new PostResponseAsyncTask(getActivity(), postdata, new AsyncResponse() {
            @Override
            public void processFinish(String s) {


              //Toast.makeText(getActivity(),"successfully updated",Toast.LENGTH_SHORT).show();



                StringTokenizer st1=new StringTokenizer(s,":");

                while(st1.hasMoreTokens()) {
                    prod1[i] = "ID:"+st1.nextToken().toString();
                 //   Toast.makeText(getActivity(),prod1[i],Toast.LENGTH_SHORT).show();
                    i++;
                }

                Bundle bundle=new Bundle();
                bundle.putStringArray("st",prod1);
                fragstatus fs=new fragstatus();
                fs.setArguments(bundle);
                FragmentManager fragmentManager=getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame,fs).commit();

            }
        });
        task.execute("http://itkalam.cf/popo/statusapp.php");





        return view;
    }
}


/*
                Bundle bundle=new Bundle();
                bundle.putStringArray("st",prod1);
                fragstatus fs=new fragstatus();
                fs.setArguments(bundle);
                FragmentManager fragmentManager=getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame,fs).commit(); */
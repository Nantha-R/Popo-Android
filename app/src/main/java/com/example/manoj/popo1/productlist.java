package com.example.manoj.popo1;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.logging.Handler;
import java.util.zip.Inflater;

/**
 * Created by Manoj on 3/26/2017.
 */

public class productlist extends Fragment {
    View view;
    String p="",time="";
    StringTokenizer st;
    String s;
    String[] prod1=new String[50];
    String[] time1=new String[50];
     int i=0;
     int j=0;
    public String[] productArray =new String [50];

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {



        String id1=getArguments().getString("sid");
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("dd-MM-yyyy");
        String strDate = mdformat.format(calendar.getTime());

        SimpleDateFormat time2=new SimpleDateFormat("HH:mm:ss");
        String strTime=time2.format(calendar.getTime());

       // Toast.makeText(getActivity(),strTime,Toast.LENGTH_LONG).show();


       // strDate="25-04-2017";


      //  final String[] productArray1 = {"p1","p2","p3","p4"};





        HashMap postdata=new HashMap();
        postdata.put("sid",id1);
        postdata.put("date",strDate);

        PostResponseAsyncTask task1=new PostResponseAsyncTask(getActivity(), postdata, new AsyncResponse() {
            @Override
            public void processFinish(String temp) {


                s=temp;

                st = new StringTokenizer(s,";");


                p = st.nextToken();
                time = st.nextToken();

                StringTokenizer st1=new StringTokenizer(p,":");

                while(st1.hasMoreTokens()) {
                    prod1[i] = st1.nextToken().toString();
                    //Toast.makeText(getActivity(),prod1[i],Toast.LENGTH_SHORT).show();
                    i++;
                }
                st=new StringTokenizer(time,":");

                while(st.hasMoreTokens())
                {
                    time1[j]=st.nextToken().toString();


                  if(time1[j].equals("9"))
                        time1[j]="9AM-10AM";
                    else if(time1[j].equals("8"))
                        time1[j]="8AM-9AM";
                    else if(time1[j].equals("10"))
                        time1[j]="10AM-11AM";
                    else if(time1[j].equals("11"))
                        time1[j]="11AM-12PM";
                    else if(time1[j].equals("12"))
                        time1[j]="12PM-1PM";
                    else if(time1[j].equals("13"))
                        time1[j]="1PM-2PM";
                    else if(time1[j].equals("14"))
                        time1[j]="2PM-3PM";
                    else if(time1[j].equals("15"))
                        time1[j]="3PM-4PM";
                    else if(time1[j].equals("16"))
                        time1[j]="4PM-5PM";

                    //Toast.makeText(getActivity(),time1[j],Toast.LENGTH_SHORT).show();
                    j++;

                }

                for(i=0;i<j;i++)
                {
                    //productArray1[i]="shhjs";
                    productArray[i]=String.format("ID: %s    Time:%s ",prod1[i],time1[i]);
                    //Toast.makeText(getActivity(),productArray[i],Toast.LENGTH_SHORT).show();

                }

                Bundle bundle=new Bundle();
                bundle.putStringArray("pa",productArray);
                bundle.putStringArray("proid",prod1);
                fragproductlist fp=new fragproductlist();
                fp.setArguments(bundle);
                FragmentManager fragmentManager=getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame,fp).commit();

              //  Intent in=new Intent(getActivity(),productlist1.class);
                //in.putExtras(bundle);
                //startActivity(in);



            }

        });


        task1.execute("http://itkalam.cf/popo/productlistapp.php");


        view=inflater.inflate(R.layout.firstlayout,container,false);

      //  Bundle bundle=new Bundle();
        //bundle.putStringArray("key",productArray);
       ;



       //Toast.makeText(getActivity(),productArray[2],Toast.LENGTH_SHORT).show();



        //get products id on date sort by time ,display id time location
        //get product id,rec name,e mail,phoneno,address,expected date



        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                //get entire product details and pass it into next activity productdetails.java

                    Intent in=new Intent(getActivity(),ProductDetails.class);
                    in.putExtra("pid",productArray1[i]);
                    startActivity(in);

            }
        });*/


        return view;
    }
}

package com.example.manoj.popo1;
import android.Manifest;
import android.Manifest.permission;
import android.app.Fragment;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
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

public class message_all extends Fragment {
    View view;
    EditText message;
    Button send;
    String[] mobile=new String[100];
    int i=0,i1=0;
    String msg="The package addressed to ___ from ___ hub to address ___ is estimated to arrive today by ____ hours";
    String[] rname=new String[50];
    String[] hub=new String[50];
    String[] ra=new String[50];
    String[] ta=new String[50];
    String[] id1=new String[100];
    String hubaddress;
    String sname1;
    String snum1;
    SmsManager sms;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        String id=getArguments().getString("sid");


        view=inflater.inflate(R.layout.fifthlayout,container,false);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("dd-MM-yyyy");
        String strDate = mdformat.format(calendar.getTime());
        send=(Button)view.findViewById(R.id.button3);
        message=(EditText)view.findViewById(R.id.editText5);
        message.setText(msg);

        HashMap postdata=new HashMap();
        postdata.put("sid",id);
        postdata.put("date",strDate);

        PostResponseAsyncTask task1=new PostResponseAsyncTask(getActivity(), postdata, new AsyncResponse() {
            @Override
            public void processFinish(String s) {



                StringTokenizer st=new StringTokenizer(s,";");
                String id=st.nextToken();
                String mob=st.nextToken();
                String name=st.nextToken();
                hubaddress=st.nextToken();
                String address=st.nextToken();
                String time=st.nextToken();
                String sname=st.nextToken();
                String snum=st.nextToken();


                st=new StringTokenizer(id,":");
                while(st.hasMoreTokens())
                {
                    id1[i]=st.nextToken();

                    i++;
                }
                   i=0;
                st=new StringTokenizer(mob,":");
                while(st.hasMoreTokens())
                {
                    mobile[i]=st.nextToken();

                    i++;
                }
                i=0;
                st=new StringTokenizer(name,":");
                while(st.hasMoreTokens())
                {
                    rname[i]=st.nextToken();
                    i++;
                }
                i=0;

                st=new StringTokenizer(address,":");
                while(st.hasMoreTokens())
                {
                    ra[i]=st.nextToken();
                    i++;
                }
                i=0;

                st=new StringTokenizer(time,":");
                while(st.hasMoreTokens())
                {
                    ta[i]=st.nextToken();
                    i++;
                }

                st=new StringTokenizer(hubaddress,":");
                while(st.hasMoreTokens())
                {
                    hubaddress=st.nextToken();

                }
                st=new StringTokenizer(sname,":");
                while(st.hasMoreTokens())
                {
                    sname1=st.nextToken();

                                    }


                st=new StringTokenizer(snum,":");
                while(st.hasMoreTokens())
                {
                    snum1=st.nextToken();

                }




            }
        });
        task1.execute("http://itkalam.cf/popo/messageallapp.php");



        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getActivity(),"Sending Message...",Toast.LENGTH_SHORT).show();
                if(ContextCompat.checkSelfPermission(getActivity(), permission.SEND_SMS)!= PackageManager.PERMISSION_GRANTED)
                {
                    int m=0;
                    ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.SEND_SMS},m);
                }
                sms=SmsManager.getDefault();
              /*  for(int j=0;j<i;j++)
                {
                        Toast.makeText(getActivity(),ta[j],Toast.LENGTH_SHORT).show();
                     msg="The package addressed to "+rname[j]+" has been dispatched from "+hubaddress+"to address "+ra[j]+" may arrive today by "+ta[j]+":00 hours";

                    sms.sendTextMessage(mobile[j],null,msg,null,null);



                }*/
               msg=String.format("Your package is out for delivery.For further queries contact postman  %s - %s ",sname1,snum1);
                for(int j=0;j<i;j++)
                {

                    sms.sendTextMessage(mobile[j],null,msg,null,null);


                }
                Toast.makeText(getActivity(),"Message Sent",Toast.LENGTH_SHORT).show();






            }
        });

        return view;
    }
}

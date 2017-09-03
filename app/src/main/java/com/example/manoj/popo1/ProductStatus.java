package com.example.manoj.popo1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class ProductStatus extends AppCompatActivity {
String pid;
    TextView textView;
    Spinner sp;
    int pos;
    String p,rcity,scity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //SalesPortal obj=new SalesPortal();
        //String id=obj.id;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_status);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("dd-MM-yyyy");
        final String strDate = mdformat.format(calendar.getTime());

        SimpleDateFormat time2=new SimpleDateFormat("HH:mm:ss");
        final String strTime=time2.format(calendar.getTime());

        Bundle b=this.getIntent().getExtras();
        pid=b.getString("pid");
        rcity=b.getString("rcity");
        scity=b.getString("scity");




        //Intent in=getIntent();
       //pid= in.getStringExtra("pid");
        textView=(TextView)findViewById(R.id.textView35);
        sp=(Spinner)findViewById(R.id.spinner2);
        textView.setText(pid);

//get product id and product status
        Button update=(Button)findViewById(R.id.button8);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pos=sp.getSelectedItemPosition();
p=String.valueOf(pos);

                HashMap postdata=new HashMap();
                postdata.put("pos",p);
                postdata.put("sid",pid);
                PostResponseAsyncTask task=new PostResponseAsyncTask(ProductStatus.this, postdata, new AsyncResponse() {
                    @Override
                    public void processFinish(String s) {


                        Toast.makeText(ProductStatus.this,"successfully updated " +scity,Toast.LENGTH_SHORT).show();

                    }
                });
                task.execute("http://itkalam.cf/popo/productstatusapp.php");


                  if(pos==1)
                {

                    HashMap postdata1=new HashMap();
                    postdata1.put("sdate",strDate);
                    postdata1.put("stime",strTime);
                    postdata1.put("scity",scity);
                    postdata1.put("rcity",rcity);
                    postdata1.put("pid",pid);

                    Toast.makeText(ProductStatus.this,strTime+strDate+pid,Toast.LENGTH_SHORT).show();

                    PostResponseAsyncTask task1=new PostResponseAsyncTask(ProductStatus.this, postdata1, new AsyncResponse() {
                        @Override
                        public void processFinish(String s) {


                            Toast.makeText(ProductStatus.this,s,Toast.LENGTH_SHORT).show();

                        }
                    });
                    task1.execute("http://itkalam.cf/popo/a.php");




                }

                //change the status of the products id
                //after update move back to list view,,,productlist.class




            }
        });
    }
}

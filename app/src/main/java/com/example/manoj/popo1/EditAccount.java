package com.example.manoj.popo1;

import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import java.util.HashMap;

public class EditAccount extends AppCompatActivity {

    String name,location,number,id;
    EditText name1,number1,location1;
    TextView id1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);

        name1=(EditText)findViewById(R.id.editText6);
        id1=(TextView)findViewById(R.id.idtextview);
        number1=(EditText)findViewById(R.id.editText7);
        location1=(EditText)findViewById(R.id.editText8);

        Intent in=getIntent();
        Bundle bundle=getIntent().getBundleExtra("key");
        name=bundle.getString("name");
        location=bundle.getString("location");
        id=bundle.getString("id");
        number=bundle.getString("num");

        name1.setText(name);
        id1.setText(id);
        number1.setText(number);
        location1.setText(location);


    }
    public void saveOnClick(View view)
    {
        //sql query to save
        HashMap postdata=new HashMap();
        postdata.put("id",id1.getText().toString().trim());
        postdata.put("name",name1.getText().toString().trim());
        postdata.put("number",number1.getText().toString().trim());
        postdata.put("location",location1.getText().toString().trim());

        PostResponseAsyncTask task1=new PostResponseAsyncTask(EditAccount.this, postdata, new AsyncResponse() {
            @Override
            public void processFinish(String s) {

                if(s.contains("success"))
                {
                    Toast.makeText(EditAccount.this,"Success",Toast.LENGTH_SHORT).show();
                  //  Intent in=new Intent(EditAccount.this,SalesPortal.class);
                   // startActivity(in);
                }
            }
        });
        task1.execute("http://itkalam.cf/popo/editaccountapp.php");




       // FragmentManager fragmentManager=getFragmentManager();
       // fragmentManager.beginTransaction().replace(R.id.content_frame,new salesaccount()).commit();

    }


}

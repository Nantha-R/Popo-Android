package com.example.manoj.popo1;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;

import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import java.util.HashMap;


public class tab1sales extends Fragment{

    private EditText salespersonid,password;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {





        View rootView = inflater.inflate(R.layout.saleslogin, container, false);

        Button b1=(Button)rootView.findViewById(R.id.button2);
        salespersonid=(EditText)rootView.findViewById(R.id.editText3);
        password=(EditText)rootView.findViewById(R.id.editText4);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String salesid=salespersonid.getText().toString().trim();
                String password1=password.getText().toString().trim();

                //check for username and password existence
                //pass the saleperson id

                if(salesid.equals("")||password1.equals(""))
                {
                    Toast.makeText(getContext(),"Enter your Username and Password",Toast.LENGTH_SHORT).show();

                }
                else {
                    HashMap postdata = new HashMap();
                    postdata.put("eid", salesid);
                    postdata.put("epass", password1);

                    PostResponseAsyncTask task1 = new PostResponseAsyncTask(getContext(), postdata, new AsyncResponse() {
                        @Override
                        public void processFinish(String s) {

                            if(s.contains("success"))
                            {
                                Intent i=new Intent(getContext(),SalesPortal.class);
                                i.putExtra("id",salesid);
                                startActivity(i);
                            }
                            else
                                if(s.contains("Invalid Login"))
                                {
                                    Toast.makeText(getContext(),s,Toast.LENGTH_SHORT).show();


                                }

                        }
                    });
                    task1.execute("http://itkalam.cf/popo/tab1salesapp.php");
                    ;
                }
                //craete shared preferences





            }
        });


        return rootView;

    }


}

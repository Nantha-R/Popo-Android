package com.example.manoj.popo1;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.MainActivity;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import java.util.HashMap;


public class tab2user extends Fragment{

    private EditText productid,phoneno;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.userlogin, container, false);

        Button b1=(Button)rootView.findViewById(R.id.userbutton);
        productid=(EditText)rootView.findViewById(R.id.editText3);
        phoneno=(EditText)rootView.findViewById(R.id.phnnumber);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               // Intent in=new Intent(getActivity(),UserPortal.class);
               // startActivity(in);

                String prodid=productid.getText().toString().trim();
                String phnno=phoneno.getText().toString().trim();

                if(prodid.equals("")&&phnno.equals(""))
                {
                    Toast.makeText(getContext(),"Enter the product id and phoneno of the receiver",Toast.LENGTH_SHORT).show();
                }
                else {
                    //check for prodid and phnno

                    HashMap postdata = new HashMap();
                    postdata.put("id", prodid);
                    postdata.put("phone", phnno);

                    PostResponseAsyncTask task1 = new PostResponseAsyncTask(getContext(), postdata, new AsyncResponse() {
                        @Override
                        public void processFinish(String s) {



                            if (s.contains("success")) {
                                //Toast.makeText(getContext(),s,Toast.LENGTH_SHORT).show();
                               Intent i=new Intent(getActivity(),UserPortal.class);
                                i.putExtra("s",s);
                              startActivity(i);




                            } else if (s.contains("failure")) {

                                Toast.makeText(getContext(),"Invalid Details",Toast.LENGTH_SHORT).show();
                            }


                        }
                    });
                    task1.execute("http://itkalam.cf/popo/tab2userapp.php");

                }
                //pass on db information like datae time onto uerportal.class utab1.time
                //pass on location of last postmen utab2time
                //pass on name phone locality of salesmen to utab3time



            }
        });

        return rootView;
    }


}

package com.example.manoj.popo1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class productlist1 extends AppCompatActivity {
String[] pa=new String[100];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productlist1);
        Bundle b=this.getIntent().getExtras();
         pa=b.getStringArray("pa");

        ListAdapter la=new customadapter(this,pa);
        ListView listview=(ListView)findViewById(R.id.plistview2);
        listview.setAdapter(la);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(productlist1.this, "Clicked at positon = " + position, Toast.LENGTH_SHORT).show();

            }

        });



     //  ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.activity_list_item,pa);

       // ListView listView=(ListView)findViewById(R.id.PlistView);
        //listView.setAdapter(arrayAdapter);

    }
}

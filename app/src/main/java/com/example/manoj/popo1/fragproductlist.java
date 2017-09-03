package com.example.manoj.popo1;

/**
 * Created by Manoj on 4/1/2017.
 */
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class fragproductlist extends Fragment{
    View view;

    String[] id1=new String[100];
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        view=inflater.inflate(R.layout.productfragment,container,false);

        Bundle b= getArguments();
        String[] pa=b.getStringArray("pa");
     id1=b.getStringArray("proid");

        //Toast.makeText(getActivity(),pa[2],Toast.LENGTH_LONG).show();

        ListAdapter la=new customadapter(getActivity(),pa);
        ListView listview=(ListView)view.findViewById(R.id.plistview2);
        listview.setAdapter(la);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Toast.makeText(getActivity(), "Clicked at positon = " + position, Toast.LENGTH_SHORT).show();

                Intent in=new Intent(getActivity(),ProductDetails.class);

                in.putExtra("pid",id1[position]);
                startActivity(in);

            }

        });
       // ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(inflater.getContext(),android.R.layout.simple_list_item_1,pa);

        //ListView listView=(ListView)view.findViewById(R.id.plistview2);
        //listView.setAdapter(arrayAdapter);

        return view;
    }
}

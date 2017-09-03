package com.example.manoj.popo1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by Manoj on 4/2/2017.
 */

public class fragstatus extends Fragment {
    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        view=inflater.inflate(R.layout.statusfragment,container,false);
     Bundle b= getArguments();
    String[] pa=b.getStringArray("st");
  //  Toast.makeText(getActivity(),pa[1],Toast.LENGTH_LONG).show();
        ListAdapter la=new customadapter(getActivity(),pa);
        ListView listview=(ListView)view.findViewById(R.id.liststatus);
        listview.setAdapter(la);




        return view;
    }
}

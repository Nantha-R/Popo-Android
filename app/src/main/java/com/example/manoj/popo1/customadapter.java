package com.example.manoj.popo1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Nanthakumar on 26-03-2017.
 */

class customadapter extends ArrayAdapter<String> {


    public customadapter(Context context,String[] foods) {
        super(context,R.layout.customlistview,foods);
        //
        // super(context,R.layout.customlistview,food);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater li=LayoutInflater.from(getContext());
        View refview=li.inflate(R.layout.customlistview,parent,false);
        String helloitem=getItem(position);

        TextView text1=(TextView)refview.findViewById(R.id.textView37);

        text1.setText(helloitem);


        return refview;


    }
}

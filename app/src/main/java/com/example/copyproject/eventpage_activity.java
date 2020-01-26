package com.example.copyproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class eventpage_activity extends AppCompatActivity {
   private ImageView eventimage;
   private TextView eventname,eventdate,eventlocation,eventdetails;

    private String eventid, name, location,date, time, url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_detail);
        this.setTitle("EL EVENTO");


        eventimage=findViewById(R.id.event_pict);
        eventname=findViewById(R.id.event_name);
        eventdate=findViewById(R.id.event_date);
        eventlocation=findViewById(R.id.event_location);
        eventdetails=findViewById(R.id.event_details);


        name=getIntent().getStringExtra("Name");
        location=getIntent().getStringExtra("Location");
        date=getIntent().getStringExtra("Date");
        time=getIntent().getStringExtra("time");


        eventname.setText(name);
        eventlocation.setText(location);
        eventdate.setText(date);
        eventdetails.setText(time);
        //Toast.makeText(this,name,Toast.LENGTH_SHORT).show();

       url=getIntent().getStringExtra("url");
       // show(""+url);
        try {
            Picasso.with(eventpage_activity.this).load(url).into(eventimage);
        }
        catch(Exception e)
        {
            show(""+e);
        }









    }
   void show(String s)
   {
       AlertDialog.Builder alert=new AlertDialog.Builder(this);
       alert.setCancelable(true);
       alert.setMessage(s);
       alert.show();
   }
}

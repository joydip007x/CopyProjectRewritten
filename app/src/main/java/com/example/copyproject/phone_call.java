package com.example.copyproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class phone_call extends AppCompatActivity {

    //private EditText etNumber;
    private TextView one,two,three,four;
    private ImageButton btcall, btcall1, btcall2, btcall3;
    FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_call);

        this.setTitle("EL EVENTO");
        one=findViewById(R.id.etextView1);
        two=findViewById(R.id.etextView2);
        three=findViewById(R.id.etextView3);
        four=findViewById(R.id.etextView4);

      //  etNumber=findViewById(R.id.et_number);
        btcall=findViewById(R.id.bt_call);
        btcall1=findViewById(R.id.bt_call1);
        btcall2=findViewById(R.id.bt_call2);
        btcall3=findViewById(R.id.bt_call3);
        btcall1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // String phone  = etNumber.getText().toString();
                String phone1 ="01731494199";



                String s1="tel:"+phone1;
                Intent intent=new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse(s1));
                startActivity(intent);


            }
        });
        btcall2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // String phone  = etNumber.getText().toString();
                String phone2 ="01731494199";


                String s2="tel:"+phone2;
                Intent intent=new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse(s2));
                startActivity(intent);


            }
        });
        btcall3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // String phone  = etNumber.getText().toString();
                String phone3 ="01731494199";

                String s3="tel:"+phone3;
                Intent intent=new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse(s3));
                startActivity(intent);


            }
        });

        btcall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             // String phone  = etNumber.getText().toString();
                String phone ="01731494199";
             /* if(phone.isEmpty())
              {
                  Toast.makeText(phone_call.this, "Enter Phone Number", Toast.LENGTH_SHORT).show();
              }*/

                  String s="tel:"+phone;
                  Intent intent=new Intent(Intent.ACTION_CALL);
                  intent.setData(Uri.parse(s));
                  startActivity(intent);


            }
        });

    }
}

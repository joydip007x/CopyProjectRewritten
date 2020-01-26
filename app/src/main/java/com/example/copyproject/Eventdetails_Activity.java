package com.example.copyproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.File;

public class Eventdetails_Activity extends AppCompatActivity {


    private static final int PICK_IMAGE_REQUEST = 1;
    private ImageView mImageView;
    private ProgressBar mProgressBar;
    private Button mButtonChooseImage;

    private Uri mImageUri;
    private EditText eventname, eventdate, location,eventtime;
    private Button done;
    private FirebaseAuth firebaseAuth;
    String event_name, event_date, event_location,event_time, event_image;

    private StorageReference mStorageRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventdetails_);
        this.setTitle("EL EVENTO");


        mButtonChooseImage = findViewById(R.id.button_choose_image);
       // mButtonUpload = findViewById(R.id.button_upload);
        //mTextViewShowUploads = findViewById(R.id.text_view_show_uploads);
       // mEditTextFileName = findViewById(R.id.edit_text_file_name);
        mImageView = findViewById(R.id.image_view);
        mProgressBar = findViewById(R.id.progress_bar);


        eventname = findViewById(R.id.eventname_id);
        eventdate = findViewById(R.id.eventdate_id);
        location = findViewById(R.id.location_id);
        eventtime = findViewById(R.id.eventtime_id);

        mStorageRef = FirebaseStorage.getInstance().getReference();


        done=findViewById(R.id.button_upload);
        firebaseAuth = FirebaseAuth.getInstance();
        mButtonChooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {
                    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                    DatabaseReference myRef;/// = firebaseDatabase.getReference(firebaseAuth.getUid());
                    String postuid = FirebaseDatabase.getInstance().getReference().child("event").push().getKey().toString();

                    // System.out.println("ASASAS "+postuid);
                    Eventdetails event = new Eventdetails(event_name, event_date,event_location,event_time, event_image,postuid);

                    //Eventdetails event = new Eventdetails(event_name, event_date, event_location,FirebaseAuth.getInstance().getUid());
                    myRef = firebaseDatabase.getReference().child("event").child(postuid);
                 ///   FirebaseAuth.getInstance().getUid();

                    myRef.setValue(event);
                    Toast.makeText(getApplicationContext(),"Event Created",Toast.LENGTH_LONG).show();

                    startActivity(new Intent(Eventdetails_Activity.this,event_participation_choice_activity.class));

                }
            }
        });


    }

    private Boolean validate() {
        Boolean result = false;
        event_name = eventname.getText().toString().trim();
        event_date = eventdate.getText().toString().trim();
        event_location = location.getText().toString().trim();
        event_time = eventtime.getText().toString().trim();

    // event_location = location.getText().toString();

        if (event_name.isEmpty() || event_date.isEmpty() || event_image.isEmpty()) {
            Toast.makeText(Eventdetails_Activity.this, "Please enter all the details", Toast.LENGTH_SHORT).show();
        } else {
            result = true;
        }

        return result;
    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            mImageUri = data.getData();
            Picasso.with(Eventdetails_Activity.this).load(mImageUri).into(mImageView);


            Uri file = mImageUri;
            final StorageReference riversRef = mStorageRef.child("images/rabbi-"+ System.currentTimeMillis()+".jpg");

            riversRef.putFile(file)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            // Get a URL to the uploaded content
                            riversRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    Picasso.with(Eventdetails_Activity.this).load(uri.toString()).into(mImageView);
                                    event_image = uri.toString();
                                    Toast.makeText(getApplicationContext(), "Upload Successful", Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            Toast.makeText(getApplicationContext(), "Upload Failed", Toast.LENGTH_LONG).show();
                        }
                    });

          //  Picasso.with(this).load(mImageUri).into(mImageView);
           // Picasso.with(this).load(mImageUri).into(mImageView);
        }
    }
    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }






}

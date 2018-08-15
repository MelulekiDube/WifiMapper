package com.example.clayza.capstone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    EditText longitude;
    EditText latitude;
    EditText strength;
    Button save;
    Date date;
    Button viewData;
    DatabaseReference databaseSignal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseSignal= FirebaseDatabase.getInstance().getReference("location");


        longitude =(EditText)findViewById(R.id.longitude);
        latitude =(EditText)findViewById(R.id.latitude);
        strength =(EditText)findViewById(R.id.strength);
        save =(Button)findViewById(R.id.submit);
        viewData= (Button)findViewById(R.id.viewData);

        viewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addSignal();
            }
        });



    }




    /** Called when the user taps the Send button */
    public void sendMessage() {
        Intent intent = new Intent(this, Main2Activity.class);

        intent.putExtra("message from activity 1", "blah");
        startActivity(intent);
    }





    private void addSignal(){
        long lon = Long.parseLong(longitude.getText().toString().trim());
        long lat = Long.parseLong(latitude.getText().toString().trim());

        double strengthValue =Double.parseDouble(strength.getText().toString());
        date = new Date();

        //store the values on firebase
        String id =databaseSignal.push().getKey();//creates a unique string ID


        LocationCapstone lc = new LocationCapstone();
        databaseSignal.child(id).setValue(lc);
        Toast.makeText(this,"Signal added",Toast.LENGTH_LONG).show();

    }




}
/*
*
* */
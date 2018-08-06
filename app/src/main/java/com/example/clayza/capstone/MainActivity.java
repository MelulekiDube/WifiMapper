package com.example.clayza.capstone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    EditText location;
    EditText strength;
    Button save;
    Date date;

    DatabaseReference databaseSignal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseSignal= FirebaseDatabase.getInstance().getReference("signal");


        location =(EditText)findViewById(R.id.location);
        strength =(EditText)findViewById(R.id.strength);
        save =(Button)findViewById(R.id.submit);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addSignal();
            }
        });
    }

    private void addSignal(){
        String locationName =location.getText().toString().trim();
        double strengthValue =Double.parseDouble(strength.getText().toString());
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        date = new Date();

        //store the values on firebase
        String id =databaseSignal.push().getKey();//creates a unique string ID
        Signal sig = new Signal(id,locationName,strengthValue,date);
        databaseSignal.child(id).setValue(sig);
        Toast.makeText(this,"Signal added",Toast.LENGTH_LONG).show();

    }
}
/*
*
* */
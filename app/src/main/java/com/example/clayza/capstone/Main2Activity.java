package com.example.clayza.capstone;

import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends Activity {


    DatabaseReference databaseSignal = FirebaseDatabase.getInstance().getReference().child("location");
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        listView = (ListView) findViewById(R.id.recordList);

      final List<LocationCapstone> signalList = new ArrayList<>();
        // Read from the database
        databaseSignal.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange (DataSnapshot dataSnapshot){
            // This method is called once with the initial value and again
            // whenever data at this location is updated.


            for(DataSnapshot snapshot:dataSnapshot.getChildren()){
               LocationCapstone location =snapshot.getValue(LocationCapstone.class);
                signalList.add(location);
              //  Log.d("TAG", "Value is: " + location);
            }

       }

        @Override
        public void onCancelled (DatabaseError error){
            // Failed to read value
            Log.w("TAG", "Failed to read value.", error.toException());
        }
    });
    List<String> ls = new ArrayList<>();

        for(LocationCapstone item:signalList)
            ls.add(item.toString());

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,ls);
        listView.setAdapter(adapter);


}

}

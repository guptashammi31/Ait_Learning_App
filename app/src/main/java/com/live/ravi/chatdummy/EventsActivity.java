package com.live.ravi.chatdummy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EventsActivity extends AppCompatActivity {
    DatabaseReference eventreffrence;

    TextView setdate,settime,setaddress;
    Spinner spinner;
    String str;
    public ArrayAdapter<String> spinnerCountShoesArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        setaddress=(TextView)findViewById(R.id.addr);
        setdate=(TextView)findViewById(R.id.ddd);
        settime=(TextView)findViewById(R.id.ttt);
        spinner = (Spinner) findViewById(R.id.spinner);
        spinnerCountShoesArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.cityname));
        spinner.setAdapter(spinnerCountShoesArrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parentView,
                                       View selectedItemView, int position, long id) {
                Object item = parentView.getItemAtPosition(position);
                str=item.toString();
                Toast.makeText(getApplicationContext(),str,Toast.LENGTH_SHORT).show();
            come(str);
            }
            @Override
            public void
            onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
    public void come(String str){
        eventreffrence= FirebaseDatabase.getInstance().getReference("Events").child(str);


        eventreffrence.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    setdate.setText((String) dataSnapshot.child("date").getValue());
                    settime.setText((String) dataSnapshot.child("time").getValue());
                    setaddress.setText((String) dataSnapshot.child("address").getValue());



                }
            }

            @Override
            public void onCancelled(DatabaseError dataSnapshot) {

            }
        });}
}

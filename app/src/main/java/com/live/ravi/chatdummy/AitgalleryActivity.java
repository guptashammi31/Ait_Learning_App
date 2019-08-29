package com.live.ravi.chatdummy;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AitgalleryActivity extends AppCompatActivity {
    //recyclerview object
    private RecyclerView recyclerView1;

    //adapter object
    private RecyclerView.Adapter adapter1;

    //database reference
    private DatabaseReference mDatabase1;

    //progress dialog
    private ProgressDialog progressDialog1;

    //list to hold all the uploaded images
    private List<Upload> uploads;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aitgallery);
        recyclerView1 = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));


        progressDialog1 = new ProgressDialog(this);

        uploads = new ArrayList<>();

        //displaying progress dialog while fetching images
        progressDialog1.setMessage("Please wait...");
        progressDialog1.show();
        mDatabase1 = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS);

        //adding an event listener to fetch values
        mDatabase1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                //dismissing the progress dialog
                progressDialog1.dismiss();

                //iterating through all the values in database
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    Upload upload = postSnapshot.getValue(Upload.class);
                    uploads.add(upload);
                }
                //creating adapter
                adapter1 = new MyAdapter(getApplicationContext(), uploads);

                //adding adapter to recyclerview
                recyclerView1.setAdapter(adapter1);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                progressDialog1.dismiss();
            }
        });

    }
}
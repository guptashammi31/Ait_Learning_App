package com.live.ravi.chatdummy;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

public class AdminActivity extends AppCompatActivity {
    DatabaseReference userid;
    EditText adminid,passadmin;
    Button login;
String user,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);



        adminid = (EditText) findViewById(R.id.input_email);
        passadmin =(EditText) findViewById(R.id.input_password);
        login = (Button)findViewById(R.id.btnlogin);

        String username = adminid.getText().toString();

        userid= FirebaseDatabase.getInstance().getReference("Adminlogin");

        //  ref= FirebaseDatabase.getInstance().getReference(Database_Path);
        userid.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    user=((String) dataSnapshot.child("user").getValue());
                    pass=((String) dataSnapshot.child("password").getValue());


//
                }
            }

            
            @Override
            public void onCancelled(DatabaseError dataSnapshot) {

            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(adminid.getText().toString().equals(user) &&
                        passadmin.getText().toString().equals(pass)) {
                    Intent intent = new Intent(AdminActivity.this, AdminViewActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(),
                            " wrong crendential...",Toast.LENGTH_SHORT).show();

                }
            }
        });


    }
}

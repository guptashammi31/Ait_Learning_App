package com.live.ravi.chatdummy;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ComplainMailActivity extends AppCompatActivity {
EditText sub,mess;
Button button11;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complain_mail);
        sub=(EditText)findViewById(R.id.subj) ;
        mess=(EditText)findViewById(R.id.message) ;
button11=(Button)findViewById(R.id.btn);

        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String subject = sub.getText().toString().trim();
                String message = mess.getText().toString().trim();

                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto","guptashammi31@gmail.com", null));
                intent.putExtra(Intent.EXTRA_SUBJECT, subject);
                intent.putExtra(Intent.EXTRA_TEXT, message);
                startActivity(Intent.createChooser(intent, "Choose an Email client :"));

            }
        });


    }




    }


package com.live.ravi.chatdummy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminViewActivity extends AppCompatActivity {
Button upgallery,uppdf,reg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view);
        reg = (Button)findViewById(R.id.reg);

        upgallery = (Button)findViewById(R.id.upgallery);
        uppdf = (Button)findViewById(R.id.uppdf);


        upgallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(AdminViewActivity.this, UploadGalleryActivity.class);
                startActivity(intent);

            }
        });
        uppdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(AdminViewActivity.this, UploadPdfActivity.class);
                startActivity(intent);

            }
        });
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(AdminViewActivity.this, RegisterActivity.class);
                startActivity(intent);

            }
        });
    }
}

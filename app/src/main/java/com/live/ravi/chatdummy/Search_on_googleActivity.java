package com.live.ravi.chatdummy;

import android.app.SearchManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class Search_on_googleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_google);
        final EditText searchtext=(EditText)findViewById(R.id.editText);
        ImageButton imgbutton=(ImageButton)findViewById(R.id.imageButton);

        imgbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                    String term = searchtext.getText().toString();
                    intent.putExtra(SearchManager.QUERY, term);
                    startActivity(intent);
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
        });



    }
}

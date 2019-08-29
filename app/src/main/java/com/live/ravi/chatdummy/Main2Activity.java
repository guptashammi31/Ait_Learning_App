package com.live.ravi.chatdummy;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener  {

    TextView nameuser, textView5 ,walletuser, review, network, plugins, myapps, mainmenus,
            pagetitle, pagesubtitle;
    DatabaseReference ref,ref1;
    Dialog myDialog;
    private TextView mTextMessage;
    TextView textView;
    private Switch myswitch;
    String link;

    // SharedPref sharedpref;
    Button btnguide;
    Animation atg, atgtwo, atgthree;
    ImageView imageView3;
    LinearLayout linearLayout3,linearLayout4,linearLayout5,admission,Faculty,branch,course,event,gallery,lectures,call,search,btnfollow,share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //sharedpref = new SharedPref(this);
        //  if(sharedpref.loadNightModeState()==true) {
        //    setTheme(R.style.darktheme);
        //}
        //  else  setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        myDialog = new Dialog(this);
        // myswitch=(Switch)findViewById(R.id.myswitch);



        mTextMessage = (TextView) findViewById(R.id.message);




        ref1= FirebaseDatabase.getInstance().getReference("Playstore");

        //  ref= FirebaseDatabase.getInstance().getReference(Database_Path);
        ref1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    link=((String) dataSnapshot.child("text").getValue());


//
                }
            }

            @Override
            public void onCancelled(DatabaseError dataSnapshot) {

            }
        });





        // BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        // navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        ref= FirebaseDatabase.getInstance().getReference("Thought");
        textView=(TextView)findViewById(R.id.textGrid);

        //  ref= FirebaseDatabase.getInstance().getReference(Database_Path);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    textView.setText((String) dataSnapshot.child("s1").getValue());


//
                }
            }

            @Override
            public void onCancelled(DatabaseError dataSnapshot) {

            }
        });

        atg = AnimationUtils.loadAnimation(this, R.anim.atg);
        atgtwo = AnimationUtils.loadAnimation(this, R.anim.atgtwo);
        atgthree = AnimationUtils.loadAnimation(this, R.anim.atgthree);

        nameuser = (TextView) findViewById(R.id.nameuser);
        textView5 = (TextView) findViewById(R.id.textView5);
        //walletuser = findViewById(R.id.walletuser);

        // imageView3 = findViewById(R.id.imageView3);
        btnfollow = (LinearLayout) findViewById(R.id.btnfollow);
        share = (LinearLayout) findViewById(R.id.share);

        admission = (LinearLayout) findViewById(R.id.admission);
        lectures = (LinearLayout) findViewById(R.id.lectures);
        Faculty = (LinearLayout) findViewById(R.id.Faculty);
        branch = (LinearLayout) findViewById(R.id.branch);
        course = (LinearLayout) findViewById(R.id.course);
        event = (LinearLayout) findViewById(R.id.event);
        gallery = (LinearLayout) findViewById(R.id.gallery);
        linearLayout3 = (LinearLayout) findViewById(R.id.linearLayout3);
        linearLayout4 = (LinearLayout) findViewById(R.id.linearLayout4);
        linearLayout5 = (LinearLayout) findViewById(R.id.linearLayout5);
        call=(LinearLayout)findViewById(R.id.call);
        search=(LinearLayout)findViewById(R.id.search);
        btnfollow.setOnClickListener(this);

        admission.setOnClickListener(this);
        lectures.setOnClickListener(this);
        Faculty.setOnClickListener(this);
        branch.setOnClickListener(this);
        course.setOnClickListener(this);
        event.setOnClickListener(this);
        gallery.setOnClickListener(this);
        call.setOnClickListener(this);
        search.setOnClickListener(this);
        share.setOnClickListener(this);
        // pagetitle = findViewById(R.id.pagetitle);
        // pagesubtitle = findViewById(R.id.pagesubtitle);

        // btnguide = findViewById(R.id.btnguide);

        // pass an animation
        linearLayout3.startAnimation(atg);

        linearLayout4.startAnimation(atgtwo);
        // pagesubtitle.startAnimation(atgtwo);

        linearLayout5.startAnimation(atgthree);

        // import font
        Typeface MLight = Typeface.createFromAsset(getAssets(), "fonts/ML.ttf");
        Typeface MMedium = Typeface.createFromAsset(getAssets(), "fonts/MM.ttf");
        Typeface MRegular = Typeface.createFromAsset(getAssets(), "fonts/MR.ttf");


        // customize font
        nameuser.setTypeface(MMedium);
        //walletuser.setTypeface(MLight);

//        mainmenus.setTypeface(MRegular);

//        review.setTypeface(MMedium);
//        plugins.setTypeface(MMedium);
//        network.setTypeface(MMedium);
//        myapps.setTypeface(MMedium);

//        btnguide.setTypeface(MMedium);

        // pagetitle.setTypeface(MRegular);
        // pagesubtitle.setTypeface(MLight);



    }
    public void ShowPopup(View v) {
        TextView txtclose;
        Button btnFollow;
        myDialog.setContentView(R.layout.custompopup);

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }


    public void onClick(View v) {
        Intent i;
        switch (v.getId()) {
            case R.id.btnfollow:
                i = new Intent(this, AboutusActivity.class);startActivity(i);
                break;
            case R.id.admission:
                i = new Intent(this, AdmissionActivity.class);startActivity(i);
                break;
            case R.id.lectures:
                i = new Intent(this, LoginActivity.class);startActivity(i);
                break;
            case R.id.Faculty:
                i = new Intent(this, AdminActivity.class);startActivity(i);
                break;
            case R.id.branch:
                i = new Intent(this, About2Activity.class);startActivity(i);

                break;
            case R.id.course:
                i = new Intent(this, ShowCoursesActivity.class);startActivity(i);
                break;
            case R.id.event:
                i = new Intent(this, EventsActivity.class);startActivity(i);
                break;
            case R.id.gallery:
                i = new Intent(this, AitgalleryActivity.class);startActivity(i);
                break;
            case R.id.share:
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,"Subject here");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, link);
                startActivity(Intent.createChooser(sharingIntent, "Shearing Option"));
                break;
            case R.id.search:
                i = new Intent(this,FaqActivity.class);startActivity(i);

                break;

            case R.id.call:

                        Intent intent = new Intent(Intent.ACTION_DIAL);
                        String temp = "tel:" + "8342888881";
                        intent.setData(Uri.parse(temp));
                        startActivity(intent);

                break;

            default:
                break;
        }
    }
    public void restartApp () {
        Intent i = new Intent(getApplicationContext(),Main2Activity.class);
        startActivity(i);
        finish();
    }
}

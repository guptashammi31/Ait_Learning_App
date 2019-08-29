package com.live.ravi.chatdummy;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.live.ravi.chatdummy.Fragments.ChatsFragment;
import com.live.ravi.chatdummy.Fragments.ProfileFragment;
import com.live.ravi.chatdummy.Fragments.UsersFragment;
import com.live.ravi.chatdummy.Model.User;

import java.util.ArrayList;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    CircleImageView profile_image;
    TextView username;
    DatabaseReference ref1;
String link;
    FirebaseUser firebaseUser;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar =(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        username =(TextView) findViewById(R.id.username);

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




        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());



        final TabLayout tabLayout =(TabLayout) findViewById(R.id.tab_layout);
        final ViewPager viewPager =(ViewPager) findViewById(R.id.view_pager);


        reference = FirebaseDatabase.getInstance().getReference("Chats");

                ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
                int unread = 0;





        viewPagerAdapter.addFragment(new UsersFragment(), "Lectures");
        viewPagerAdapter.addFragment(new ProfileFragment(), " My Profile");

                viewPager.setAdapter(viewPagerAdapter);

                tabLayout.setupWithViewPager(viewPager);




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case  R.id.logout:
                FirebaseAuth.getInstance().signOut();
                // change this code beacuse your app will crash
                startActivity(new Intent(MainActivity.this, SplashScreenActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                return true;

            case  R.id.share:
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,"Subject here");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, link);
                startActivity(Intent.createChooser(sharingIntent, "Shearing Option"));
                return true;
            case  R.id.complain:
                        Intent intent = new Intent(MainActivity.this, ComplainMailActivity.class);
                        startActivity(intent);


                return  true;

        }

        return false;



    }

    class ViewPagerAdapter extends FragmentPagerAdapter {

        private ArrayList<Fragment> fragments;
        private ArrayList<String> titles;

        ViewPagerAdapter(FragmentManager fm){
            super(fm);
            this.fragments = new ArrayList<>();
            this.titles = new ArrayList<>();
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        public void addFragment(Fragment fragment, String title){
            fragments.add(fragment);
            titles.add(title);
        }

        // Ctrl + O

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
    }

    private void status(String status){
        reference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("status", status);

        reference.updateChildren(hashMap);
    }

    @Override
    protected void onResume() {
        super.onResume();
        status("online");
    }

    @Override
    protected void onPause() {
        super.onPause();
        status("offline");
    }
}

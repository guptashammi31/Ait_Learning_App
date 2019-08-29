package com.live.ravi.chatdummy.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.live.ravi.chatdummy.DisplayImagesActivity;
import com.live.ravi.chatdummy.DisplaySubjectActivity;
import com.live.ravi.chatdummy.Model.User;
import com.live.ravi.chatdummy.R;

import java.util.List;


public class UsersFragment extends Fragment {

    private RecyclerView recyclerView;
Context conttext;
    private List<User> mUsers;
Button module1,module2,module3,module4,module5,module6,module7,module8,module9,module10,module11,module12;
    TextView search_users;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_users, container, false);
        search_users = view.findViewById(R.id.sear);
        module1 = view.findViewById(R.id.module1);
        module2 = view.findViewById(R.id.module2);
        module3 = view.findViewById(R.id.module3);
        module4 = view.findViewById(R.id.module4);
        module5 = view.findViewById(R.id.module5);
        module6 = view.findViewById(R.id.module6);
        module7 = view.findViewById(R.id.module7);
        module8 = view.findViewById(R.id.module8);
        module9 = view.findViewById(R.id.module9);
        module10 = view.findViewById(R.id.module10);
        module11 = view.findViewById(R.id.module11);
        module12 = view.findViewById(R.id.module12);
        search_users.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), DisplayImagesActivity.class);
                startActivity(intent);

            }
        });

        module1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), DisplaySubjectActivity.class);
                intent.putExtra("sub", "module1");
                startActivity(intent);

            }
        });

        module2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), DisplaySubjectActivity.class);
                intent.putExtra("sub", "module2");
                startActivity(intent);

            }
        });  module3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), DisplaySubjectActivity.class);
                intent.putExtra("sub", "module3");
                startActivity(intent);

            }
        });  module4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), DisplaySubjectActivity.class);
                intent.putExtra("sub", "module4");
                startActivity(intent);

            }
        });

        module5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), DisplaySubjectActivity.class);
                intent.putExtra("sub", "module5");
                startActivity(intent);

            }
        });



        module6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), DisplaySubjectActivity.class);
                intent.putExtra("sub", "module6");
                startActivity(intent);

            }
        });

        module7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), DisplaySubjectActivity.class);
                intent.putExtra("sub", "module7");
                startActivity(intent);

            }
        });  module8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), DisplaySubjectActivity.class);
                intent.putExtra("sub", "module8");
                startActivity(intent);

            }
        });  module9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), DisplaySubjectActivity.class);
                intent.putExtra("sub", "module9");
                startActivity(intent);

            }
        });

        module10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), DisplaySubjectActivity.class);
                intent.putExtra("sub", "module10");
                startActivity(intent);

            }
        });

        module11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), DisplaySubjectActivity.class);
                intent.putExtra("sub", "module11");
                startActivity(intent);

            }
        });

        module12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), DisplaySubjectActivity.class);
                intent.putExtra("sub", "module12");
                startActivity(intent);

            }
        });
        return view;
        }
}

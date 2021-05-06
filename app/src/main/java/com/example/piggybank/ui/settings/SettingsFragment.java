package com.example.piggybank.ui.settings;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.piggybank.CategoryManagement;
import com.example.piggybank.R;
import com.example.piggybank.SettingsActivity;

public class SettingsFragment extends Fragment {



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_settings, container, false);
        Button settings_button = root.findViewById(R.id.settings_button);
        Button category_settings_button = root.findViewById(R.id.category_settings_button);

        Button aboutUs_button = root.findViewById(R.id.aboutUs_button);

        //ft.setCustomAnimations(android.R.animator.fade_in,
            //    android.R.animator.fade_out);


        settings_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity().getApplicationContext(), SettingsActivity.class);
                startActivity(intent);


            }
        });
        category_settings_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), CategoryManagement.class);
                startActivity(intent);
            }
        });
        return root;
    }






}
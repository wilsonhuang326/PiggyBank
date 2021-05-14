package com.example.piggybank.ui.transactions;

import android.content.Intent;
import android.content.ClipData;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.piggybank.R;
import com.example.piggybank.SettingsActivity;
import com.example.piggybank.addTrans;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class TransactionsFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_transactions, container, false);
        final TextView textView = root.findViewById(R.id.text_transactions);

        FloatingActionButton button1 = root.findViewById(R.id.addingTrans);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), addTrans.class);
                startActivity(intent);

            }
        });
        return root;
    }

}
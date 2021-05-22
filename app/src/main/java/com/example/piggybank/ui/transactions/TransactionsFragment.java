package com.example.piggybank.ui.transactions;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.piggybank.CategoryAdapter;
import com.example.piggybank.R;
import com.example.piggybank.AddTrans;
import com.example.piggybank.TransactionAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class TransactionsFragment extends Fragment {
    private ListView mListView;

    private TransactionAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_transactions, container, false);


        adapter = new TransactionAdapter(getActivity());

        mListView=(ListView)root.findViewById(R.id.transaction_list);
        mListView.setAdapter(adapter);
        FloatingActionButton button1 = root.findViewById(R.id.addingTrans);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), AddTrans.class);
                startActivity(intent);

            }
        });
        return root;
    }

}
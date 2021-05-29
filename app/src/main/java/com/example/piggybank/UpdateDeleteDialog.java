package com.example.piggybank;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyboardShortcutGroup;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

/////////暂时弃用
public class UpdateDeleteDialog extends Dialog implements
        View.OnClickListener {
    private Context mContext;
    private Button yes,no;
    private TextView messageView;
    private String message;
    private int cid;
    public UpdateDeleteDialog(@NonNull Context context,String message,int cid) {
        super(context);
        this.mContext=context;
        this.message=message;
        this.cid=cid;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.update_delete_dialog);
        yes = (Button) findViewById(R.id.update_delete_dialog_yes);
        no = (Button) findViewById(R.id.update_delete_dialog_no);
        messageView=(TextView) findViewById(R.id.update_delete_dialog_text);
        messageView.setText(message);
        yes.setOnClickListener(this);
        no.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.update_delete_dialog_yes:
                Toast.makeText(mContext, "yes",
                        Toast.LENGTH_LONG).show();
            break;
            case R.id.update_delete_dialog_no:

                dismiss();
                break;
            default:
                dismiss();
                break;
        }
    }


}

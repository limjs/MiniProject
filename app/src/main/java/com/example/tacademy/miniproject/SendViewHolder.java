package com.example.tacademy.miniproject;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Tacademy on 2016-08-22.
 */
public class SendViewHolder extends RecyclerView.ViewHolder{
    TextView messageView;

    public SendViewHolder(View itemView) {
        super(itemView);
        messageView = (TextView)itemView.findViewById(R.id.text_message);
    }

    public void setMessage(String message){
        messageView.setText(message);
    }
}

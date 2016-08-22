package com.example.tacademy.miniproject;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tacademy.miniproject.data.ContentData;

/**
 * Created by Tacademy on 2016-08-22.
 */
public class ContentViewHolder extends RecyclerView.ViewHolder {
    TextView contentView;
    ImageView pictureView;

    public ContentViewHolder(View itemView) {
        super(itemView);
        contentView = (TextView)itemView.findViewById(R.id.text_content);
        pictureView = (ImageView)itemView.findViewById(R.id.image_picture);
    }
    public void setContent(ContentData content){
        contentView.setText(content.getContent());
        Glide.with(pictureView.getContext())
                .load(content.getImageUrl())
                .into(pictureView);
    }

}

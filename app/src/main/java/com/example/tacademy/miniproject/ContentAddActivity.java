package com.example.tacademy.miniproject;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.tacademy.miniproject.data.ContentData;
import com.example.tacademy.miniproject.manager.NetworkManager;
import com.example.tacademy.miniproject.manager.NetworkRequest;
import com.example.tacademy.miniproject.request.UploadRequest;

import java.io.File;

public class ContentAddActivity extends AppCompatActivity {

    EditText messageView;
    ImageView pictureView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_add);

        messageView = (EditText)findViewById(R.id.edit_message);
        pictureView = (ImageView)findViewById(R.id.image_picture);

        Button btn = (Button)findViewById(R.id.btn_upload);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = messageView.getText().toString();
                if (!TextUtils.isEmpty(content) && uploadFile != null) {
                    UploadRequest request = new UploadRequest(this, content, uploadFile);
                    NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<NetworkRequest<ContentData>>() {
                        @Override
                        public void onSuccess(NetworkRequest<NetworkRequest<ContentData>> request, NetworkRequest<ContentData> result) {
                            Toast.makeText(ContentAddActivity.this, "success", Toast.LENGTH_SHORT).show();
                            finish();
                        }

                        @Override
                        public void onFail(NetworkRequest<NetworkRequest<ContentData>> request, int errorCode, String errorMessage, Throwable e) {
                            Toast.makeText(ContentAddActivity.this, "fail", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

    }

    private static final int RC_GET_IMAGE = 1;
    File uploadFile = null;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RC_GET_IMAGE){
            if(resultCode == Activity.RESULT_OK){
                Uri uri = data.getData();
                Cursor c = getContentResolver().query(uri, new String[]{MediaStore.Images.Media.DATA}, null, null, null);
                if(c.moveToNext()){
                    String path = c.getString(c.getColumnIndex(MediaStore.Images.Media.DATA));
                    uploadFile = new File(path);
                    Glide.with(this)
                            .load(uploadFile)
                            .into(pictureView);
                }
            }
        }
    }


}

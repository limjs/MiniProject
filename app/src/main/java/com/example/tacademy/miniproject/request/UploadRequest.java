package com.example.tacademy.miniproject.request;

import android.content.Context;

import com.example.tacademy.miniproject.data.ContentData;
import com.example.tacademy.miniproject.data.NetworkResult;
import com.example.tacademy.miniproject.manager.NetworkRequest;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.lang.reflect.Type;

import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Tacademy on 2016-08-12.
 */
public class UploadRequest extends AbstractRequest<NetworkRequest<ContentData>>{

    MediaType jpeg = MediaType.parse("image/jpeg");
    Request request;
    public UploadRequest(Context context, String content, File file){
        HttpUrl url = getBaseUrlBuilder()
                .addPathSegment("upload")
                .build();

        MultipartBody.Builder builder = new MultipartBody.Builder()
                .addFormDataPart("content", content);
        if(file!=null){
            //키값, 파일이름(스트링), 실제 파일.
            //같은 키값으로 넣으면 배열이 만들어진다
            builder.addFormDataPart("myFile", file.getName(),
                    RequestBody.create(jpeg,file));
        }
        RequestBody body = builder.build();
        request = new Request.Builder()
                .url(url)
                .post(body)
                .tag(content)
                .build();
    }

    @Override
    protected Type getType() {
        return new TypeToken<NetworkResult<ContentData>>(){}.getType();
    }

    @Override
    public Request getRequest() {
        return request;
    }
}

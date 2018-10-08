package com.kxkg.youehu.gaopeic.util;

import android.util.Log;

import com.blankj.utilcode.util.StringUtils;
import com.google.gson.Gson;
import com.kxkg.youehu.gaopeic.constant.Constant;
import com.kxkg.youehu.gaopeic.entity.UploadEntity;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/9/18.
 */

public class OkHttpUpload {

    private UploadListener uploadListener;
    private String[] args;
    private void sendMultipart(String args,String type){
        int cacheSize = 10 * 1024 * 1024;
        //设置超时时间及缓存
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS);

        OkHttpClient mOkHttpClient=builder.build();

        MultipartBody.Builder mbody=new MultipartBody.Builder().setType(MultipartBody.FORM);

        List<File> fileList=new ArrayList<File>();
        File img1=new File(args);
        fileList.add(img1);

        int i=0;
        for(File file:fileList){
            if(file.exists()){
                mbody.addFormDataPart(file.getName()+i,file.getName(), RequestBody.create(MediaType.parse("image/png"),file));
                i++;
            }
        }

        RequestBody requestBody =mbody.build();
        Request request = new Request.Builder()
                .url(Constant.URL+"/consumer/api/v1/upload/carer/"+type)
                .post(requestBody)
                .build();

        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                uploadListener.onFailure(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String body=response.body().string();
                Log.e("onResponse----",body);
                Gson gson=new Gson();
                UploadEntity uploadEntity= gson.fromJson(body,UploadEntity.class);
                if (StringUtils.equals(Constant.REQUEST_SUCCESS,uploadEntity.getCode())) {

                    if (uploadListener != null) {
                        uploadListener.getImgPath(body);
                    }
                }else {
                    uploadListener.onFailure(uploadEntity.getMsg());
                }
            }
        });
    }

    public OkHttpUpload main(String args,String type) {
        sendMultipart(args,type);
        return OkHttpUpload.this;
    }



    public void setUploadListener(UploadListener uploadListener){
        this.uploadListener=uploadListener;
    }

    public interface  UploadListener{
        void getImgPath(String image);
        void onFailure(String msg);
    }

}

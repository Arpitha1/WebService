package com.example.abhi.webservice.network;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.abhi.webservice.utils.CommonUtilities;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.util.concurrent.TimeUnit;

import okio.Buffer;


public class CallAddr extends AsyncTask<String, Void, String> {

    private Context context;
    private String result = "";
    private FormEncodingBuilder formBody;
    private String url;
    private OnWebServiceResult resultListener;
    private CommonUtilities.SERVICE_TYPE Servicetype;





    public CallAddr(Context context, String url, FormEncodingBuilder formBody, CommonUtilities.SERVICE_TYPE Servicetype, OnWebServiceResult resultListener) {
        this.context = context;
        this.formBody = formBody;
        this.url = url;
        this.resultListener = resultListener;
        this.Servicetype = Servicetype;


    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        OkHttpClient client =new OkHttpClient();
        client.setConnectTimeout(120, TimeUnit.SECONDS); // connect timeout
        client.setReadTimeout(120, TimeUnit.SECONDS); // socket timeout
        //formBody.add("platform", "android");


        Request request = new Request.Builder()
                .url(url)
                //.post(body)
                .build();
        Log.e("CallAddr " + Servicetype, "url= " + url + " params= " + bodyToString(request));
        try {
            Response response = client.newCall(request).execute();
            if (!response.isSuccessful()) {
                result = response.toString();
                if (result.equals("") || result.equals("null") || result.length() == 0) {
                     CommonUtilities.showToast(context, "Something went wrong. Try later.");
                }
            }
            result = response.body().string();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Log.e("CallAddr", "service_type= " + Servicetype + " result= " + s);
            resultListener.getWebResponse(s, Servicetype);
        }

    private static String bodyToString(final Request request) {

        try {
            final Request copy = request.newBuilder().build();
            final Buffer buffer = new Buffer();
            copy.body().writeTo(buffer);
            return buffer.readUtf8();
        } catch (Exception e) {
            return "did not work";
        }
    }
}

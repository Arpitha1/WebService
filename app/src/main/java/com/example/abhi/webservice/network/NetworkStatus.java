/*********************************************************
 * NetworkStatus helps us to check the Internet connection 
 * state of our handheld device.**************************
 ********************************************************/
package com.example.abhi.webservice.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


public class NetworkStatus {

    private static NetworkStatus instance = new NetworkStatus();
     static Context context;
    ConnectivityManager connectivityManager;


    public static NetworkStatus getInstance(Context ctx) {
        context = ctx;
        return instance;
    }


    public boolean isConnectedToInternet(){
        connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();


        return activeNetwork != null;
    }
}

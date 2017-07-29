package com.myball.Service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import org.json.JSONArray;

import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class GetThings extends IntentService {
    ConnectivityManager cm;
    NetworkInfo activeNetwork;
    public GetThings() {
        super(GetThings.class.getName());
    }
    @Override
    protected void onHandleIntent(Intent intent) {

            try{
                cm =(ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                activeNetwork = cm.getActiveNetworkInfo();
                boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
                if(isConnected) {
                        String Ur = "";
                        URL url;
                        HttpURLConnection conn;
                        url = new URL(Ur);
                        String param = "UserID=" + URLEncoder.encode("", "UTF-8");

                        conn = (HttpURLConnection) url.openConnection();
                        conn.setDoOutput(true);
                        conn.setRequestMethod("POST");
                        conn.setFixedLengthStreamingMode(param.getBytes().length);
                        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                        PrintWriter out = new PrintWriter(conn.getOutputStream());
                        out.print(param);
                        out.close();
                        if (conn.getResponseCode() == 200) {
                            StringBuilder stringBuilder = new StringBuilder();
                            Scanner inStream = new Scanner(conn.getInputStream());
                            while (inStream.hasNextLine())
                                stringBuilder.append(inStream.nextLine());
                            if (stringBuilder.toString().length() > 10) {
                                JSONArray jArray = new JSONArray(stringBuilder.toString());
                                if (jArray.length() > 0)
                                    for (int AR = 0; AR < jArray.length(); AR++) {
                                    }
                            }
                        }


                }
            }
            catch(Exception e){ }
            finally{

            }
        }
}
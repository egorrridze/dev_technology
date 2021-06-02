package ru.practice.dev_technology;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.ls.LSOutput;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DownloadThread extends AsyncTask<Void, Void, String> {
    String query;
    Spinner values;
    HttpURLConnection httpURLConnection;
    String first_value_type;
    String second_value_type;
    String first_value_num;
    MainActivity context;
    String line;

    public DownloadThread(MainActivity context, String query, Spinner values, HttpURLConnection httpURLConnection, String first_value_type, String second_value_type, String first_value_num) {
        this.context = context;
        this.query = query;
        this.values = values;
        this.httpURLConnection = httpURLConnection;
        this.first_value_type = first_value_type;
        this.second_value_type = second_value_type;
        this.first_value_num = first_value_num;
    }

    public String getTask() {
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("https://trppconverter1.herokuapp.com/convert?from=");
        queryBuilder.append(first_value_type);
        queryBuilder.append("&num=");
        queryBuilder.append(first_value_num);
        queryBuilder.append("&to=");
        queryBuilder.append(second_value_type);
        query = queryBuilder.toString();
        System.out.println(query);
        httpURLConnection = null;
        try {
            httpURLConnection = (HttpURLConnection) new URL(query).openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setConnectTimeout(10000);
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.connect();

            StringBuilder stringBuilder = new StringBuilder();

            if (HttpURLConnection.HTTP_OK == httpURLConnection.getResponseCode()) {
                BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));



                while ((line = in.readLine()) != null) {
                    stringBuilder.append(line);
                }

                line = stringBuilder.toString();
                System.out.println(line);
//                context.secondValueNum.setText(line);
                return line;
//                Toast.makeText(MainActivity.class,line,Toast.LENGTH_SHORT).show();

            } else {
                System.out.println("fail: " + httpURLConnection.getResponseCode());
            }
        } catch (Throwable cause) {
            cause.printStackTrace();
        } finally {
            if (httpURLConnection != null)
                httpURLConnection.disconnect();
        }
        return null;
    }


    @Override
    protected String doInBackground(Void... voids) {
        return this.getTask();
    }

    @Override
    protected void onPostExecute(String s) {
        context.secondValueNum.setText(line);
        StringBuilder result = new StringBuilder();
        result.append(context.inputNum.getText());
        result.append(" ");
        result.append(context.choice1.getSelectedItem());
        result.append(" = ");
        result.append(context.secondValueNum.getText());
        result.append(" ");
        result.append(context.choice2.getSelectedItem());
        context.cardData.add(result.toString());
        SharedPreferences.Editor editor = context.preferences.edit();
        editor.putStringSet("cardData", context.cardData);
        editor.apply();
    }
}

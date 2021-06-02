package ru.practice.dev_technology;

import android.os.AsyncTask;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.ls.LSOutput;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DownloadThread extends AsyncTask<Void, Void, String[]> {
    String query;
    Spinner values;
    HttpURLConnection httpURLConnection;

    public DownloadThread(String query, Spinner values, HttpURLConnection httpURLConnection) {
        this.query = query;
        this.values = values;
        this.httpURLConnection = httpURLConnection;
    }

    public String[] getTask(){
        query = "https://trppconverter1.herokuapp.com/getValues?type="+values.getSelectedItem().toString();
        httpURLConnection = null;
        try{
            httpURLConnection = (HttpURLConnection) new URL(query).openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setConnectTimeout(250);
            httpURLConnection.setReadTimeout(250);
            httpURLConnection.connect();

            StringBuilder stringBuilder = new StringBuilder();
            StringBuilder word = new StringBuilder();
            String[] subStr;
            ArrayList<String> stringArr = new <>;

            if (HttpURLConnection.HTTP_OK == httpURLConnection.getResponseCode()){
                BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));

                String line;
                ArrayList<String> list = new ArrayList<String>();

                while ((line = in.readLine())!=null){
                    stringBuilder.append(line);
                }

                line = stringBuilder.toString();
                System.out.println(line);
                for (int i = 0;i<line.length(); i++){
                    if(line.charAt(i)!='[' && line.charAt(i)!=']' && line.charAt(i)!='"' && line.charAt(i)!=',' && line.charAt(i)!=' ')
//                        System.out.println(line.charAt(i));
                        word.append(line.charAt(i));
                    stringArr.add(word.toString());
                }
                System.out.println(stringArr);
                subStr = line.replace(',',' ')
                        .replace('[',' ').replace(']',' ')
                        .split("\\s");
                System.out.println(subStr[1]);
                return subStr;
            } else {
                System.out.println("fail: "+httpURLConnection.getResponseCode());
            }
        }catch (Throwable cause){
            cause.printStackTrace();
        }finally {
            if(httpURLConnection!=null)
                httpURLConnection.disconnect();
        }
        return null;
    }


    @Override
    protected String[] doInBackground(Void... voids) {
        return this.getTask();
    }
}

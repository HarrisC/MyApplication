package com.example.inspiron.myapplication;

import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.net.URL;

/**
 * Created by inspiron on 15/6/2017.
 */

public class DataFetcher extends AsyncTask<Void, Integer, String> {
    URL url;

    public DataFetcher() {

    }

    @Override
    protected String doInBackground(Void... params) {
        try {
            url = new URL("http://ark.gamepedia.com/Dinosaurs");
            Document doc = Jsoup.parse(url, 3000);
            //Elements title = doc.select();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {

    }
}

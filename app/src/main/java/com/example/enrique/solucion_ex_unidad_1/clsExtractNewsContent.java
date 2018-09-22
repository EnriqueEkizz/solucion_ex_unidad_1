package com.example.enrique.solucion_ex_unidad_1;

import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class clsExtractNewsContent extends AsyncTask<Void,Void,Void> {

    private String url;

    public void setUrl(String xUrl){
        this.url = xUrl;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            Document doc = (Document) Jsoup.connect(url).get();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}

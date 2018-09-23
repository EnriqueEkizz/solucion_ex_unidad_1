package com.example.enrique.solucion_ex_unidad_1;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class contentNewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_news);
    }

    /////////////////////////////////////////////////////////////////////////////// CLASE EXTRAER LISTA NOTICIAS
    public class clsExtractNewsList extends AsyncTask<Void, Void, Void> {
        private String url;
        public Elements newslist;
        private Document doc;
        private String tagclassnew_gestion = "article.news-detail.gestion.default";

        public clsExtractNewsList(String xUrl) {
            this.url = xUrl;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                doc = (Document) Jsoup.connect(this.url).get();
                newslist = doc.select(tagclassnew_gestion);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            String titulo, summary, hora, contenido;
            List<String> imagesUrl;

            //Obteniendo detalle de noticias (Hora)
            for (Element element : this.newslist) {
                titulo = element.select("div.sf.elemento.generico").select("h1").text();
                summary = element.select("div.sf.elemento.generico").select("h2").select("a").text();
                hora = element.select("div.news-column").select("div.news-author-date ").select("time").select("span").text();

                //urlimagen = element.select("figure.flow-image").select("img").attr("data-src");

                //item.add(new modelItemnewslist(hora, titulo, urlimagen));
            }

            //adaptadorItemsNewsList = new rvNewslistAdaptador(item);
            //recyclerViewItemsNewsList.setAdapter(adaptadorItemsNewsList);
        }
    }
}

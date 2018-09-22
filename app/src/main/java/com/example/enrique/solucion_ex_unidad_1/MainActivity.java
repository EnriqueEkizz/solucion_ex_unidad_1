package com.example.enrique.solucion_ex_unidad_1;

import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerViewAccessibilityDelegate;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView tv_categoria;
    private RecyclerView recyclerViewItemsNewsList;
    private rvNewslistAdaptador adaptadorItemsNewsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerViewItemsNewsList = (RecyclerView)findViewById(R.id.rvNewsList);
        recyclerViewItemsNewsList.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onStart() {
        super.onStart();

        //new clsExtractNewsList("https://gestion.pe/archivo").execute();
        new clsExtractNewsList("https://gestion.pe/archivo/todas/2018-09-21").execute();
    }

    // Inflar menú
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.gestion_menu, menu);
        return true;
    }

    // Programando Click sobre categorias
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        tv_categoria = (TextView) findViewById(R.id.tv_categoria);

        switch (item.getItemId()) {
            case R.id.cat_economia://///////////////////////////////////// <-------------------
                return true;
            case R.id.cat_economia_a:
                //Actualizar textview categoria elegida
                tv_categoria.setText(R.string.str_cat_economia_a);
                return true;
            case R.id.cat_economia_b:
                //Actualizar textview categoria elegida
                tv_categoria.setText(R.string.str_cat_economia_b);
                return true;
            case R.id.cat_economia_c:
                //Actualizar textview categoria elegida
                tv_categoria.setText(R.string.str_cat_economia_c);
                return true;
            case R.id.cat_mundo://///////////////////////////////////// <-------------------
                //Actualizar textview categoria elegida
                return true;
            case R.id.cat_mundo_a:
                //Actualizar textview categoria elegida
                tv_categoria.setText(R.string.str_cat_mundo_a);
                return true;
            case R.id.cat_mundo_b:
                //Actualizar textview categoria elegida
                tv_categoria.setText(R.string.str_cat_mundo_b);
                return true;
            case R.id.cat_mundo_c:
                //Actualizar textview categoria elegida
                tv_categoria.setText(R.string.str_cat_mundo_c);
                return true;
            case R.id.cat_mundo_d:
                //Actualizar textview categoria elegida
                tv_categoria.setText(R.string.str_cat_mundo_d);
                return true;
            case R.id.cat_peru://///////////////////////////////////// <-------------------
                return true;
            case R.id.cat_peru_a:
                //Actualizar textview categoria elegida
                tv_categoria.setText(R.string.str_cat_peru_a);
                return true;
            case R.id.cat_tudinero://///////////////////////////////////// <-------------------
                return true;
            case R.id.cat_tudinero_a:
                //Actualizar textview categoria elegida
                tv_categoria.setText(R.string.str_cat_tudinero_a);
                return true;
            case R.id.cat_tudinero_b:
                //Actualizar textview categoria elegida
                tv_categoria.setText(R.string.str_cat_tudinero_b);
                return true;
            case R.id.cat_tecnologia://///////////////////////////////////// <-------------------
                //Actualizar textview categoria elegida
                tv_categoria.setText(R.string.str_cat_tecnologia);
                return true;
            default:///////////////////////////////////////
                return super.onOptionsItemSelected(item);
        }
    }

    /////////////////////////////////////////////////////////////////////////////// CLASE EXTRAER LISTA NOTICIAS
    public class clsExtractNewsList extends AsyncTask<Void, Void, Void> {
        private String url;
        public Elements newslist;
        private Document doc;
        private String tagclassnew_gestion = "article.flow.section-flow.clearfix";

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
            String hora, titulo, urlimagen;
            List<modelItemnewslist> item = new ArrayList<>();

            //Obteniendo detalle de noticias (Hora)
            for (Element element : this.newslist) {
                hora = element.select("div.flow-detail").select("time.flow-date").text();
                titulo = element.select("div.flow-detail").select("a.page-link").text();
                urlimagen = element.select("figure.flow-image").select("img").attr("data-src");

                item.add(new modelItemnewslist(hora, titulo, urlimagen));
            }

            adaptadorItemsNewsList = new rvNewslistAdaptador(item);
            recyclerViewItemsNewsList.setAdapter(adaptadorItemsNewsList);
        }
    }
}

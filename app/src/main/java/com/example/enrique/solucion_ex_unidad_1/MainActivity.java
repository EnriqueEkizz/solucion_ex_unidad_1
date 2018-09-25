package com.example.enrique.solucion_ex_unidad_1;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
    private String newsPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewItemsNewsList = (RecyclerView)findViewById(R.id.rvNewsList);
        recyclerViewItemsNewsList.setLayoutManager(new LinearLayoutManager(this));

        this.newsPath = "https://gestion.pe/archivo";

        startXtractNews();
    }

    /*@Override
    protected void onStart() {
        super.onStart();


    }*/

    public void startXtractNews() {
        new clsExtractNewsList(newsPath).execute();
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
                this.newsPath="https://gestion.pe/economia/mercados";
                startXtractNews();
                return true;
            case R.id.cat_economia_b:
                //Actualizar textview categoria elegida
                tv_categoria.setText(R.string.str_cat_economia_b);
                this.newsPath="https://gestion.pe/economia/empresas";
                startXtractNews();
                return true;
            case R.id.cat_economia_c:
                //Actualizar textview categoria elegida
                tv_categoria.setText(R.string.str_cat_economia_c);
                this.newsPath="https://gestion.pe/economia/management-empleo";
                startXtractNews();
                return true;
            case R.id.cat_mundo://///////////////////////////////////// <-------------------
                //Actualizar textview categoria elegida
                return true;
            case R.id.cat_mundo_a:
                //Actualizar textview categoria elegida
                this.newsPath="https://gestion.pe/mundo/eeuu";
                startXtractNews();
                tv_categoria.setText(R.string.str_cat_mundo_a);
                return true;
            case R.id.cat_mundo_b:
                //Actualizar textview categoria elegida
                this.newsPath="https://gestion.pe/mundo/mexico";
                startXtractNews();
                tv_categoria.setText(R.string.str_cat_mundo_b);
                return true;
            case R.id.cat_mundo_c:
                //Actualizar textview categoria elegida
                this.newsPath="https://gestion.pe/mundo/espana";
                startXtractNews();
                tv_categoria.setText(R.string.str_cat_mundo_c);
                return true;
            case R.id.cat_mundo_d:
                //Actualizar textview categoria elegida
                this.newsPath="https://gestion.pe/mundo/internacional";
                startXtractNews();
                tv_categoria.setText(R.string.str_cat_mundo_d);
                return true;
            case R.id.cat_peru://///////////////////////////////////// <-------------------
                return true;
            case R.id.cat_peru_a:
                //Actualizar textview categoria elegida
                this.newsPath="https://gestion.pe/peru/politica";
                startXtractNews();
                tv_categoria.setText(R.string.str_cat_peru_a);
                return true;
            case R.id.cat_tudinero://///////////////////////////////////// <-------------------
                return true;
            case R.id.cat_tudinero_a:
                //Actualizar textview categoria elegida
                tv_categoria.setText(R.string.str_cat_tudinero_a);
                this.newsPath="https://gestion.pe/tu-dinero/finanzas-personales";
                startXtractNews();
                return true;
            case R.id.cat_tudinero_b:
                //Actualizar textview categoria elegida
                this.newsPath="https://gestion.pe/tu-dinero/inmobiliarias";
                startXtractNews();
                tv_categoria.setText(R.string.str_cat_tudinero_b);
                return true;
            case R.id.cat_tecnologia://///////////////////////////////////// <-------------------
                //Actualizar textview categoria elegida
                tv_categoria.setText(R.string.str_cat_tecnologia);
                this.newsPath="https://gestion.pe/tecnologia";
                startXtractNews();
                return true;
            case R.id.cat_ultimasnoticias:
                //Actualizar textview categoria elegida
                tv_categoria.setText(R.string.str_cat_inicial);
                this.newsPath="https://gestion.pe/archivo";
                startXtractNews();
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
            String hora, titulo, urlimagen, link;
            byte carrusel, tipoMedia;
            List<modelItemnewslist> item = new ArrayList<>();

            //Obteniendo detalle de noticias (Hora)
            for (Element element : this.newslist) {
                hora = element.select("div.flow-detail").select("time.flow-date").text();
                titulo = element.select("div.flow-detail").select("a.page-link").text();
                urlimagen = element.select("figure.flow-image").select("img").attr("data-src");
                link = element.select("div.flow-detail").select("a.page-link").attr("href");

                carrusel = (byte)element.select("figure.flow-image").select("i.icon-photos").size();

                if (carrusel == 1) {
                    tipoMedia = 1; // Es carrusel de imagenes
                } else {
                    carrusel = (byte)element.select("figure.flow-image").select("i.icon-video").size();
                    if (carrusel == 1) {
                        tipoMedia = 2; // Es video
                    } else {
                        tipoMedia = 0; // Es solo imagen
                    }
                }
                item.add(new modelItemnewslist(hora, titulo, urlimagen, link, tipoMedia));
            }

            adaptadorItemsNewsList = new rvNewslistAdaptador(item, getApplicationContext());
            recyclerViewItemsNewsList.setAdapter(adaptadorItemsNewsList);
        }
    }
}

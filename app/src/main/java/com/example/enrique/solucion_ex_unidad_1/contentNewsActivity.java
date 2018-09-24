package com.example.enrique.solucion_ex_unidad_1;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class contentNewsActivity extends AppCompatActivity {

    Bundle bundle;
    TextView tvTitle;
    TextView tvSummary;
    TextView tvDate;
    TextView tvContent;
    WrapContentHeightViewPager  viewPager;
    contentNewsSliderImage contentNewsSliderImage;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_news);

        tvTitle = (TextView)findViewById(R.id.tvTitleContentNews);
        tvSummary = (TextView)findViewById(R.id.tvSummaryContentNews);
        tvDate = (TextView)findViewById(R.id.tvDateContentNews);

        viewPager = (WrapContentHeightViewPager)findViewById(R.id.vpImagesContentNews);
        tvContent = (TextView)findViewById(R.id.tvContentContentNews);

        context = this;

        Intent intent = getIntent();
        bundle = intent.getExtras();

        new clsExtractNewsContent(bundle.getString("link"),bundle.getByte("tipoMedia")).execute();
    }

    /////////////////////////////////////////////////////////////////////////////// CLASE EXTRAER LISTA NOTICIAS
    public class clsExtractNewsContent extends AsyncTask<Void, Void, Void> {
        private String url;
        private byte tipoMedia;
        public Elements newslist;
        private Document doc;
        private String tagclassnew_gestion = "article.news-detail.gestion.default";

        public clsExtractNewsContent(String xUrl, byte xTipoMedia) {
            this.url = xUrl;
            this.tipoMedia = xTipoMedia;
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
            String titulo, summary, hora, contenido = "";
            List<String> linksImage = new ArrayList<>();
            List<String> descriptionImage = new ArrayList<>();

            byte tipoMedia;

            List<String> imagesUrl;

            //Obteniendo detalle de noticias
            Element element = this.newslist.first();

            titulo = element.select("div.sf.elemento.generico").select("h1").text();
            summary = element.select("div.sf.elemento.generico").select("h2").text();
            hora = element.select("div.news-column").select("div.news-author-date").select("time").select("span").text();

            //Verificar si foto es solo foto, carrusel foto o video
            switch (this.tipoMedia) {
                case 0: // No carrusel
                    linksImage.add(element.select("div.image_pri").select("img").attr("src"));
                    descriptionImage.add(element.select("figcaption").select("p").text());
                    break;
                case 1: // Si carrusel
                    Elements gallery = element.select("div.article-media.content-gallery-ads").select("div.slider-pane").select("div.slide");
                    int gallerysize = gallery.size();

                    gallerysize--;
                    for (int i = 1; i < gallerysize; i++) {
                        linksImage.add(gallery.get(i).select("img").attr("src"));
                        descriptionImage.add(gallery.get(i).select("p").text());
                    }
                    break;
                default: // Video

            }

            Elements content = element.select("div.news-text-content").select("p");
            for (Element e : content) {
                contenido = contenido + "\n\n" +  e.text();
            }

            tvTitle.setText(titulo);
            tvSummary.setText(summary);
            tvDate.setText(hora);
            tvContent.setText(contenido);

            contentNewsSliderImage = new contentNewsSliderImage(context, linksImage, descriptionImage);
            viewPager.setAdapter(contentNewsSliderImage);
            viewPager.measure(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        }
    }
}

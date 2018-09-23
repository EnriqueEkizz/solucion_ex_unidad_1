package com.example.enrique.solucion_ex_unidad_1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class rvNewslistAdaptador extends RecyclerView.Adapter<rvNewslistAdaptador.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView hora, titulo, imagen;
        private ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            hora = (TextView)itemView.findViewById(R.id.tvHourNewsList);
            titulo = (TextView)itemView.findViewById(R.id.tvTitleNewsList);
            imageView = (ImageView)itemView.findViewById(R.id.imgPhotoNewsList);
        }
    }

    public List<modelItemnewslist> itemsNewsList;

    public rvNewslistAdaptador(List<modelItemnewslist> itemsNewsList) {
        this.itemsNewsList = itemsNewsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.article_news, viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.hora.setText(itemsNewsList.get(i).getHora());
        viewHolder.titulo.setText(itemsNewsList.get(i).getTitulo());
        Picasso.get().load(itemsNewsList.get(i).getImagen()).into(viewHolder.imageView);

        //Click
        viewHolder.itemView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String newsLink = itemsNewsList.get(i).getLink();
            }
        });
    }

    private void loadImagenFromUrl(String imagen) {

    }

    @Override
    public int getItemCount() {
        return itemsNewsList.size();
    }
}

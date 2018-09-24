package com.example.enrique.solucion_ex_unidad_1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class contentNewsSliderImage extends PagerAdapter {

    private List<String> linksImage = new ArrayList<>();
    private List<String> descriptionImage = new ArrayList<>();
    private Context context;
    private LayoutInflater layoutInflater;

    public contentNewsSliderImage(Context context, List<String> xLinksImage, List<String> xDescription) {
        this.context = context;
        this.linksImage = xLinksImage;
        this.descriptionImage = xDescription;
    }

    @Override
    public int getCount() {
        return this.linksImage.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return (view==(LinearLayout)o);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.swipe_imagenews, container, false);


        ImageView imageView = (ImageView)view.findViewById(R.id.ivImageNews);

        TextView tvCountImage = (TextView)view.findViewById(R.id.tvImageCount);
        TextView tvTotalImage = (TextView)view.findViewById(R.id.tvImageTotalCount);
        TextView tvImageDescription = (TextView)view.findViewById(R.id.tvImageDescription);

        tvCountImage.setText(String.valueOf(position + 1));
        tvTotalImage.setText(String.valueOf(linksImage.size()));

        tvImageDescription.setText(descriptionImage.get(position));
        Picasso.get().load(linksImage.get(position)).into(imageView);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        //super.destroyItem(container, position, object);
        container.removeView((LinearLayout)object);
    }
}

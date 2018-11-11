package com.example.sinukov.acatalog.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sinukov.acatalog.R;

public class ItemImageCarAdapter extends PagerAdapter {

    private int[] image_res = {R.drawable.photo1, R.drawable.photo2, R.drawable.photo3, R.drawable.photo4};
    private Context ctx;

    public ItemImageCarAdapter(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        return image_res.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater layoutInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View item_view = layoutInflater.inflate(R.layout.swipe_layout, container, false);

        ImageView imageView = (ImageView) item_view.findViewById(R.id.imageBrand);
        TextView textView = (TextView) item_view.findViewById(R.id.titleBrand);
        imageView.setImageResource(image_res[position]);
        textView.setText(ctx.getResources().getString(R.string.image_article) + position);
        container.addView(item_view);
        return item_view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
package com.example.sinukov.acatalog.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sinukov.acatalog.R;
import com.example.sinukov.acatalog.model.BrandModel;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class BrandsRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<BrandModel> brands;
    private ClickListener clickListener;
    private Context context;

    public BrandsRecyclerAdapter(Context applicationContext, List<BrandModel> recyclerBrandsAuto) {
        this.context = applicationContext;
        this.brands = recyclerBrandsAuto;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_brand, parent, false);
        return new BrandViewHolder(view);
    }

    public BrandModel getItem(int position) {
        return brands.get(position);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        BrandModel mBrand = brands.get(position);
        BrandViewHolder brandViewHolder = (BrandViewHolder) holder;
        brandViewHolder.imageBrand.setImageResource(R.drawable.ic_launcher);

        String strChange = mBrand.getImage().toUpperCase().replaceAll("_", " ");
        brandViewHolder.titleBrand.setText(strChange);
        Uri uriImage = Uri.parse("file:///" + mBrand.getPath());

        try {
            InputStream is = context.getContentResolver().openInputStream(uriImage);
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            is.close();
            brandViewHolder.imageBrand.setImageBitmap(bitmap);

        } catch (IOException e) {
            Log.d("AsyncTaskT", "e " + e);
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return brands.size();
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public interface ClickListener {
        void itemClicked(View view, int position);
    }

    public class BrandViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        RecyclerView rv;
        ImageView imageBrand;
        TextView titleBrand;

        BrandViewHolder(View itemView) {
            super(itemView);
            rv = (RecyclerView) itemView.findViewById(R.id.brandsAuto);
            imageBrand = (ImageView) itemView.findViewById(R.id.imageBrand);
            titleBrand = (TextView) itemView.findViewById(R.id.titleBrand);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) {
                clickListener.itemClicked(view, getPosition());
            }
        }

        @Override
        public boolean onLongClick(View view) {
            if (clickListener != null) {
                clickListener.itemClicked(view, getPosition());
            }
            return false;
        }
    }
}

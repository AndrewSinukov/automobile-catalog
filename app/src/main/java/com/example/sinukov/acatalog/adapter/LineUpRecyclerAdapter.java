package com.example.sinukov.acatalog.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sinukov.acatalog.R;
import com.example.sinukov.acatalog.model.LineUpModel;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class LineUpRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<LineUpModel> lineUps;
    private ClickListener clickListener;
    private Context context;

    public LineUpRecyclerAdapter(Context context, List<LineUpModel> recyclerLineUp) {
        this.context = context;
        this.lineUps = recyclerLineUp;
    }

    public LineUpModel getItem(int position) {
        return lineUps.get(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_line_up, parent, false);
        return new LineUpViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        LineUpModel mLineUp = lineUps.get(position);
        LineUpViewHolder lineUpViewHolder = (LineUpViewHolder) holder;
        lineUpViewHolder.imageBrand.setImageResource(R.drawable.ic_launcher);
        lineUpViewHolder.titleBrand.setText(mLineUp.getTitle());

        Uri uriImage = Uri.parse(mLineUp.getImage());
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 8;
        options.outHeight = 50;
        options.outWidth = 50;

        try {
            InputStream is = context.getContentResolver().openInputStream(uriImage);
            Bitmap bitmap = BitmapFactory.decodeStream(is, null, options);
            is.close();
            lineUpViewHolder.imageBrand.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return lineUps.size();
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public interface ClickListener {
        void itemClicked(View view, int position);
    }

    public class LineUpViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        RecyclerView rv;
        ImageView imageBrand;
        TextView titleBrand;

        LineUpViewHolder(View itemView) {
            super(itemView);
            rv = (RecyclerView) itemView.findViewById(R.id.brandsAuto);
            imageBrand = (ImageView) itemView.findViewById(R.id.imageBrand);
            titleBrand = (TextView) itemView.findViewById(R.id.titleBrand);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) {
                clickListener.itemClicked(view, getPosition());
            }
        }
    }
}
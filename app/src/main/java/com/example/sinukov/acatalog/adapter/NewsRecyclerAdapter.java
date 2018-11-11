package com.example.sinukov.acatalog.adapter;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sinukov.acatalog.R;
import com.example.sinukov.acatalog.helper.ParserHelper;
import com.example.sinukov.acatalog.model.NewsModel;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class NewsRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<NewsModel> news;
    private ClickListener clickListener;
    public static final SimpleDateFormat TERM_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

    public NewsRecyclerAdapter(Context context, List<NewsModel> recyclerNews) {
        this.context = context;
        this.news = recyclerNews;
    }


    public void delete(int position) {
        news.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        return new NewsViewHolder(view);
    }

    public NewsModel getItem(int position) {
        return news.get(position);
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        NewsModel mNews = news.get(position);
        NewsViewHolder newsViewHolder = (NewsViewHolder) holder;
        newsViewHolder.titleNews.setText(mNews.getTitle());
        String date = TERM_DATE_FORMAT.format(mNews.getDateCreate());
        newsViewHolder.dateNews.setText(date);
        newsViewHolder.descriptionNews.setText(mNews.getDescription());

        newsViewHolder.descriptionNews.setSingleLine(true);
        newsViewHolder.descriptionNews.setEllipsize(TextUtils.TruncateAt.END);
        newsViewHolder.descriptionNews.setMaxLines(3);

        newsViewHolder.imageNews.setImageResource(R.drawable.ic_launcher);

        Uri uriImage = Uri.parse(mNews.getImage());
        BitmapFactory.Options options = new BitmapFactory.Options();

        try {
            InputStream is = context.getContentResolver().openInputStream(uriImage);
            Bitmap bitmap = BitmapFactory.decodeStream(is, null, options);
            is.close();
            newsViewHolder.imageNews.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    public interface ClickListener {
        void itemClicked(View view, int position);
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener, View.OnClickListener {
        private RecyclerView rv;
        private TextView titleNews, dateNews, descriptionNews;
        private ImageView imageNews;

        NewsViewHolder(View itemView) {
            super(itemView);
            ParserHelper.getInstance().init(context);

            rv = (RecyclerView) itemView.findViewById(R.id.listNews);
            imageNews = (ImageView) itemView.findViewById(R.id.imageNews);
            titleNews = (TextView) itemView.findViewById(R.id.titleNews);
            dateNews = (TextView) itemView.findViewById(R.id.dateNews);
            descriptionNews = (TextView) itemView.findViewById(R.id.descriptionNews);

            itemView.setOnLongClickListener(this);
        }

        @Override
        public boolean onLongClick(View view) {
            if (clickListener != null) {
                clickListener.itemClicked(view, getPosition());
            }
            return false;
        }

        @Override
        public void onClick(View v) {
            if (clickListener != null) {
                delete(getPosition());
            }
        }
    }
}
package com.example.sinukov.acatalog.async_task;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.sinukov.acatalog.Constants;
import com.example.sinukov.acatalog.R;
import com.example.sinukov.acatalog.db.DatabaseHelper;
import com.example.sinukov.acatalog.db.DatabaseManager;
import com.example.sinukov.acatalog.model.BrandModel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.SQLException;

public class ParseBrands extends AsyncTask<Void, Integer, Bitmap> {
    private Bitmap bitmap;
    private ProgressDialog progressDialog;
    private Context ctx;
    private String url;
    private FragmentActivity fragmentActivity;

    public ParseBrands(Context context, String url, FragmentActivity fragmentActivity) {
        this.ctx = context;
        this.url = url;
        this.fragmentActivity = fragmentActivity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(ctx);
        progressDialog.setTitle(ctx.getResources().getString(R.string.progress_dialog_title));
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setProgress(0);
        progressDialog.setMessage(ctx.getResources().getString(R.string.progress_dialog_message));
        progressDialog.setIndeterminate(false);
        progressDialog.show();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        progressDialog.setProgress(values[0]);
    }

    @Override
    protected Bitmap doInBackground(Void... params) {

        try {
            Document document = Jsoup.connect(url).get();
            Element allMarks = document.getElementById(Constants.PARSE.ELEMENT_BY_ID);
            Elements imgs = allMarks.getElementsByTag(Constants.PARSE.ELEMENT_BY_TAG);
            BrandModel mBrand = new BrandModel();

            int allImages = imgs.size();
            progressDialog.setMax(allImages);

            int count = 0;
            for (Element img : imgs) {
                int progress = count * 100 / allImages;
                publishProgress(progress);
                String linkText = img.attr(Constants.PARSE.ELEMENT_ATTTR_ALT);
                String strChange = linkText.toLowerCase().replaceAll("\\s+", "_");
                String linkHref = img.attr(Constants.PARSE.ELEMENT_ATTTR_SRC);
                InputStream input = new URL(Constants.URL.BASE_URL + linkHref).openStream();
                bitmap = BitmapFactory.decodeStream(input);

                File dir = new File(Constants.PARSE.FILE_DIR);
                if (!dir.exists()) {
                    dir.mkdirs();
                }

                File file = new File(dir, Constants.PARSE.FILE_NAME);
                if (!file.exists()) {
                    dir.createNewFile();
                }

                mBrand.setDateCreate(System.currentTimeMillis());
                mBrand.setImage(strChange);
                mBrand.setPath(String.valueOf(file));
                mBrand.setName(System.currentTimeMillis() + ".jpg");

                DatabaseManager.getInstance().getHelper().getBrandDao().create(mBrand);

                OutputStream outputStream = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                outputStream.flush();
                outputStream.close();
                count++;
            }
        } catch (IOException ex) {
            Log.d("IOException", "ex " + ex);
            ex.printStackTrace();
        } catch (SQLException e) {
            Log.d("SQLException", "ex " + e);
            e.printStackTrace();
        }
        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        Toast.makeText(ctx, ctx.getResources().getString(R.string.toast_save_images), Toast.LENGTH_SHORT).show();
        progressDialog.dismiss();
        DatabaseHelper databaseHelper = new DatabaseHelper(ctx);
        databaseHelper.close();

        Intent intent = fragmentActivity.getIntent();
        fragmentActivity.finish();
        fragmentActivity.startActivity(intent);
    }
}
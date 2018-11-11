package com.example.sinukov.acatalog.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.sinukov.acatalog.Constants;
import com.example.sinukov.acatalog.R;
import com.example.sinukov.acatalog.db.DatabaseHelper;
import com.example.sinukov.acatalog.db.DatabaseManager;
import com.example.sinukov.acatalog.model.NewsModel;

public class AddNewsFragment extends Fragment implements View.OnClickListener {

    private NewsModel mNewsModel = null;
    private int brandId;
    private String brandName;
    private EditText titleNews, descriptionNews;
    private ImageView imageNews;
    private Button btUploadImage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_news, container, false);

        mNewsModel = new NewsModel();

        if (getArguments() != null) {
            brandId = getArguments().getInt("brandId", 0);
            brandName = getArguments().getString("brandName", "");
        }

        mNewsModel.setDateCreate(System.currentTimeMillis());
        mNewsModel.setBrandId(brandId);
        mNewsModel.setBrandName(brandName);

        titleNews = (EditText) view.findViewById(R.id.titleNews);
        descriptionNews = (EditText) view.findViewById(R.id.descriptionNews);

        imageNews = (ImageView) view.findViewById(R.id.imageNews);
        imageNews.setVisibility(View.GONE);

        view.findViewById(R.id.addArticle).setOnClickListener(this);
        view.findViewById(R.id.cancelArticle).setOnClickListener(this);

        btUploadImage = (Button) view.findViewById(R.id.btUploadImage);
        imageNews.setOnClickListener(this);
        btUploadImage.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addArticle:
                String getTitleNews = titleNews.getText().toString();
                String getDescriptionNews = descriptionNews.getText().toString();

                if (getTitleNews.isEmpty()) {
                    Toast.makeText(getActivity(), getResources().getString(R.string.field_required_title), Toast.LENGTH_SHORT).show();
                    return;
                }

                if (getDescriptionNews == null) {
                    Toast.makeText(getActivity(), getResources().getString(R.string.field_required_image), Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!getTitleNews.isEmpty() && getDescriptionNews != null) {

                    mNewsModel.setTitle(getTitleNews);
                    mNewsModel.setDescription(getDescriptionNews);
                    try {
                        // To do update
//                        if (idArticle != 0) {
//                            BrandModel brandUpdate = DatabaseManager.getInstance().getHelper().getBrandDao().queryForId(idArticle);
//                            brandUpdate.setName(getTitleNews);
//                            brandUpdate.setDateCreate(System.currentTimeMillis());
//                            if (selectImage == null) {
//                                brandUpdate.setImage(String.valueOf(uriImage));
//                            } else {
//                                brandUpdate.setImage(String.valueOf(selectImage));
//                            }
//                            DatabaseManager.getInstance().getHelper().getBrandDao().update(brandUpdate);
//                        } else {
                        DatabaseManager.getInstance().getHelper().getNewsDao().create(mNewsModel);
                        DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
                        databaseHelper.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    Toast.makeText(getActivity(), getResources().getString(R.string.toast_success_news_save), Toast.LENGTH_SHORT).show();
                    getActivity().onBackPressed();
                } else {
                    Toast.makeText(getActivity(), getResources().getString(R.string.field_required), Toast.LENGTH_SHORT).show();
                    return;
                }
                break;
            case R.id.cancelArticle:
                getActivity().onBackPressed();
                break;
            case R.id.btUploadImage:
            case R.id.imageNews:
                Intent gallertIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(gallertIntent, Constants.ACTIVITY_FOR_RESULT.RESULT_LOAD_IMAGE);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.ACTIVITY_FOR_RESULT.RESULT_LOAD_IMAGE && data != null) {
            Uri selectImage = data.getData();
            mNewsModel.setImage(String.valueOf(selectImage));
            imageNews.setImageURI(selectImage);
            imageNews.setVisibility(View.VISIBLE);
            btUploadImage.setVisibility(View.GONE);
        }
    }
}
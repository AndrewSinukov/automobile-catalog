package com.example.sinukov.acatalog.fragment;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
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
import com.example.sinukov.acatalog.model.BrandModel;
import com.example.sinukov.acatalog.model.LineUpModel;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class AddCarFragment extends Fragment implements View.OnClickListener {

    private LineUpModel mLineUp = null;
    private EditText editLineUp;
    private ImageView imageToUpload;
    private Button btUploadImage;
    private Uri uriImage;
    private String titleArticle, imageArticle;
    private int idArticle = 0;
    private Uri selectImage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_car, container, false);

        mLineUp = new LineUpModel();
        if (getArguments() != null) {
            int brandId = getArguments().getInt("brandId", 0);
            String brandName = getArguments().getString("brandName", "");

            mLineUp.setDateCreate(System.currentTimeMillis());
            mLineUp.setBrandId(brandId);
            mLineUp.setBrandName(brandName);

            mLineUp.setDateCreate(System.currentTimeMillis());

            DatabaseManager.getInstance().init(getActivity());
            List<BrandModel> listBrand = DatabaseManager.getBrand(idArticle);

            if (listBrand.size() != 0) {
                for (int g = 0; g < listBrand.size(); g++) {
                    idArticle = listBrand.get(g).getId();
                    imageArticle = listBrand.get(g).getImage();
                    titleArticle = listBrand.get(g).getName();
                    uriImage = Uri.parse(imageArticle);
                }

                mLineUp.setTitle(titleArticle);
                editLineUp = (EditText) view.findViewById(R.id.editArticle);
                editLineUp.setText(titleArticle);

                imageToUpload = (ImageView) view.findViewById(R.id.imageToUpload);
                imageToUpload.setVisibility(View.VISIBLE);

                btUploadImage = (Button) view.findViewById(R.id.btUploadImage);

                try {
                    InputStream is = getContext().getContentResolver().openInputStream(uriImage);
                    Bitmap bitmap = BitmapFactory.decodeStream(is);
                    is.close();
                    imageToUpload.setImageBitmap(bitmap);
                    if (uriImage != null) {
                        btUploadImage.setVisibility(View.GONE);
                    } else {
                        btUploadImage.setVisibility(View.VISIBLE);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mLineUp.setImage(imageArticle);
            }

            editLineUp = (EditText) view.findViewById(R.id.editLineUp);

            imageToUpload = (ImageView) view.findViewById(R.id.imageToUpload);
            imageToUpload.setVisibility(View.GONE);

            view.findViewById(R.id.addArticle).setOnClickListener(this);
            view.findViewById(R.id.cancelArticle).setOnClickListener(this);

            btUploadImage = (Button) view.findViewById(R.id.btUploadImage);
            imageToUpload.setOnClickListener(this);
            btUploadImage.setOnClickListener(this);
        }

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addArticle:
                String strArticle = editLineUp.getText().toString();
                Drawable strImgName = imageToUpload.getDrawable();
                if (strArticle.isEmpty()) {
                    Toast.makeText(getActivity(), getResources().getString(R.string.field_required_title), Toast.LENGTH_SHORT).show();
                    return;
                }

                if (strImgName == null) {
                    Toast.makeText(getActivity(), getResources().getString(R.string.field_required_image), Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!strArticle.isEmpty() && strImgName != null) {
                    String title = editLineUp.getText().toString();
                    mLineUp.setTitle(title);
                    try {
                        if (idArticle != 0) {
                            BrandModel brandUpdate = DatabaseManager.getInstance().getHelper().getBrandDao().queryForId(idArticle);
                            brandUpdate.setName(title);
                            brandUpdate.setDateCreate(System.currentTimeMillis());
                            if (selectImage == null) {
                                brandUpdate.setImage(String.valueOf(uriImage));
                            } else {
                                brandUpdate.setImage(String.valueOf(selectImage));
                            }
                            DatabaseManager.getInstance().getHelper().getBrandDao().update(brandUpdate);
                        } else {
                            DatabaseManager.getInstance().getHelper().getLineUpDao().create(mLineUp);
                            DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
                            databaseHelper.close();
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    Toast.makeText(getActivity(), getResources().getString(R.string.toast_success_car_save), Toast.LENGTH_SHORT).show();
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
            case R.id.imageToUpload:
                Intent gallertIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(gallertIntent, Constants.ACTIVITY_FOR_RESULT.RESULT_LOAD_IMAGE);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.ACTIVITY_FOR_RESULT.RESULT_LOAD_IMAGE && data != null) {
            selectImage = data.getData();
            mLineUp.setImage(String.valueOf(selectImage));
            imageToUpload.setImageURI(selectImage);
            imageToUpload.setVisibility(View.VISIBLE);
            btUploadImage.setVisibility(View.GONE);
        }
    }
}
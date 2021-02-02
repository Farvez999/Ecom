package com.korearbazar.ecom.VendorProfile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.korearbazar.ecom.R;

public class VendorAddNewProductsActivity extends AppCompatActivity {

    EditText pNEnglish,pNBagla,pSku,pCategory,pSubCategory,pChildCategory,pStock,pDescriptionEnglish,pDescriptionBangla,pPolicyEnglish,pPolicyBangla,pCurrentPrice,pPreviousPrice,pTags;
    ImageView pImageView,pGalleryImages;
    TextView pUploadImage,pSetGalleryImages,createProduct;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_add_new_products);

        pNEnglish = findViewById(R.id.pNEnglish);
        pNBagla = findViewById(R.id.pNBagla);
        pSku = findViewById(R.id.pSku);
        pCategory = findViewById(R.id.pCategory);
        pSubCategory = findViewById(R.id.pSubCategory);
        pChildCategory = findViewById(R.id.pChildCategory);
        pStock = findViewById(R.id.pStock);
        pDescriptionEnglish = findViewById(R.id.pDescriptionEnglish);
        pDescriptionBangla = findViewById(R.id.pDescriptionBangla);
        pPolicyEnglish = findViewById(R.id.pPolicyEnglish);
        pPolicyBangla = findViewById(R.id.pPolicyBangla);

        pImageView = findViewById(R.id.pImageView);
        pGalleryImages = findViewById(R.id.pGalleryImages);

        pUploadImage = findViewById(R.id.pUploadImage);
        pSetGalleryImages = findViewById(R.id.pSetGalleryImages);

        createProduct = findViewById(R.id.createProduct);

    }
}
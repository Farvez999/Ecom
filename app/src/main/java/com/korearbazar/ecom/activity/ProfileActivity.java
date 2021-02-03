package com.korearbazar.ecom.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.korearbazar.ecom.ApiInterface;
import com.korearbazar.ecom.R;
import com.korearbazar.ecom.UserProfile.AffiliateCodeActivity;
import com.korearbazar.ecom.UserProfile.EditProfileActivity;
import com.korearbazar.ecom.UserProfile.FavoriteSellerActivity;
import com.korearbazar.ecom.UserProfile.MessageActivity;
import com.korearbazar.ecom.UserProfile.ResetPasswordActivity;
import com.korearbazar.ecom.UserProfile.UserDashboardActivity;
import com.korearbazar.ecom.UserProfile.WithdrawActivity;
import com.korearbazar.ecom.adapter.DasboardOrderAdapter;
import com.korearbazar.ecom.model.DasboardOrderModel;
import com.korearbazar.ecom.model.ProdModel;
import com.korearbazar.ecom.utils.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ProfileActivity extends AppCompatActivity {

    private TextView userDashboard,userVendorPanel,up_Withdraw,up_Message,upFSeller,upAffiliate,upEditProfile,upResetPassword;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setTitle("My Profile");

//        sessionManager = new SessionManager(this);

        userDashboard = findViewById(R.id.userDashboard);
        userDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, UserDashboardActivity.class);
                startActivity(intent);
            }
        });

        userVendorPanel = findViewById(R.id.userVendorPanel);
        userVendorPanel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, VendorActivity.class);
                startActivity(intent);
            }
        });

        up_Withdraw=findViewById(R.id.upWithdraw);
        up_Withdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, WithdrawActivity.class);
                startActivity(intent);
            }
        });

        up_Message=findViewById(R.id.upMessage);
        up_Message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, MessageActivity.class);
                startActivity(intent);
            }
        });

        upFSeller=findViewById(R.id.upFSeller);
        upFSeller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, FavoriteSellerActivity.class);
                startActivity(intent);
            }
        });

        upAffiliate = findViewById(R.id.upAffiliate);
        upAffiliate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, AffiliateCodeActivity.class);
                startActivity(intent);
            }
        });

        upEditProfile = findViewById(R.id.upEditProfile);
        upEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, EditProfileActivity.class);
                startActivity(intent);
            }
        });

        upResetPassword = findViewById(R.id.upResetPassword);
        upResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, ResetPasswordActivity.class);
                startActivity(intent);
            }
        });


    }


}
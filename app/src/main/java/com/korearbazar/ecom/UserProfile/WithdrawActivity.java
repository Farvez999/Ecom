package com.korearbazar.ecom.UserProfile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.korearbazar.ecom.ApiInterface;
import com.korearbazar.ecom.R;
import com.korearbazar.ecom.adapter.WithdhowAdapter;
import com.korearbazar.ecom.model.WithdhowModel;
import com.korearbazar.ecom.model.WithdrawResponse;
import com.korearbazar.ecom.utils.SessionManager;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class WithdrawActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TextView WNow;

    SessionManager sessionManager;
    WithdrawResponse withdrawResponsesData;
    Integer user_id;

    private RecyclerView wRecyclerView;
    private WithdhowAdapter wExampleAdapter;
    private ArrayList<WithdhowModel> wExampleList;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw);

        setTitle("Withdraw");

        sessionManager = new SessionManager(this);
        progressBar = new ProgressBar(this);


        WNow = findViewById(R.id.WNow);

        WNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WithdrawActivity.this,WithdrawCreateActivity.class);
                startActivity(intent);
            }
        });

        wRecyclerView = findViewById(R.id.wRecyclerView);
        wRecyclerView.setHasFixedSize(true);
        wRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        wExampleList = new ArrayList<>();

        WithdrawData();


    }



    //AffilateWithdrawData
    private void WithdrawData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiInterface.JSONURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        ApiInterface api = retrofit.create(ApiInterface.class);
        Call<String> call = api.getAffilateWithdraw(sessionManager.getToken());
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
//                Log.i("Responsestring", response.body().toString());
                //Toast.makeText()
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString());

                        String jsonresponse = response.body().toString();
                        affilateWithdrawData_writeRecycler(jsonresponse);

                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(WithdrawActivity.this, "Error" + t, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void affilateWithdrawData_writeRecycler(String jsonresponse) {
        try {
            JSONObject obj = new JSONObject(jsonresponse);
            JSONArray jsonArray = obj.getJSONArray("withdraws");
            //Toast.makeText(this, "affiliate_sign"+jsonArray, Toast.LENGTH_SHORT).show();

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String id = jsonObject.getString("id");
                String created_at = jsonObject.getString("created_at");
                String method = jsonObject.getString("method");
                String acc_email = jsonObject.getString("acc_email");
                String amount = jsonObject.getString("amount");
                String status = jsonObject.getString("status");

                wExampleList.add(new WithdhowModel(id,created_at,method,acc_email,amount,status));
//                Toast.makeText(this, "withdrawsID"+withdrawsID, Toast.LENGTH_SHORT).show();
//                Log.e("withdrawsID",withdrawsID);
            }

            wExampleAdapter = new WithdhowAdapter(WithdrawActivity.this, wExampleList);
            wRecyclerView.setAdapter(wExampleAdapter);



            JSONObject affiliate_sign=obj.getJSONObject("sign");
            //Toast.makeText(this, "affiliate_sign"+affiliate_sign, Toast.LENGTH_SHORT).show();

//        String affiliate_link = obj.getString("link");
//        String affiliate_html = obj.getString("html");
//        affilate_Link.setText(affiliate_link);
//        affilate_html.setText(affiliate_html);

        }catch (Exception e){

        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
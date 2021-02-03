package com.korearbazar.ecom.UserProfile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;

import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.korearbazar.ecom.Api;
import com.korearbazar.ecom.Api2;
import com.korearbazar.ecom.ApiInterface;

import com.korearbazar.ecom.R;

import com.korearbazar.ecom.SendMessageResponse;
import com.korearbazar.ecom.adapter.MessageAdapter;
import com.korearbazar.ecom.model.MessageModel;

import com.korearbazar.ecom.utils.SessionManager;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


import retrofit.RetrofitError;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static com.korearbazar.ecom.Api.getClient;

public class MessageActivity extends AppCompatActivity {

    TextView uComposeMessage;

    SessionManager sessionManager;
    MessageResponse messageResponseData;

    private RecyclerView meRecyclerView;
    private MessageAdapter meExampleAdapter;
    private ArrayList<MessageModel> meExampleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        setTitle("Messages");

        sessionManager = new SessionManager(this);

        uComposeMessage = findViewById(R.id.uComposeMessage);
        uComposeMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MessageActivity.this,MessageComposeActivity.class);
                startActivity(intent);
            }
        });

        meRecyclerView = findViewById(R.id.meRecyclerView);
        meRecyclerView.setHasFixedSize(true);
        meRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        meExampleList = new ArrayList<>();
        


        MessagesData();


    }





        private void MessagesData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiInterface.JSONURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        ApiInterface api = retrofit.create(ApiInterface.class);
        Call<String> call = api.getMessages(sessionManager.getToken());
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
//                Log.i("Responsestring", response.body().toString());
                //Toast.makeText()
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString());

                        String jsonresponse = response.body().toString();
                        affilateMessages_writeRecycler(jsonresponse);

                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(MessageActivity.this, "Error" + t, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void affilateMessages_writeRecycler(String jsonresponse) {
        try {
            JSONObject obj = new JSONObject(jsonresponse);
            JSONArray jsonArray = obj.getJSONArray("convs");
            //Toast.makeText(this, "affiliate_sign"+jsonArray, Toast.LENGTH_SHORT).show();

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String id = jsonObject.getString("id");
                String subject = jsonObject.getString("subject");
                String message = jsonObject.getString("message");

                meExampleList.add(new MessageModel(id,subject,message));
//                String withdrawsID = jsonObject.getString("id");
//                Toast.makeText(this, "withdrawsID"+withdrawsID, Toast.LENGTH_SHORT).show();
//                Log.e("withdrawsID",withdrawsID);
            }

            meExampleAdapter = new MessageAdapter(MessageActivity.this, meExampleList);
            meRecyclerView.setAdapter(meExampleAdapter);



            JSONObject affiliate_sign=obj.getJSONObject("sign");
            //Toast.makeText(this, "affiliate_sign"+affiliate_sign, Toast.LENGTH_SHORT).show();

//        String affiliate_link = obj.getString("link");
//        String affiliate_html = obj.getString("html");
//        affilate_Link.setText(affiliate_link);
//        affilate_html.setText(affiliate_html);

        }catch (Exception e){

        }
    }
}
package com.korearbazar.ecom.UserProfile;

import androidx.appcompat.app.AppCompatActivity;

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
import com.korearbazar.ecom.R;
import com.korearbazar.ecom.utils.SessionManager;

import java.util.HashMap;
import java.util.Map;

public class MessageComposeActivity extends AppCompatActivity {
    SessionManager sessionManager;
    MessageResponse messageResponseData;

    private EditText mTo,mSubjects,mMessages;
    private TextView mSendmessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_compose);
        setTitle("Message Compose");
        sessionManager=new SessionManager(this);

        mTo = findViewById(R.id.mEmail);
        mSubjects = findViewById(R.id.mSubject);
        mMessages = findViewById(R.id.mMessage);
        mSendmessage = findViewById(R.id.mSendMessage);
        mSendmessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                messageSend();
            }
        });
    }

    private void messageSend() {
        String YourUrl = "http://ecom.hrventure.xyz/api/user/user/contact";
        final String email = mTo.getText().toString();
        final String subject = mSubjects.getText().toString();
        final String message = mMessages.getText().toString();
        StringRequest request = new StringRequest(Request.Method.POST, YourUrl, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (!response.equals(null)) {
                    Log.e("Your Array Response", response);
                    Toast.makeText(MessageComposeActivity.this, "Your Array Response "+response.toString(), Toast.LENGTH_SHORT).show();
                } else {
                    Log.e("Your Array Response", "Data Null");
                    Toast.makeText(MessageComposeActivity.this, "b"+response, Toast.LENGTH_SHORT).show();
                }

            }

        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error is ", "" + error);
                Toast.makeText(MessageComposeActivity.this, "eee"+error, Toast.LENGTH_SHORT).show();
            }
        }) {

            //This is for Headers If You Needed
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json; charset=UTF-8");
                params.put("token", sessionManager.getToken());
                return params;
            }

            //Pass Your Parameters here
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("to", email);
                params.put("subject", subject);
                params.put("body", message);
                return params;
            }
        };
        Toast.makeText(MessageComposeActivity.this, ""+sessionManager.getToken(), Toast.LENGTH_SHORT).show();
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(request);
    }

//    private void messageSend() {
//        // display a progress dialog
//        final ProgressDialog progressDialog = new ProgressDialog(MessageActivity.this);
//        progressDialog.setCancelable(false);
//        progressDialog.setMessage("Please Wait");
//        progressDialog.show();
//
//        Log.e("Check Token",sessionManager.getToken());
//
//
//
//        Call<SendMessageResponse> messageResponseCall= getClient().UserPmeaasgeSend(
//                sessionManager.getToken(),
//                mTo.getText().toString().trim(),
//                mSubjects.getText().toString().trim(),
//                mMessages.getText().toString().trim()
//        );
//        messageResponseCall.enqueue(new Callback<SendMessageResponse>() {
//            @Override
//            public void onResponse(Call<SendMessageResponse> call, Response<SendMessageResponse> response) {
//                progressDialog.dismiss(); //dismiss progress dialog
//                SendMessageResponse response1=response.body();
//                // Toast.makeText(MessageActivity.this, response1.getSuccess()., Toast.LENGTH_SHORT).show();
//                Log.e("onSuccess", String.valueOf(response1.getSuccess()));
//
//            }
//
//            @Override
//            public void onFailure(Call<SendMessageResponse> call, Throwable t) {
//                Log.e("onSuccess", String.valueOf(t.getMessage()));
//
//            }
//        });
//
//
////        new retrofit.Callback<MessageResponse>() {
////            @Override
////            public void success(MessageResponse messageResponse, retrofit.client.Response response) {
////                progressDialog.dismiss(); //dismiss progress dialog
////                messageResponseData = messageResponse;
////                Toast.makeText(MessageActivity.this, messageResponse.getMessage(), Toast.LENGTH_SHORT).show();
////            }
////
////            @Override
////            public void failure(RetrofitError error) {
////                Toast.makeText(MessageActivity.this, error.toString(), Toast.LENGTH_LONG).show();
////                progressDialog.dismiss();
////            }
////        }
//
//        Log.e("Check Session", String.valueOf(sessionManager));
//    }


//    private void messageSend() {
//        String url="http://ecom.hrventure.xyz/api/user/user/contact";
//        final String phoneNumber = mTo.getText().toString();
//        final String amounts = mSubjects.getText().toString();
//        final String acc_emails = mMessages.getText().toString();
//        StringRequest request = new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                if (!response.equals(null)) {
//                    Log.e("Your Array Response", response);
//                } else {
//                    Log.e("Your Array Response", "Data Null");
//                }
//            }
//
//        }, new com.android.volley.Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.e("error is ", "" + error);
//            }
//        }) {
//
//            //This is for Headers If You Needed
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("Content-Type", "application/json; charset=UTF-8");
//                params.put("Authorization", sessionManager.getToken());
//                return params;
//            }
//
//            //Pass Your Parameters here
//            @Override
//            protected Map<String, String> getParams() {
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("to", phoneNumber);
//                params.put("subject", amounts);
//                params.put("body", acc_emails);
//                return params;
//            }
//        };
//        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
//        queue.add(request);
//    }

//    private void messageSend() {
//        // display a progress dialog
//        final ProgressDialog progressDialog = new ProgressDialog(MessageActivity.this);
//        progressDialog.setCancelable(false);
//        progressDialog.setMessage("Please Wait");
//        progressDialog.show();
//       // ApiInterface api = retrofit.create(ApiInterface.class);
////        Call<String> call = Api.getClient().getMessages(sessionManager.getToken());
////        Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
//
//        Api.getClient().UserPmeaasgeSend(
//                sessionManager.getToken(),
//                mTo.getText().toString().trim(),
//                mSubjects.getText().toString().trim(),
//                mMessages.getText().toString().trim(),
//                "email",
//                new retrofit.Callback<MessageResponse>() {
//                    @Override
//                    public void success(MessageResponse messageResponse, retrofit.client.Response response) {
//                        progressDialog.dismiss(); //dismiss progress dialog
//                        messageResponseData = messageResponse;
//                        Toast.makeText(MessageActivity.this, messageResponse.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void failure(RetrofitError error) {
//                        Toast.makeText(MessageActivity.this, error.toString(), Toast.LENGTH_LONG).show();
//                        progressDialog.dismiss();
//                    }
//                }
//                );
//
//    }
}
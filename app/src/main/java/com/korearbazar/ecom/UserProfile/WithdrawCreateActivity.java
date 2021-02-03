package com.korearbazar.ecom.UserProfile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
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

public class WithdrawCreateActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    SessionManager sessionManager;

    private Spinner spinner;
    private EditText method,amount,acc_email,reference;
    private TextView Withdraw;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw_create);
        setTitle("Withdraw Now");

        sessionManager = new SessionManager(this);
        progressBar = new ProgressBar(this);

        spinner = findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.numbers, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        method =findViewById(R.id.Sp);
        amount = findViewById(R.id.wAmmount);
        acc_email = findViewById(R.id.eAEmail);
        reference = findViewById(R.id.wReference);
        Withdraw = findViewById(R.id.withdraw);

        Withdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClickWithdraw();
            }
        });
    }

    //    public void ClickWithdraw(final String method, final String amount , final String acc_email){
//        RequestQueue MyRequestQueue = Volley.newRequestQueue(this);
//        String url = "http://ecom.hrventure.xyz/api/user/affilate/withdraw/create"; // <----enter your post url here
//        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//
//            }
//        }, new com.android.volley.Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        } ){
//            protected Map<String, String> getParams() {
//                Map<String, String> MyData = new HashMap<String, String>();
//                MyData.put("methods", method);
//                MyData.put("amount", amount);
//                MyData.put("acc_email", acc_email);
//                return MyData;
//            }
//        };
//
//
//        MyRequestQueue.add(MyStringRequest);
//    }

    private void ClickWithdraw() {
//        final ProgressDialog progressBar = new ProgressDialog(WithdrawActivity.this);
//        progressDialog.setCancelable(false); // set cancelable to false
//        progressDialog.setMessage("Please Wait"); // set message
//        progressDialog.show();

        String url = "http://ecom.hrventure.xyz/api/user/affilate/withdraw/create";
//        final String phoneNumber = method.getText().toString();
//        final String amounts = amount.getText().toString();
//        final String acc_emails = acc_email.getText().toString();
        final String phoneNumber = "cash";
        final String amounts = "200";
        final String acc_emails = "abc@gmail.com";

        StringRequest requestPostResponsen = new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String getAnswer = response.toString();
                if (getAnswer.equals("RegistrationSuccessful")) {
                    Toast.makeText(WithdrawCreateActivity.this, "Okay" + response, Toast.LENGTH_SHORT).show();
                    finish();
                }

            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(WithdrawCreateActivity.this, "Fail" + error.getStackTrace(), Toast.LENGTH_SHORT).show();
                Log.e("FAilED", String.valueOf(error.getLocalizedMessage()));
                //progressBar.dismiss();
            }
        }) {
            //To send our parametrs
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
//                params.put("customer_address",method);
                params.put("methods", phoneNumber);
                params.put("amount", amounts);
                params.put("acc_email", acc_emails);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(requestPostResponsen);
    }
//
////        final ProgressDialog progressDialog = new ProgressDialog(WithdrawActivity.this);
////        progressDialog.setCancelable(false); // set cancelable to false
////        progressDialog.setMessage("Please Wait"); // set message
////        progressDialog.show(); // show progress dialog
////
////        // Api is a class in which we define a method getClient() that returns the API Interface class object
////        // registration is a POST request type method in which we are sending our field's data
////        Api.getClient().withdraw(method.getText().toString().trim(),
////                amount.getText().toString().trim(),
////                acc_email.getText().toString().trim(),
////                reference.getText().toString().trim(),
////                "email", new Callback<WithdrawResponse>() {
////                    @Override
////                    public void success(SignUpResponse signUpResponse, retrofit.client.Response response) {
////                        // in this method we will get the response from API
////                        progressDialog.dismiss(); //dismiss progress dialog
////                        signUpResponsesData = signUpResponse;
////                        user_id = signUpResponse.getUserid();
////                        Toast.makeText(SignUpActivity.this, signUpResponse.getMessage(), Toast.LENGTH_SHORT).show();
////                        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
////                        startActivity(intent);
////                    }
////
////                    @Override
////                    public void failure(RetrofitError error) {
////                        // if error occurs in network transaction then we can get the error in this method.
////                        Toast.makeText(SignUpActivity.this, error.toString(), Toast.LENGTH_LONG).show();
////                        progressDialog.dismiss(); //dismiss progress dialog
////
////                    }
////                });
//
////        Api.getClient().withdraw(
////                method.getText().toString().trim(),
////                amount.getText().toString().trim(),
////                acc_email.getText().toString().trim(),
////                reference.getText().toString().trim(),
////                new retrofit.Callback<WithdrawResponse>() {
////                    @Override
////                    public void success(WithdrawResponse withdrawResponse, retrofit.client.Response response) {
////                        // in this method we will get the response from API
////                        progressDialog.dismiss(); //dismiss progress dialog
////                        withdrawResponsesData = withdrawResponse;
////                        user_id = withdrawResponse.getUserid();
////                        Toast.makeText(WithdrawActivity.this, withdrawResponse.getMessage(), Toast.LENGTH_SHORT).show();
////                        Intent intent = new Intent(WithdrawActivity.this, ProfileActivity.class);
////                        startActivity(intent);
////                    }
////
////                    @Override
////                    public void failure(RetrofitError error) {
////                        // if error occurs in network transaction then we can get the error in this method.
////                        Toast.makeText(WithdrawActivity.this, error.toString(), Toast.LENGTH_LONG).show();
////                        progressDialog.dismiss(); //dismiss progress dialog
////
////                    }
////                });
//
//    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
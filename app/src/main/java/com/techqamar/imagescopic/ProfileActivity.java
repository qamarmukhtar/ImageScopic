package com.techqamar.imagescopic;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.squareup.picasso.Picasso;
import com.techqamar.imagescopic.CommonUtils.Urls;
import com.techqamar.imagescopic.CommonUtils.Utils;
import com.techqamar.imagescopic.CommonUtils.VolleySingleton;

import org.json.JSONObject;


public class ProfileActivity extends AppCompatActivity {

    public TextView first_name_textview, last_name_textview, useremail, back_to_home, last_nameTextView, username1, useremail1;
    public String first_name, last_name, email, UserId, imageUrl;
    public String id;
    public ImageView mImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        first_name_textview = (TextView) findViewById(R.id.username);
//        last_name_textview = (TextView) findViewById(R.id.username1);
        useremail = (TextView) findViewById(R.id.useremail);
        username1 = (TextView) findViewById(R.id.username1);
        last_nameTextView = (TextView) findViewById(R.id.last_nameTextView);
        useremail1 = (TextView) findViewById(R.id.useremail1);
        back_to_home = (TextView) findViewById(R.id.back_to_home);
        mImageView = findViewById(R.id.profile_image);


        Intent intent = getIntent();
        id = intent.getStringExtra("s_id");
        System.out.println("id" + id);


//        SharedPreferences sh = ProfileActivity.this.getSharedPreferences("profiledata", Context.MODE_PRIVATE);
//        Username = sh.getString("username", "");
//        UserPhno = sh.getString("phoneno", "");
//        Useremail = sh.getString("email", "");
//        UserId = sh.getString("id", "");


        back_to_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        if (id == null) {
            finish();
        } else {
            CheckCredentials();
        }
    }

    private void CheckCredentials() {

        String urlForLogin = String.format(Urls.PROFILE, id);

        Log.e("url", "url" + urlForLogin);


        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlForLogin,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject Object = new JSONObject(response);
                            System.out.println("Output" + response);
                            JSONObject jsonObject = Object.getJSONObject("data");
//                            branchid = Object.getString("branchid");
//                            compare_string = Object.getString("message");
                            UserId = jsonObject.getString("id");
                            first_name = jsonObject.getString("first_name");
                            System.out.println("username" + first_name);
                            first_name_textview.setText(first_name);
                            username1.setText(first_name);
                            last_name = jsonObject.getString("last_name");
                            System.out.println("lastname" + last_name);
//                            last_name_textview.setText(last_name);
                            last_nameTextView.setText(last_name);
                            email = jsonObject.getString("email");
                            System.out.println("email" + email);
                            useremail.setText(email);
                            useremail1.setText(email);
                            imageUrl = jsonObject.getString("avatar");
                            System.out.println("imageUrl" + imageUrl);
                            Picasso.get().load(imageUrl).fit().centerInside().into(mImageView);


                        } catch (Exception e) {
                            e.printStackTrace();
                        }

//                        if (compare_string.equals("Invalid API Call")) {
//                            btnRegister.setVisibility(View.VISIBLE);
////                            spinKitView.setVisibility(View.INVISIBLE);
//                            Toast.makeText(Register_activity.this, "Check all Fields Username / Password", Toast.LENGTH_SHORT).show();
//                        } else {
//
//                            Log.e("RES.......", "..............................." + response);
//
//                            Toast.makeText(Register_activity.this, "Registration  Successful", Toast.LENGTH_SHORT).show();
//                            SharedPreferences sharedpreferences = Register_activity.this.getSharedPreferences("profiledata", Context.MODE_PRIVATE);
//                            SharedPreferences.Editor editor = sharedpreferences.edit();
//                            editor.putString("Loginresponse", response);
//                            editor.putString("id", id);
//                            editor.putString("username", username);
//                            editor.putString("phoneno", phoneno);
//                            editor.putString("email", email);
//                            editor.commit();
//
//                            Intent i = new Intent(Register_activity.this, MainActivity.class);
//                            startActivity(i);
//                            finish();
//                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                btnRegister.setVisibility(View.VISIBLE);
//                spinKitView.setVisibility(View.INVISIBLE);
                Log.e("ERROR LoginActivity", error.toString());
            }
        });

        Utils.setVolleyRetryPolicy(stringRequest);
        VolleySingleton.getInstance(ProfileActivity.this).addToRequestQueue(stringRequest, "Login");
    }
}

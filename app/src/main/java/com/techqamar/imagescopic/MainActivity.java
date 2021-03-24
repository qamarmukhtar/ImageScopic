package com.techqamar.imagescopic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.techqamar.imagescopic.CommonUtils.Urls;
import com.techqamar.imagescopic.Milk_TypeListRecViewAdapter.Milk_TypeListPojo;
import com.techqamar.imagescopic.Milk_TypeListRecViewAdapter.Milk_TypeListRecViewAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ImageView imgBackButton;
    RequestQueue requestQueue;

    private StaggeredGridLayoutManager mLayoutManager;
//    private LottieAnimationView tv_no_cards;

    Milk_TypeListRecViewAdapter milkTypeListRecViewAdapter;
    ArrayList<Milk_TypeListPojo> milkTypeListPojoArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery__stores);
        TextView textView = findViewById(R.id.Category_toolbar_text);
        textView.setText("ImageScopic");

//        imgBackButton = (ImageView) findViewById(R.id.imgBackButton);
//        imgBackButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(Grocery_Stores.this, MainActivity.class));
//                finish();
//            }
//        });
//        tv_no_cards = findViewById(R.id.tv_no_cards);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        requestQueue = Volley.newRequestQueue(MainActivity.this);


//        new CheckInternetConnection(this).checkConnection();




        milkTypeListRecViewAdapter = new Milk_TypeListRecViewAdapter(milkTypeListPojoArrayList, new Milk_TypeListRecViewAdapter.OnInvoiceOptionclicked() {
            @Override
            public void onPayOptionClicked(Milk_TypeListPojo invoiceListPojo) {


            }

            @Override
            public void onPrintOptionClicked(Milk_TypeListPojo invoiceListPojo) {

            }
        });
        //using staggered grid pattern in recyclerview
        mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLayoutManager);


//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        recyclerView.setFocusable(false);
//        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(milkTypeListRecViewAdapter);

        getGroceryStoreDetails();
    }

    private void getGroceryStoreDetails() {


        String url = String.format(Urls.USERS);

        System.out.println("Sever Response " + url);

        StringRequest postRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            System.out.println("Sever Response jsonObject" + response);

                            JSONArray jsonArray = jsonObject.getJSONArray("data");

//                            JSONArray jsonArray = new JSONArray(response);
//
//                            System.out.println("Sever Response jsonObject" + response);
//
//                            JSONObject jsonObject = jsonArray.getJSONObject(Integer.parseInt("data"));

//                            DcListPojo content[] = new Gson().fromJson(jsonArray.toString(), DcListPojo[].class);
//                            ArrayList<DcListPojo> dataList = new ArrayList<DcListPojo>(Arrays.asList(content));
                            Milk_TypeListPojo content[] = new Gson().fromJson(jsonArray.toString(), Milk_TypeListPojo[].class);
                            ArrayList<Milk_TypeListPojo> dataList = new ArrayList<Milk_TypeListPojo>(Arrays.asList(content));

                            milkTypeListPojoArrayList.addAll(dataList);

                            milkTypeListRecViewAdapter.notifyDataSetChanged();

                            if (milkTypeListPojoArrayList.size() == 0) {

                                Toast.makeText(MainActivity.this, "List is empty", Toast.LENGTH_SHORT).show();

//                                tv_no_cards.setVisibility(View.VISIBLE);

                            } else {

//                                tv_no_cards.setVisibility(View.INVISIBLE);

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(MainActivity.this, "Bad Response From Server", Toast.LENGTH_SHORT).show();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        if (error instanceof ServerError)
                            Toast.makeText(MainActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
                        else if (error instanceof TimeoutError)
                            Toast.makeText(MainActivity.this, "Connection Timed Out", Toast.LENGTH_SHORT).show();
                        else if (error instanceof NetworkError)
                            Toast.makeText(MainActivity.this, "Bad Network Connection", Toast.LENGTH_SHORT).show();


//                        String body = null;
//                        try {
//                            body = new String(error.networkResponse.data, "UTF-8");
//                            Log.d("Error.Response", body);
//
//                        } catch (UnsupportedEncodingException e) {
//                            e.printStackTrace();
//                        }

                    }
                }
        ) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                return params;
            }
        };


//        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
//                new Response.Listener<String>()
//                {
//                    @Override
//                    public void onResponse(String response) {
//
//                        System.out.println("Sever Response"+response);
//
//
//                        GroceryListPojo content[] = new Gson().fromJson(jsonArray.toString(), GroceryListPojo[].class);
//                        ArrayList<GroceryListPojo> dataList = new ArrayList<GroceryListPojo>(Arrays.asList(content));
//
//
//                        groceryListPojoArrayList.addAll(dataList);
//
//                        groceryListRecViewAdapter.notifyDataSetChanged();
//
//                        if(groceryListPojoArrayList.size()==0){
//
//                            tv_no_cards.setVisibility(View.VISIBLE);
//
//                        }
//
//
//
//                    }
//                },
//                new Response.ErrorListener()
//                {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//
//
//
//                        String body = null;
//                        try {
//                            body = new String(error.networkResponse.data, "UTF-8");
//                            Log.d("Error.Response", body);
//
//                        } catch (UnsupportedEncodingException e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//                }
//        ) {
//            @Override
//            protected Map<String, String> getParams()
//            {
//                Map<String, String>  params = new HashMap<String, String>();
//                return params;
//            }
//        };

        requestQueue.add(postRequest);


    }
}
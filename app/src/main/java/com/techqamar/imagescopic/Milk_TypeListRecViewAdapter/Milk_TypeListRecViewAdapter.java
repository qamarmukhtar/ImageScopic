package com.techqamar.imagescopic.Milk_TypeListRecViewAdapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.request.RequestOptions;

import com.squareup.picasso.Picasso;
import com.techqamar.imagescopic.ProfileActivity;
import com.techqamar.imagescopic.R;

import java.util.ArrayList;




public class Milk_TypeListRecViewAdapter extends RecyclerView.Adapter<Milk_TypeListRecViewAdapter.ViewHolder> {

    private ArrayList<Milk_TypeListPojo> milkTypeListPojoArrayList;
    private Context context;
    private OnInvoiceOptionclicked onInvoiceOptionclicked;
    RequestOptions option;

    public Milk_TypeListRecViewAdapter(ArrayList<Milk_TypeListPojo> d, OnInvoiceOptionclicked onInvoiceOptionclicked) {
        this.milkTypeListPojoArrayList = d;
        this.onInvoiceOptionclicked=onInvoiceOptionclicked;

        // Request option for Glide
        option = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);

    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();

        View view = LayoutInflater.from(context).inflate(R.layout.grocery_store_item_layout, parent, false);



        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        final Milk_TypeListPojo milkTypeListPojo = milkTypeListPojoArrayList.get(position);

        final String s_id= milkTypeListPojo.get_id();
        final String imageUrl = milkTypeListPojo.getAvatar();
        System.out.println("imageURl"+imageUrl);
        final String first_name = milkTypeListPojo.getFirst_name();
        final String last_name = milkTypeListPojo.getLast_name();
        final String email = milkTypeListPojo.getEmail();

//        holder.owner_name.setText(owner_name);
        holder.first_name.setText(first_name);
//        holder.email.setText(email);
        holder.last_name.setText(last_name);
        holder.email.setText(email);

//        Glide.with(context).load(milkTypeListPojo.getmImageUrl()).into(holder.mImageView);



//        if (!imageUrl.equals("")){
//            Picasso.get().load(imageUrl).into(holder.mImageView);
//        } else {
//            //todo - implement a default image in case img_url is indeed empty
////            Picasso.get().load(defaultImage).into(holder.mImageView);
//        }

//        byte[] decodedString = Base64.decode(milkTypeListPojo.getmImageUrl(), Base64.DEFAULT);
//        Bitmap imgBitMap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
//
//        holder.mImageView.setImageBitmap(imgBitMap);


        Picasso.get().load(imageUrl).fit().centerInside().into(holder.mImageView);

//        holder.shop_name.setText(groceryListPojo.getmStorename());
//        holder.store_address.setText(context.getString(R.string.Rs)+" "+groceryListPojo.getAddress());
//        holder.distance.setText(context.getString(R.string.Rs)+" "+groceryListPojo.getKilometer());
//        holder.materialRatingBar.setRating(groceryListPojo.getStar_rating());

        holder.viewitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProfileActivity.class);
//                intent.putExtra("first_name",first_name);
//                intent.putExtra("last_name",last_name);
//                intent.putExtra("email",email);
                intent.putExtra("s_id",s_id);
//                intent.putExtra("image_url", Uri.parse(imageUrl));
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return milkTypeListPojoArrayList.size();
    }


    public interface OnInvoiceOptionclicked{


        void onPayOptionClicked(Milk_TypeListPojo invoiceListPojo);
        void onPrintOptionClicked(Milk_TypeListPojo invoiceListPojo);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView mImageView;
        public TextView first_name,last_name,email;
        View viewitem;

        public ViewHolder(View itemView) {
            super(itemView);
             viewitem= itemView;

            mImageView = itemView.findViewById(R.id.image_cartlist);
            first_name = itemView.findViewById(R.id.first_name);
            last_name = itemView.findViewById(R.id.last_name);
            email = itemView.findViewById(R.id.email);



        }
    }

}

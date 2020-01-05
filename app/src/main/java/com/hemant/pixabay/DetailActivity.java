package com.hemant.pixabay;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import static com.hemant.pixabay.MainActivity.EXTRA_CREATOR;
import static com.hemant.pixabay.MainActivity.EXTRA_LIKES;
import static com.hemant.pixabay.MainActivity.EXTRA_URL;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        String imageurl = intent.getStringExtra(EXTRA_URL);
        String creator_name = intent.getStringExtra(EXTRA_CREATOR);
        int likecount = intent.getIntExtra(EXTRA_LIKES,0);
        ImageView imageview_detail = findViewById(R.id.imageview_details);
        TextView tv =findViewById(R.id.textviewcreatorname_detail);
        TextView tvlike = findViewById(R.id.textviewlike_detail);

        Picasso.with(this).load(imageurl).fit().centerCrop().into(imageview_detail);
        tv.setText(creator_name);
        tvlike.setText("Likes"+likecount);

    }
}

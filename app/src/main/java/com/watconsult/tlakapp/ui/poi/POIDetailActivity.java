package com.watconsult.tlakapp.ui.poi;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;
import com.watconsult.tlakapp.R;

public class POIDetailActivity extends AppCompatActivity {
    String location,country,poiname,poiImg,description,typeIcon;
    RoundedImageView roundedImageView;
    ImageView typeImg;
    TextView text_details,text_description;
    String fullPath;
    String typeImagePath = "https://account.tlakapp.com/tlak/images/uploads/poiicons/";
    ImageView back_btnx_txt;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.poi_detail_layout);
        back_btnx_txt = (ImageView) findViewById(R.id.back_btnx_txt);
        back_btnx_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Bundle args = getIntent().getExtras();
        if (args != null) {
            location = String.valueOf(args.get("locationName"));
            System.out.println("location--"+location);
            country = String.valueOf(args.get("countryName"));
            poiname = String.valueOf(args.get("poiName"));
            poiImg = String.valueOf(args.get("sendPath"));
            description = String.valueOf(args.get("poiDescription"));
            typeIcon = String.valueOf(args.get("typeIcon"));
            System.out.println("typeIcon========="+typeIcon);
        }
        fullPath = typeImagePath + typeIcon;
        typeImg = (ImageView) findViewById(R.id.imgs);
        Picasso.with(this).load(fullPath).into(typeImg);
        roundedImageView = (RoundedImageView) findViewById(R.id.poi_Img);
        Picasso.with(this).load(poiImg).into(roundedImageView);
        text_details = (TextView) findViewById(R.id.text_details);
        text_details.setText(poiname+", "+location+", "+  country);
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Regular.ttf");
        text_details.setTypeface(custom_font);
        text_description = (TextView) findViewById(R.id.text_description);
        Typeface custom_font1 = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Regular.ttf");
        text_description.setTypeface(custom_font1);
        text_description.setText(description);
    }
}

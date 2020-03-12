package com.watconsult.tlakapp.adapter;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Parcelable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.viewpager.widget.PagerAdapter;

import com.squareup.picasso.Picasso;
import com.watconsult.tlakapp.R;
import com.watconsult.tlakapp.model.OptionalTourItem;

import java.util.ArrayList;


public class SlidingImage_Adapter extends PagerAdapter {


    private ArrayList<OptionalTourItem> IMAGES = new ArrayList<>();
    private LayoutInflater inflater;
    private Context context;


    public SlidingImage_Adapter(Context context, ArrayList<OptionalTourItem> IMAGES) {
        this.context = context;
this.IMAGES=IMAGES;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return IMAGES.size();
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        View imageLayout = inflater.inflate(R.layout.tour_item_list, view, false);
        assert imageLayout != null;
        final ImageView optionalImage = (ImageView) imageLayout.findViewById(R.id.img);

        TextView name = (TextView) imageLayout.findViewById(R.id.name);
        Typeface custom_fon = Typeface.createFromAsset(context.getAssets(),  "fonts/Roboto-Bold.ttf");
        name.setTypeface(custom_fon);
        TextView promoContent = (TextView) imageLayout.findViewById(R.id.promoContent);
        Typeface custom_font = Typeface.createFromAsset(context.getAssets(),  "fonts/Roboto-Regular.ttf");
        promoContent.setTypeface(custom_font);
        TextView description = (TextView) imageLayout.findViewById(R.id.description);
        Typeface custom_font1 = Typeface.createFromAsset(context.getAssets(),  "fonts/Roboto-Regular.ttf");
        description.setTypeface(custom_font1);
        OptionalTourItem item = IMAGES.get(position);
        Picasso.with(context).load(item.getOptionalDepartureImage()).into(optionalImage);
        name.setText("" + item.getOptionalDepartureName());
        promoContent.setText("" + item.getPromoContent());
        description.setText("" + item.getDescription());
        Button phone = (Button) imageLayout.findViewById(R.id.contact);
        Button email = (Button) imageLayout.findViewById(R.id.email);
        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri u = Uri.parse("tel:" + item.getPhone());
                Intent i = new Intent(Intent.ACTION_DIAL, u);
                try
                {
                    context.startActivity(i);
                }
                catch (SecurityException s)
                {
                    s.printStackTrace();
                }
            }
        });
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent intent = new Intent (Intent.ACTION_VIEW , Uri.parse("mailto:" + item.getEmail()));
                    intent.putExtra(Intent.EXTRA_SUBJECT, "");
                    intent.putExtra(Intent.EXTRA_TEXT, "");
                    context.startActivity(intent);
                }catch(ActivityNotFoundException e){
                    //TODO smth
                }
            }
        });
     //   optionalImage.setImageResource(IMAGES.get(position));
        view.addView(imageLayout, 0);
        return imageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }

}

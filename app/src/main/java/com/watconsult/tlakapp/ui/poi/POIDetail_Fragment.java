package com.watconsult.tlakapp.ui.poi;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;
import com.watconsult.tlakapp.R;

public class POIDetail_Fragment extends Fragment {
    String location,country,poiname,poiImg,description,typeIcon;
    RoundedImageView roundedImageView;
    ImageView typeImg;
    TextView text_details,text_description;
    String fullPath;
    String typeImagePath = "https://account.tlakapp.com/tlak/images/uploads/poiicons/";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.poi_detail_layout, container, false);
        Bundle args = getArguments();
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
        typeImg = (ImageView) root.findViewById(R.id.imgs);
        Picasso.with(getActivity()).load(fullPath).into(typeImg);
        roundedImageView = (RoundedImageView) root.findViewById(R.id.poi_Img);
        Picasso.with(getActivity()).load(poiImg).into(roundedImageView);
        text_details = (TextView) root.findViewById(R.id.text_details);
        text_details.setText(poiname+", "+location+", "+  country);
        Typeface custom_font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Medium.ttf");
        text_details.setTypeface(custom_font);

        text_description = (TextView) root.findViewById(R.id.text_description);
        Typeface custom_font1 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Regular.ttf");
        text_description.setTypeface(custom_font1);
        text_description.setText(description);

        return root;
    }
}

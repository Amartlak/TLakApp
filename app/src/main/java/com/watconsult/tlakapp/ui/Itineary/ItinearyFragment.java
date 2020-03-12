package com.watconsult.tlakapp.ui.Itineary;

import android.Manifest.permission;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.watconsult.sqlite.DBAdapter;
import com.watconsult.tlakapp.R;
import com.watconsult.tlakapp.ServiceProviderClass;
import com.watconsult.tlakapp.SharedPrefs;
import com.watconsult.tlakapp.WIFIInternetConnectionDetector;
import com.watconsult.tlakapp.adapter.ItinearyPoAdapter;
import com.watconsult.tlakapp.adapter.Optional_IT_Adapter;
import com.watconsult.tlakapp.model.ItinearyItem;
import com.watconsult.tlakapp.model.ItinearyPOIItem;
import com.watconsult.tlakapp.model.Optional_IT_Item;
import com.watconsult.tlakapp.ui.flights.FlightDetailActivty;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import me.yuqirong.cardswipelayout.CardItemTouchHelperCallback;
import me.yuqirong.cardswipelayout.CardLayoutManager;
import me.yuqirong.cardswipelayout.OnSwipeListener;


public class ItinearyFragment extends Fragment implements LocationListener {
    private List<Integer> listlist = new ArrayList<>();
    private ArrayList<ItinearyItem> itinearylistItems = new ArrayList<ItinearyItem>();
    private ArrayList<ItinearyPOIItem> itinearypoiItems = new ArrayList<ItinearyPOIItem>();
    private ArrayList<Optional_IT_Item> itinearyoptionalItems = new ArrayList<Optional_IT_Item>();

    NestedScrollView nestedScrollView;
    private ItinearyViewModel homeViewModel;
    TextView heading, sub_heading,cityname;
    //  CardView cardView;
    RecyclerView recyclerView;
    String itinearyImagePath = null;
    String ItinearyImage = null;
    CardItemTouchHelperCallback cardCallback;
    SharedPrefs sharedPrefs;
    double latittude;
    double longitude;
    String daynumbers = null;
    String cityNames = null;
    String pkgName;
    WIFIInternetConnectionDetector cd;
    private static final int PERMISSION_ACCESS_COARSE_LOCATION = 1;
    String finalImgPath = null;
    String totalDays = null;
    String totalNight = null;
    Boolean isConnectionExist = false;
    String inclusion;
    RecyclerView poi_list,optional_list;
    Location mLastLocation;
    public static String company_name = "";
    String itinearyId = null;
    private LocationManager locationManager;
    private String provider;
    ItinearyItem item;
    int itemCount;
    String globalData;
    SharedPrefs shareprefs;
    ItinearyPoAdapter itinearyPOAdapter;
    String it_DayNumber,poi_DayNumber;
    String itinearyImg,poiImg;
    Optional_IT_Adapter optional_it_adapter;
    JSONObject jsonObj;
    double currentLatitude, currentLongitude;
    String matchdata;
    ArrayList<ItinearyPOIItem> otherList = new ArrayList<>();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(ItinearyViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);
        sharedPrefs = new SharedPrefs(getActivity());
        cd = new WIFIInternetConnectionDetector(getActivity());
        isConnectionExist = cd.checkMobileInternetConn();
        if (!isConnectionExist) {
            AlertDialog.Builder a_builder = new AlertDialog.Builder(getActivity());
            a_builder.setMessage(R.string.NoInternet)
                    .setCancelable(false)
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //  name.setText(null);
                            dialog.dismiss();
                        }
                    });
            AlertDialog alert = a_builder.create();
            alert.setTitle("Error");
            alert.show();
            alert.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);
        }

        @SuppressLint("MissingPermission") Location location = locationManager.getLastKnownLocation(provider);
        if (location != null) {

           onLocationChanged(location);
        }
        heading = (TextView) root.findViewById(R.id.heading);
        Typeface custom_font1 = Typeface.createFromAsset(getActivity().getAssets(),  "fonts/Roboto-Bold.ttf");
        heading.setTypeface(custom_font1);
        sub_heading = (TextView) root.findViewById(R.id.sub_heading);
        Typeface custom_font2 = Typeface.createFromAsset(getActivity().getAssets(),  "fonts/Roboto-Regular.ttf");
        sub_heading.setTypeface(custom_font1);
        cityname = (TextView) root.findViewById(R.id.city);
        cityname.setText("");
        Typeface custom_font5 = Typeface.createFromAsset(getActivity().getAssets(),  "fonts/Roboto-Medium.ttf");
        cityname.setTypeface(custom_font5);

        //Amar
        poi_list = (RecyclerView) root.findViewById(R.id.poi_list);
       // cityname.setText(cityNames);
        recyclerView = (RecyclerView) root.findViewById(R.id.recyclerView);
        optional_list = (RecyclerView) root.findViewById(R.id.optional_list);
        recyclerView.setNestedScrollingEnabled(false);
        //  cardView = (CardView) root.findViewById(R.id.card);
      //  cardView.setRadius(19);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(new MyAdapter());

       cardCallback = new CardItemTouchHelperCallback(recyclerView.getAdapter(), itinearylistItems);
        initView();

        //initData();
     /*   final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
       JSONObject object = new JSONObject();
        try {
            object.put("token", sharedPrefs.getToken());
            Log.e("obJE=",object.toString());
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("error",e.toString());
        }
        if (ContextCompat.checkSelfPermission(getActivity(), permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[] { permission.ACCESS_COARSE_LOCATION },
                    PERMISSION_ACCESS_COARSE_LOCATION);
        }
        if(itinearylistItems.size()>0) {
            System.out.println("jhbdjsbfhbshf");
        }else{
            System.out.println("ghjgd");
            new ItinearyTask().execute(object);

        }
       /* if(itinearylistItems.isEmpty()){

        }*/
        return root;
    }
   /* private void initData() {
        list.add(R.drawable.itinerary_main_img);
        list.add(R.drawable.itinerary_page_poi_img);
        list.add(R.drawable.itinerary_page_poi_img1);
        list.add(R.drawable.itinerary_page_poi_img2);
        list.add(R.drawable.itinerary_main_img);
        list.add(R.drawable.itinerary_page_poi_img);
        list.add(R.drawable.itinerary_page_poi_img1);
    }*/


  /*  @Override
    protected void onResume() {
        super.onResume();
        retrieve();*/


    private void initView() {
        cardCallback.setOnSwipedListener(new OnSwipeListener() {
            @Override
            public void onSwiping(RecyclerView.ViewHolder viewHolder, float ratio, int direction) {
                MyAdapter.MyViewHolder myHolder = (MyAdapter.MyViewHolder) viewHolder;
                viewHolder.itemView.setAlpha(1 - Math.abs(ratio) * 0.2f);
            }
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, Object o, int direction) {
                MyAdapter.MyViewHolder myHolder = (MyAdapter.MyViewHolder) viewHolder;
                viewHolder.itemView.setAlpha(1f);
            }
            @Override
            public void onSwipedClear() {
               // Toast.makeText(getActivity(), "data clear", Toast.LENGTH_SHORT).show();
                recyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                       // Toast.makeText(getActivity(),itinearylistItems.size(),Toast.LENGTH_LONG).show();
                       // System.out.println();itinearylistItems.size();
                           // itinearylistItems.addAll(itinearylistItems);
                           // recyclerView.getAdapter().notifyDataSetChanged();
                        addmoreData();

                    }
                }, 5L);
            }
        });
        final ItemTouchHelper touchHelper = new ItemTouchHelper(cardCallback);
        final CardLayoutManager cardLayoutManager = new CardLayoutManager(recyclerView, touchHelper);
        recyclerView.setLayoutManager(cardLayoutManager);
        touchHelper.attachToRecyclerView(recyclerView);
    }

    private void addmoreData() {
        itinearylistItems.clear();
        parseResultAsPerOrgStandards(globalData);
    }

   /*  @Override
     public void onLocationChanged(Location location) {
         currentLatitude = location.getLatitude();
         System.out.println("currentLatitude ="+currentLatitude);
         currentLongitude = location.getLongitude();
         System.out.println("currentLongitude ="+currentLongitude);
     }*/
 @Override
    public void onLocationChanged(Location location) {
        double lat = location.getLatitude();
        sharedPrefs.setLat(String.valueOf(lat));
        System.out.println("lat------------" + lat);
        double lng = location.getLongitude();
        sharedPrefs.setLongi(String.valueOf(lng));
        System.out.println("lng------------------" + lng);
        Geocoder geoCoder = new Geocoder(getActivity(), Locale.getDefault()); //it is Geocoder
        StringBuilder builder = new StringBuilder();

        Geocoder gcd = new Geocoder(getActivity().getBaseContext(),
                Locale.getDefault());
        List<Address> addresses;
        try {
            addresses = gcd.getFromLocation(lat, lng
                    , 1);
            if (addresses.size() > 0)
                System.out.println(addresses.get(0).getLocality());
            cityNames = addresses.get(0).getLocality();

            System.out.println("cityNamessss="+cityNames);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }
    @Override
    public void onProviderEnabled(String provider) {
    }
    @Override
    public void onProviderDisabled(String provider) {
    }

    public class ItinearyTask extends AsyncTask<JSONObject, Void, String> {
        ProgressDialog progressDialog;
        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(getActivity(), R.style.MyAlertDialogStyle);
            progressDialog.setMessage("Please wait...");
            progressDialog.setCancelable(false);
            progressDialog.show();
            super.onPreExecute();
        }
        @Override
        protected String doInBackground(JSONObject... param) {
            return ServiceProviderClass.getItinearyData(param[0]);
        }
        @Override
        protected void onPostExecute(String result) {
            System.out.println("resultdddsss---dd-----"+result);
            progressDialog.dismiss();
            globalData = result;
            parseResultAsPerOrgStandards(result);
        }
    }

    private void parseResultAsPerOrgStandards(String result) {
        if (result != null) {
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(result);
                String error = String.valueOf(jsonObject.getBoolean("error"));
                String message = jsonObject.getString("message");
                JSONObject jsonObject1 = jsonObject.getJSONObject("traveler");
                String pkgId = String.valueOf(jsonObject1.getInt("pkgId"));
                String travelerId = jsonObject1.getString("travelerId");
                String token = jsonObject1.getString("token");
                String tenantId = jsonObject1.getString("tenantId");
                sharedPrefs.setTravelerId(tenantId);
                JSONObject jsonObject2 = jsonObject.getJSONObject("tourPackage");
                pkgName = jsonObject2.getString("pkgName");
                heading.setText(pkgName);
                sharedPrefs.setPkgName(pkgName);
                company_name = jsonObject2.getString("companyName");
                String startDate = jsonObject2.getString("startDate");
                String endDate = jsonObject2.getString("endDate");
                totalDays = String.valueOf(jsonObject2.getInt("totalDays"));
                totalNight = String.valueOf(jsonObject2.getInt("totalNight"));
                System.out.println("totalNight  " + totalNight);
                sub_heading.setText(totalNight + " Nights " + "/ " + totalDays + " Days");
                String startTime = jsonObject2.getString("startTime");
                JSONArray jsonArray = jsonObject.getJSONArray("itinearies");
                final int numberofitems = jsonArray.length();
                for (int i = 0; i < numberofitems; i++) {
                    item = new ItinearyItem();
                     jsonObj = jsonArray.getJSONObject(i);
                    System.out.println("json--s-" + jsonObject1);
                    System.out.println("itinearyId--------"+itinearyId);
                    item.setItinearyId(Integer.parseInt(jsonObj.getString("itinearyId")));
                    item.setDayNumber(jsonObj.getString("dayNumber"));
                    item.setDayHeading(jsonObj.getString("dayHeading"));
                    item.setDescription(jsonObj.getString("description"));
                    item.setItinearyImage(jsonObj.getString("itinearyImage"));
                    it_DayNumber = jsonObj.getString("dayNumber");
                    itinearylistItems.add(item);
                    itemCount = itinearylistItems.size();
                    }
                JSONArray arraypoi = jsonObject.getJSONArray("poi");
                final int numberof = arraypoi.length();
                for(int j=0; j< numberof; j++) {
                    ItinearyPOIItem items = new ItinearyPOIItem();
                    JSONObject jsonOb = arraypoi.getJSONObject(j);
                    System.out.println("jsonOb---------"+jsonOb);
                    items.setPoiImage(jsonOb.getString("poiImage"));
                    itinearypoiItems.add(items);
                }
                JSONArray jsonArrs = jsonObject.getJSONArray("optionalPoi");
                final int numberofite = jsonArrs.length();
                for (int i = 0; i < numberofite; i++) {
                    Optional_IT_Item ite = new Optional_IT_Item();
                    JSONObject jsonOb = jsonArrs.getJSONObject(i);
                    System.out.println("jsonOb====="+jsonOb);
                    String img = jsonOb.getString("poiImage");
                    System.out.println("img====="+img);
                    ite.setPoiImage(jsonOb.getString("poiImage"));
                    itinearyoptionalItems.add(ite);
                }
                    //initView();+
                  //  itinearylistItems.add(item);
                //    itemCount = itinearylistItems.size();


               /* JSONArray arraypoi = jsonObj.getJSONArray("poi");
                final int numberof = arraypoi.length();
                for(int j=0; j< numberof; j++){
                    ItinearyPOIItem items = new ItinearyPOIItem();
                    JSONObject jsonOb = arraypoi.getJSONObject(j);
                    System.out.println("jsonOb---------"+jsonOb);
                   *//* items.setDayNumber(jsonOb.getString("dayNumber"));
                    items.setLocationName(jsonOb.getString("locationName"));*//*
                    items.setPoiName(jsonOb.getString("poiName"));
                    items.setPoiImage(jsonOb.getString("poiImage"));
                    itinearypoiItems.add(items);
                  //  itinearylistItems.add(item);
                }*/
               /* JSONArray jsonArrs = jsonObject.getJSONArray("optionalPoi");
                final int numberofite = jsonArrs.length();
                for (int i = 0; i < numberofite; i++) {
                    Optional_IT_Item ite = new Optional_IT_Item();
                    JSONObject jsonOb = jsonArrs.getJSONObject(i);
                    System.out.println("jsonOb====="+jsonOb);
                    String img = jsonOb.getString("poiImage");
                    System.out.println("img====="+img);
                    ite.setPoiImage(jsonOb.getString("poiImage"));
                    itinearyoptionalItems.add(ite);
                }
                JSONArray jsonArr = jsonObject.getJSONArray("poi");
                final int numberofitem = jsonArr.length();
                for (int i = 0; i < numberofitem; i++) {
                    ItinearyPOIItem items = new ItinearyPOIItem();
                    JSONObject jsonObjs = jsonArr.getJSONObject(i);
                    System.out.println("jsonObjs======="+jsonObjs);
                    poi_DayNumber = jsonObjs.getString("dayNumber");
                    items.setPoiImage(jsonObjs.getString("poiImage"));
                    itinearypoiItems.add(items);
                }*/
            }catch (JSONException e)
            {
            e.printStackTrace();
            }
            optional_it_adapter = new Optional_IT_Adapter(itinearyoptionalItems);
            LinearLayoutManager mLayoutManagers = new LinearLayoutManager(getActivity());
            mLayoutManagers.setOrientation(LinearLayoutManager.HORIZONTAL);
            optional_list.setLayoutManager(mLayoutManagers);
            optional_list.setItemAnimator(new DefaultItemAnimator());
            optional_list.setAdapter(optional_it_adapter);
            System.out.println(it_DayNumber+"------------"+poi_DayNumber);

              itinearyPOAdapter = new ItinearyPoAdapter(itinearypoiItems);
              LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
              mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
              poi_list.setLayoutManager(mLayoutManager);
              poi_list.setItemAnimator(new DefaultItemAnimator());
              poi_list.setAdapter(itinearyPOAdapter);
            recyclerView.postDelayed(new Runnable() {
                @Override
                public void run() {
                        recyclerView.getAdapter().notifyDataSetChanged();

                }
            },5L);
/*            cardCallback.setOnSwipedListener(new OnSwipeListener() {
                @Override
                public void onSwiping(RecyclerView.ViewHolder viewHolder, float ratio, int direction) {

                }

                @Override
                public void onSwiped(RecyclerView.ViewHolder viewHolder, Object o, int direction) {

                }

                @Override
                public void onSwipedClear() {
                    recyclerView.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            itinearylistItems.add(item);
                        }
                    },5L);
                }
            });*/

        }
    }
   private class MyAdapter extends RecyclerView.Adapter {

       @Override
       public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
           View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);

           return new MyViewHolder(view);
       }

       @Override
       public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
           ItinearyItem itinearyItem = itinearylistItems.get(position);

           ImageView avatarImageView = ((MyViewHolder) holder).avatarImageView;
           // avatarImageView.setImageResource(itinearyItem.);
           ((MyViewHolder) holder).welcome_name.setText("" + itinearyItem.getDayHeading());
          // ((MyViewHolder) holder).tv_sub1.setText("" + itinearyItem.getDescription());
              if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
               ((MyViewHolder) holder).tv_sub1.setText(Html.fromHtml(itinearyItem.getDescription(),Html.FROM_HTML_MODE_COMPACT));
             //  tv_sub1.setText(Html.fromHtml("<h2>Title</h2><br><p>Description here</p>", Html.FROM_HTML_MODE_COMPACT));
           } else {
             //  tv_sub1.setText(Html.fromHtml("<h2>Title</h2><br><p>Description here</p>"));
               ((MyViewHolder) holder).tv_sub1.setText(Html.fromHtml(itinearyItem.getDescription(),Html.FROM_HTML_MODE_COMPACT));
           }
           ((MyViewHolder) holder).tv_name.setText("Day " + itinearyItem.getDayNumber());
           ((MyViewHolder) holder).itid.setText("" + itinearyItem.getItinearyId());
           String imagepaths = itinearyImagePath;

           System.out.println("imagepath-88888888888-" + imagepaths);
           String poiImgpath = itinearyItem.getItinearyImage();
           System.out.println("poiImgpath-" + poiImgpath);
           finalImgPath = itinearyImg + "/" + poiImgpath;

           System.out.println("finalImgPath--" + finalImgPath);
           String itineary = String.valueOf(itinearyItem.getItinearyId());
           System.out.println("itineary----"+itineary);
           ((MyViewHolder) holder).id = String.valueOf(itinearyItem.getItinearyId());
           ((MyViewHolder) holder).wel_name = itinearyItem.getDescription();
           ((MyViewHolder) holder).tv_sub1s = itinearyItem.getDayNumber();
           ((MyViewHolder) holder).tourtnight = itinearyItem.getTotalNight();
           ((MyViewHolder) holder).tourday = itinearyItem.getTotalDays();
           ((MyViewHolder) holder).daynumber = itinearyItem.getDayNumber();
           // ((MyViewHolder) holder).avatarImageView = finalImgPath;

      Picasso.with(getContext()).load(itinearyItem.getItinearyImage()).into(avatarImageView);

           ((MyViewHolder) holder).more.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Intent intent = new Intent(getActivity(), ItinearyDetailActivity.class);
                   intent.putExtra("itinearyId", itineary);
                   getActivity().startActivity(intent);
                 /*  Bundle bundle = new Bundle();
                   bundle.putString("itinearyId", itineary); // Put anything what you want
                   ItnearyFragmentDetail a2Fragment = new ItnearyFragmentDetail();
                   a2Fragment.setArguments(bundle);

                   getFragmentManager()
                           .beginTransaction()
                           .replace(R.id.changeframe, a2Fragment)
                           .commit();*/
/*
                     ItnearyFragmentDetail a2Fragment = new ItnearyFragmentDetail();
                   FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                   transaction.replace(R.id.changeframe, a2Fragment).commit();*/
               }
           });
               }
       @Override
       public int getItemCount() {
           return itinearylistItems.size();
       }

       class MyViewHolder extends RecyclerView.ViewHolder {
           ImageView avatarImageView;
           Context mcontext;
           ImageView likeImageView;
           ImageView dislikeImageView;
           Button more;
           String finalImgPaths;
           String name, wel_name, tv_sub1s, daynumber, id;
           int tourday, tourtnight;
           TextView tv_name, welcome_name, tv_sub1, tv_sub2, tv_sub3,itid;

           MyViewHolder(View itemView) {
               super(itemView);

               avatarImageView = (ImageView) itemView.findViewById(R.id.iv_avatar);
               tv_name = (TextView) itemView.findViewById(R.id.tv_name);
               Typeface custom_font1 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Medium.ttf");
               tv_name.setTypeface(custom_font1);
               welcome_name = (TextView) itemView.findViewById(R.id.welcome_name);
               Typeface custom_font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Medium.ttf");
               welcome_name.setTypeface(custom_font);

              /* tv_sub1 = (TextView) itemView.findViewById(R.id.tv_sub1);
               Typeface custom = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Regular.ttf");
               tv_sub1.setTypeface(custom);*/


               tv_sub1 = (TextView) itemView.findViewById(R.id.tv_sub1);


              /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                   tv_sub1.setText(Html.fromHtml("<h2>Title</h2><br><p>Description here</p>", Html.FROM_HTML_MODE_COMPACT));
               } else {
                   tv_sub1.setText(Html.fromHtml("<h2>Title</h2><br><p>Description here</p>"));
               }*/

               itid = (TextView) itemView.findViewById(R.id.itid);
             /*  tv_sub2 = (TextView) itemView.findViewById(R.id.tv_sub2);
               Typeface custom1 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Regular.ttf");
               tv_sub2.setTypeface(custom1);
               tv_sub3 = (TextView) itemView.findViewById(R.id.tv_sub3);
               Typeface custom2 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Regular.ttf");
               tv_sub3.setTypeface(custom2);*/
               more = (Button) itemView.findViewById(R.id.more);

           }
       }
   }

   /* //RETRIEVE
    private void retrieve()
    {
        DBAdapter db=new DBAdapter(getActivity());

        //OPEN
        db.openDB();

        ItinearyPOIItem.clear();

        //SELECT
        Cursor c=db.getAllPlayers();

        //LOOP THRU THE DATA ADDING TO ARRAYLIST
        while (c.moveToNext())
        {
            int id=c.getInt(0);
            String name=c.getString(1);
            String pos=c.getString(2);

            //CREATE PLAYER
            ItinearyPOIItem p=new ItinearyPOIItem(name,pos,id);

            //ADD TO PLAYERS
            ItinearyPOIItem.add(p);
        }

        //SET ADAPTER TO RV
       // if(!(ItinearyPOIItem.size()<1))
        if(!ItinearyPOIItem.size()<1)
        {
            rv.setAdapter(adapter);
        }

    }*/
}
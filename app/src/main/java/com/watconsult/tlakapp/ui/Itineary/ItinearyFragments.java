/*
package com.watconsult.tlakapp.ui.Itineary;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.watconsult.tlakapp.R;
import com.watconsult.tlakapp.ServiceProviderClass;
import com.watconsult.tlakapp.SharedPrefs;
import com.watconsult.tlakapp.WIFIInternetConnectionDetector;
import com.watconsult.tlakapp.adapter.MoviesAdapter;
import com.watconsult.tlakapp.model.ItinearyItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import me.yuqirong.cardswipelayout.CardItemTouchHelperCallback;
import me.yuqirong.cardswipelayout.CardLayoutManager;
import me.yuqirong.cardswipelayout.OnSwipeListener;

public class ItinearyFragments extends Fragment {
    private List<Integer> list = new ArrayList<>();
    private ArrayList<ItinearyItem> itinearylistItems = new ArrayList<ItinearyItem>();
    NestedScrollView nestedScrollView;
    private ItinearyViewModel homeViewModel;
    TextView heading,sub_heading;
    //  CardView cardView;
    RecyclerView recyclerView;

    MoviesAdapter moviesAdapter;
    SharedPrefs sharedPrefs;
    WIFIInternetConnectionDetector cd;
    Boolean isConnectionExist = false;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(ItinearyViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        sharedPrefs=new SharedPrefs(getActivity());
        cd = new WIFIInternetConnectionDetector(getActivity());
        isConnectionExist = cd.checkMobileInternetConn();
        if(!isConnectionExist){
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
        heading = (TextView) root.findViewById(R.id.heading);
        Typeface custom_font1 = Typeface.createFromAsset(getActivity().getAssets(),  "fonts/Roboto-Bold.ttf");
        heading.setTypeface(custom_font1);
        sub_heading = (TextView) root.findViewById(R.id.sub_heading);
        Typeface custom_font2 = Typeface.createFromAsset(getActivity().getAssets(),  "fonts/Roboto-Regular.ttf");
        sub_heading.setTypeface(custom_font1);
        recyclerView = (RecyclerView) root.findViewById(R.id.recyclerView);
        recyclerView.setNestedScrollingEnabled(false);
        //  cardView = (CardView) root.findViewById(R.id.card);
        //  cardView.setRadius(19);


        //initView();
        initData();
     */
/*   final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*//*

        JSONObject object = new JSONObject();
        try {
            object.put("token", sharedPrefs.getToken());
            Log.e("obJE=",object.toString());
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("error",e.toString());
        }
        new ItinearyTask().execute(object);
        return root;
    }
    private void initData() {
        list.add(R.drawable.itinerary_main_img);
        list.add(R.drawable.itinerary_page_poi_img);
        list.add(R.drawable.itinerary_page_poi_img1);
        list.add(R.drawable.itinerary_page_poi_img2);
        list.add(R.drawable.itinerary_main_img);
        list.add(R.drawable.itinerary_page_poi_img);
        list.add(R.drawable.itinerary_page_poi_img1);
    }

   */
/* private void initView() {
        cardCallback.setOnSwipedListener(new OnSwipeListener() {
            @Override
            public void onSwiping(RecyclerView.ViewHolder viewHolder, float ratio, int direction) {
                ItinearyFragments.MyAdapter.MyViewHolder myHolder = (ItinearyFragments.MyAdapter.MyViewHolder) viewHolder;
                viewHolder.itemView.setAlpha(1 - Math.abs(ratio) * 0.2f);
            }
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, Object o, int direction) {
                ItinearyFragments.MyAdapter.MyViewHolder myHolder = (ItinearyFragments.MyAdapter.MyViewHolder) viewHolder;
                viewHolder.itemView.setAlpha(1f);
            }
            @Override
            public void onSwipedClear() {
                // Toast.makeText(getActivity(), "data clear", Toast.LENGTH_SHORT).show();
                recyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for (int i=0;i<=6;i++) {
                            initData();
                            recyclerView.getAdapter().notifyDataSetChanged();
                        }
                    }
                }, 5L);
            }
        });
        final ItemTouchHelper touchHelper = new ItemTouchHelper(cardCallback);
        final CardLayoutManager cardLayoutManager = new CardLayoutManager(recyclerView, touchHelper);
        recyclerView.setLayoutManager(cardLayoutManager);
        touchHelper.attachToRecyclerView(recyclerView);
    }
*//*

    public class ItinearyTask extends AsyncTask<JSONObject, Void, String> {
        ProgressDialog progressDialog;
        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(getActivity(),R.style.MyAlertDialogStyle);
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
            parseResultAsPerOrgStandards(result);
        }
    }

    private void parseResultAsPerOrgStandards(String result) {
        if (result != null) {
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(result);
                String error = String.valueOf(jsonObject.getBoolean("error"));
                String itinearyImagePath = jsonObject.getString("itinearyImagePath");
                JSONObject jsonObject1 = jsonObject.getJSONObject("traveler");
                String travelerId = jsonObject1.getString("travelerId");
                String token = jsonObject1.getString("token");
                String peopleId = jsonObject1.getString("peopleId");
                String tour_package_id = jsonObject1.getString("tour_package_id");
                String tenantId = jsonObject1.getString("tenantId");
                JSONObject jsonObject2 = jsonObject.getJSONObject("tourPackage");
                String pkgId = String.valueOf(jsonObject2.getInt("pkgId"));
                String pkgName = jsonObject2.getString("pkgName");
                heading.setText(pkgName);
                String startDate = jsonObject2.getString("startDate");
                String endDate = jsonObject2.getString("endDate");
                String totalDays = String.valueOf(jsonObject2.getInt("totalDays"));
                String startTime = jsonObject2.getString("startTime");
                String timezone = jsonObject2.getString("timezone");
                String totalNight = String.valueOf(jsonObject2.getInt("totalNight"));
                sub_heading.setText(totalNight+" Night "+ "/ "+ totalDays +" Days tour Package");
                JSONArray jsonArray = jsonObject.getJSONArray("itinearies");
                final int numberofitems = jsonArray.length();
                for (int i = 0; i < numberofitems; i++) {
                    ItinearyItem item = new ItinearyItem();
                    JSONObject jsonObj = jsonArray.getJSONObject(i);
                    System.out.println("json---"+jsonObject1);
                    item.setItinearyId(Integer.parseInt(jsonObject1.getString("itinearyId")));
                    item.setDayNumber(jsonObj.getString("dayNumber"));
                    item.setDayHeading(jsonObj.getString("dayHeading"));
                    item.setScreenView(jsonObj.getString("screenView"));
                    item.setDescription(jsonObj.getString("description"));
                    item.setInclusions(jsonObj.getString("inclusions"));
                    item.setItinearyImage(jsonObj.getString("itinearyImage"));
                    itinearylistItems.add(item);
                    recyclerView.getAdapter().notifyDataSetChanged();
                }

            }catch (JSONException e)
            {
                e.printStackTrace();
            }
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(moviesAdapter);
            cardCallback = new CardItemTouchHelperCallback(recyclerView.getAdapter(), itinearylistItems);
            final ItemTouchHelper touchHelper = new ItemTouchHelper(cardCallback);
            final CardLayoutManager cardLayoutManager = new CardLayoutManager(recyclerView, touchHelper);
            recyclerView.setLayoutManager(cardLayoutManager);
            touchHelper.attachToRecyclerView(recyclerView);
        }
    }

 */
/*   private class MyAdapter extends RecyclerView.Adapter {
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
            return new ItinearyFragments.MyAdapter.MyViewHolder(view);
        }
        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ImageView avatarImageView = ((ItinearyFragments.MyAdapter.MyViewHolder) holder).avatarImageView;
            avatarImageView.setImageResource(list.get(position));
            ItinearyItem item = itinearylistItems.get(position);
            RecyclerView.ViewHolder.welcome_name.setText("" +.getDayHeading());
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            ImageView avatarImageView;
            ImageView likeImageView;
            ImageView dislikeImageView;
            Button more;
            TextView tv_name,welcome_name,tv_sub1,tv_sub2,tv_sub3;
            MyViewHolder(View itemView) {
                super(itemView);

                avatarImageView = (ImageView) itemView.findViewById(R.id.iv_avatar);
                tv_name = (TextView) itemView.findViewById(R.id.tv_name);
                Typeface custom_font1 = Typeface.createFromAsset(getActivity().getAssets(),  "fonts/Roboto-Medium.ttf");
                tv_name.setTypeface(custom_font1);
                welcome_name = (TextView) itemView.findViewById(R.id.welcome_name);
                Typeface custom_font = Typeface.createFromAsset(getActivity().getAssets(),  "fonts/Roboto-Medium.ttf");
                welcome_name.setTypeface(custom_font);

                tv_sub1 = (TextView) itemView.findViewById(R.id.tv_sub1);
                Typeface custom = Typeface.createFromAsset(getActivity().getAssets(),  "fonts/Roboto-Regular.ttf");
                tv_sub1.setTypeface(custom);
               // tv_sub1.setText("" + item.getDescription());
                *//*
*/
/*tv_sub2 = (TextView) itemView.findViewById(R.id.tv_sub2);
                Typeface custom1 = Typeface.createFromAsset(getActivity().getAssets(),  "fonts/Roboto-Regular.ttf");
                tv_sub2.setTypeface(custom1);
                tv_sub3 = (TextView) itemView.findViewById(R.id.tv_sub3);
                Typeface custom2 = Typeface.createFromAsset(getActivity().getAssets(),  "fonts/Roboto-Regular.ttf");
                tv_sub3.setTypeface(custom2);*//*
*/
/*
                more = (Button) itemView.findViewById(R.id.more);
                more.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ItnearyFragmentDetail a2Fragment = new ItnearyFragmentDetail();
                        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                        transaction.replace(R.id.changeframe, a2Fragment).commit();
                    }
                });
              *//*
*/
/*  likeImageView = (ImageView) itemView.findViewById(R.id.iv_like);
                dislikeImageView = (ImageView) itemView.findViewById(R.id.iv_dislike);*//*
*/
/*
            }
        }
    }*//*

  */
/*  private void initView() {
        cardCallback.setOnSwipedListener(new OnSwipeListener() {
            @Override
            public void onSwiping(RecyclerView.ViewHolder viewHolder, float ratio, int direction) {
               ItinearyFragments.MyAdapter.MyViewHolder myHolder = (ItinearyFragments.MyAdapter.MyViewHolder) viewHolder;
                 viewHolder.itemView.setAlpha(1 - Math.abs(ratio) * 0.2f);
            }
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, Object o, int direction) {
                ItinearyFragments.MyAdapter.MyViewHolder myHolder = (ItinearyFragments.MyAdapter.MyViewHolder) viewHolder;
                viewHolder.itemView.setAlpha(1f);
            }
            @Override
            public void onSwipedClear() {
                // Toast.makeText(getActivity(), "data clear", Toast.LENGTH_SHORT).show();
                recyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for (int i=0;i<=6;i++) {
                            recyclerView.getAdapter().notifyDataSetChanged();
                        }
                    }
                }, 5L);
            }
        });
        final ItemTouchHelper touchHelper = new ItemTouchHelper(cardCallback);
        final CardLayoutManager cardLayoutManager = new CardLayoutManager(recyclerView, touchHelper);
        recyclerView.setLayoutManager(cardLayoutManager);
        touchHelper.attachToRecyclerView(recyclerView);
    }*//*


}
*/

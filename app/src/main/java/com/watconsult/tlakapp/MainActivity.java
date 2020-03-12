package com.watconsult.tlakapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.OvershootInterpolator;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.watconsult.sqlite.DBAdapter;
import com.watconsult.tlakapp.databinding.ActivityMainBinding;
import com.watconsult.tlakapp.model.ItinearyPOIItem;
import com.watconsult.tlakapp.ui.Itineary.ItinearyFragment;
import com.watconsult.tlakapp.ui.Optional.OptionalFragment;
import com.watconsult.tlakapp.ui.document.DocumentFragment;
import com.watconsult.tlakapp.ui.feedback.FeedbackFragment;
import com.watconsult.tlakapp.ui.flights.FlightsFragment;
import com.watconsult.tlakapp.ui.hotel.HotelsFragment;
import com.watconsult.tlakapp.ui.map.MapFragment;
import com.watconsult.tlakapp.ui.map.NewMapsActivity;
import com.watconsult.tlakapp.ui.mygroup.MyGroupFragment;
import com.watconsult.tlakapp.ui.placard.PlaCard;
import com.watconsult.tlakapp.ui.poi.Poi_Fragment;
import com.watconsult.tlakapp.ui.setting.SettingFragment;
import com.watconsult.tlakapp.ui.sos.SOSFragment;
import com.watconsult.tlakapp.ui.support.SupportFragment;
import com.watconsult.tlakapp.ui.termcondition.TConditionFragment;
import com.watconsult.tlakapp.ui.upcomingtour.TourFragment;
import com.watconsult.tlakapp.ui.weather.WeatherFragment;

import java.io.Serializable;
import java.util.List;

import static com.watconsult.tlakapp.ui.Itineary.ItinearyFragment.company_name;

public class MainActivity extends AppCompatActivity{

    TextView txt;
    private ActivityMainBinding binding;
    private Animation fabOpenAnimation;
    private Animation fabCloseAnimation;
    WIFIInternetConnectionDetector cd;
    private boolean isFabMenuOpen = false;
    RelativeLayout hotel, tour, sos, document;
    private String[] mNavigationDrawerItemTitles;
    // private String[] mBottomDrawerItemTitles;
    private static DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    Toolbar toolbar;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private CharSequence mBottomDrawer;
    ActionBarDrawerToggle mDrawerToggle;
    Boolean isConnectionExist = false;
    SwipeRefreshLayout swipeRefreshLayout;
    BottomNavigationView bottomNavigationView, mapbottomView;
    DataModel[] drawerItem = new DataModel[17];
    NewMapsActivity newa2Fragment;
    ItinearyFragment itinearyFragment;
    Poi_Fragment poi_fragment;
    WeatherFragment weatherFragment;
    Menu menu;
    WeatherFragment a2Fragment4;

    private List<String> myData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setFabHandler(new FabHandler());
        mTitle = mDrawerTitle = getTitle();

        mNavigationDrawerItemTitles = getResources().getStringArray(R.array.navigation_drawer_items_array);
        cd = new WIFIInternetConnectionDetector(getApplicationContext());
        isConnectionExist = cd.checkMobileInternetConn();
        //mBottomDrawerItemTitles= getResources().getStringArray(R.array.bottom_drawer_items_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        //swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe);
       /* swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
               *//* ItinearyFragment newFragment = new ItinearyFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.content_frame, newFragment);
                transaction.addToBackStack(null);
                // Commit the transaction
                transaction.commit();*//*

            }
        });*/
       /* try{
            Thread.sleep(1500);
            swipeRefreshLayout.setRefreshing(false);
            swipeRefreshLayout.dispatchSetActivated(false);
        }catch(InterruptedException e)
        {
            System.out.println(e);
        }*/
        setupToolbar();
      /*LayoutInflater inflater = getLayoutInflater();
       ViewGroup header = (ViewGroup)inflater.inflate(R.layout.drawer_header,mDrawerList,false);
        mDrawerList.addHeaderView(header);*/


        //Changed amar
        ItinearyFragment();
        // headerView.findViewById(R.id.navigation_header_text);
        FloatingActionButton fab = findViewById(R.id.baseFloatingActionButton);
        getAnimations();
        // DataModel[] drawerItem = new DataModel[16];
       /* LayoutInflater inflater = getLayoutInflater();
        ViewGroup header = (ViewGroup)inflater.inflate(R.layout.drawer_header,mDrawerList,false);
        mDrawerList.addHeaderView(header);*/
        drawerItem[0] = new DataModel(R.drawable.profile_ico, "Profile");
        drawerItem[1] = new DataModel(R.drawable.itinerary_ico, "Itinerary");
        drawerItem[2] = new DataModel(R.drawable.chat_ico, "My Group");
        drawerItem[3] = new DataModel(R.drawable.poi_ico, "Point of Interest");
        drawerItem[4] = new DataModel(R.drawable.upcoming_tour_ico, "Optionals");
        drawerItem[5] = new DataModel(R.drawable.flight_ico, "Flights");
        drawerItem[6] = new DataModel(R.drawable.hotel_ico, "Hotels");
        drawerItem[7] = new DataModel(R.drawable.document_ico, "Documents");
        drawerItem[8] = new DataModel(R.drawable.weather_ico, "Weather");
        drawerItem[9] = new DataModel(R.drawable.map_ico, "Map");
        drawerItem[10] = new DataModel(R.drawable.upcoming_tour_ico, "Upcoming Tour");
        drawerItem[11] = new DataModel(R.drawable.sos_ico, "SOS");
        drawerItem[12] = new DataModel(R.drawable.term_condition_ico, "Terms & Conditions");
        // drawerItem[12] = new DataModel(R.drawable.flight_ico, "Feedback");
        drawerItem[13] = new DataModel(R.drawable.contact_ico, "Contact Support");
        drawerItem[14] = new DataModel(R.drawable.flight_ico, "Feedback");
        drawerItem[15] = new DataModel(R.drawable.settings_ico, "Settings");
        drawerItem[16] = new DataModel(R.drawable.settings_ico, "PlaCard");

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);
        DrawerItemCustomAdapter adapter = new DrawerItemCustomAdapter(this, R.layout.list_view_item_row, drawerItem);
        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        setupDrawerToggle();
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom);
        bottomNavigationView.setItemIconTintList(null);
        mapbottomView = (BottomNavigationView) findViewById(R.id.mapbottom);

        menu = mapbottomView.getMenu();

        mapbottomView.setItemIconTintList(null);
        BottomNavigationViewHelper.removeShiftMode(bottomNavigationView);
        BottomNavigationViewHelper.removeShiftMode(mapbottomView);
        //loadFragment(new ItinearyFragment());
        //  final Fragment[] fragment = {null};
        // bottomNavigationView.setOnNavigationItemSelectedListener((BottomNavigationView.OnNavigationItemSelectedListener) this);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.itinery:
                        mapbottomView.setVisibility(View.GONE);
                        mDrawerLayout.closeDrawer(mDrawerList);
                        toolbar.setTitle(company_name);

                        ItinearyFragment a2Fragment = new ItinearyFragment();
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.content_frame, a2Fragment).commit();

                     /*   if (itinearyFragment == null) {
                            itinearyFragment = new ItinearyFragment();
                           *//*  Bundle arg = new Bundle();
                             arg.putString("itemNumber", "0");*//*
                            Log.e("in itinearyFragment","itinearyFragment");
                            FragmentTransaction transactio = getSupportFragmentManager().beginTransaction();
                            // itinearyFragment.setArguments(arg);
                            transactio.replace(R.id.content_frame, itinearyFragment, "itinearyFragment").commit();

                        } else {
                            FragmentTransaction transactio = getSupportFragmentManager().beginTransaction();
                            transactio.replace(R.id.content_frame, itinearyFragment, "itinearyFragment").commit();
                            //  itinearyFragment.tabClick(String.valueOf(0));
                        }*/


                        break;
                    case R.id.group:
                        toolbar.setTitle(company_name);
                        mDrawerLayout.closeDrawer(mDrawerList);
                        mapbottomView.setVisibility(View.GONE);
                        MyGroupFragment a2Fragment1 = new MyGroupFragment();
                        FragmentTransaction transaction1 = getSupportFragmentManager().beginTransaction();
                        transaction1.replace(R.id.content_frame, a2Fragment1).commit();
                        break;
                    case R.id.poi:
                        toolbar.setTitle(company_name);
                        mDrawerLayout.closeDrawer(mDrawerList);
                        mapbottomView.setVisibility(View.GONE);
                       /* Poi_Fragment poi_fragment = new Poi_Fragment();
                        FragmentTransaction transactio_poi = getSupportFragmentManager().beginTransaction();
                        transactio_poi.replace(R.id.content_frame, poi_fragment).commit();*/

                        Poi_Fragment a2Fragment2 = new Poi_Fragment();
                        FragmentTransaction transaction2 = getSupportFragmentManager().beginTransaction();
                        transaction2.replace(R.id.content_frame, a2Fragment2).commit();
                       /* if (poi_fragment == null) {
                            poi_fragment = new Poi_Fragment();
                  // Bundle arg = new Bundle();
                 //  arg.putString("itemNumber", "2");
                            FragmentTransaction transactio = getSupportFragmentManager().beginTransaction();
                            //     poi_fragment.setArguments(arg);
                            transactio.replace(R.id.content_frame, poi_fragment, "poi_fragment").commit();
                        } else {
                            FragmentTransaction transactio = getSupportFragmentManager().beginTransaction();
                            transactio.replace(R.id.content_frame, poi_fragment, "poi_fragment").commit();
                            //  itinearyFragment.tabClick(String.valueOf(0));
                        }*/
                        break;
                    case R.id.weather:
                        toolbar.setTitle(company_name);
                        mDrawerLayout.closeDrawer(mDrawerList);
                        mapbottomView.setVisibility(View.GONE);
                        WeatherFragment weatherFragment = new WeatherFragment();
                        FragmentTransaction transactionWeat = getSupportFragmentManager().beginTransaction();
                        transactionWeat.replace(R.id.content_frame, weatherFragment).commit();

                       /* if (a2Fragment4 == null) {
                            a2Fragment4 = new WeatherFragment();
                            FragmentTransaction transaction4 = getSupportFragmentManager().beginTransaction();
                            transaction4.replace(R.id.content_frame, a2Fragment4, "a2Fragment4").commit();
                        } else {
                            FragmentTransaction transaction4 = getSupportFragmentManager().beginTransaction();
                            transaction4.replace(R.id.content_frame, a2Fragment4, "a2Fragment4").commit();
                        }*/

                        break;
                    case R.id.map:
                        // toolbar.setTitle(company_name);
                        drawerItem = new DataModel[17];
                        drawerItem[16] = new DataModel(R.drawable.settings_ico, "Settings");
                        mDrawerLayout.closeDrawer(mDrawerList);
                        mapbottomView.setVisibility(View.VISIBLE);
                        toolbar.setTitle(company_name);
                        mDrawerLayout.closeDrawer(mDrawerList);
                        mapbottomView.setVisibility(View.VISIBLE);
                        if (newa2Fragment == null) {
                            newa2Fragment = new NewMapsActivity();
                            Bundle arg = new Bundle();
                            arg.putString("itemNumber", "0");
                            FragmentTransaction transactio = getSupportFragmentManager().beginTransaction();
                            newa2Fragment.setArguments(arg);
                            transactio.replace(R.id.content_frame, newa2Fragment, "newa2fragment").commit();
                        } else {
                            FragmentTransaction transactio = getSupportFragmentManager().beginTransaction();
                            transactio.replace(R.id.content_frame, newa2Fragment, "newa2fragment").commit();
                            newa2Fragment.tabClick(String.valueOf(0));
                        }

                        break;
                }
                return true;
            }
        });
        mapbottomView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.poi:
                        toolbar.setTitle(company_name);
                        mDrawerLayout.closeDrawer(mDrawerList);
                        mapbottomView.setVisibility(View.VISIBLE);
                        if (newa2Fragment == null) {
                            newa2Fragment = new NewMapsActivity();
                            Bundle arg = new Bundle();
                            arg.putString("itemNumber", "0");
                            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                            newa2Fragment.setArguments(arg);
                            transaction.replace(R.id.content_frame, newa2Fragment).commit();
                        } else {
                            newa2Fragment.tabClick(String.valueOf(0));
                        }

                        break;
                    case R.id.optional:
                        toolbar.setTitle(company_name);
                        mDrawerLayout.closeDrawer(mDrawerList);
                        mapbottomView.setVisibility(View.VISIBLE);
                        newa2Fragment.tabClick(String.valueOf(1));
                        Log.e("",String.valueOf(1));
                        Log.d("",String.valueOf(1));


                      /*  NewMapsActivity a2Fragment2 = new NewMapsActivity();
                        Bundle args = new Bundle();
                        args.putString("itemNumber", "1");
                        FragmentTransaction transactions = getSupportFragmentManager().beginTransaction();
                        a2Fragment2.setArguments(args);
                        transactions.replace(R.id.content_frame, a2Fragment2).commit();*/
                        break;
                    case R.id.restaurant:
                        toolbar.setTitle(company_name);
                        mDrawerLayout.closeDrawer(mDrawerList);
                        mapbottomView.setVisibility(View.VISIBLE);
                        newa2Fragment.tabClick(String.valueOf(2));

                        break;
                    case R.id.shopping:
                        toolbar.setTitle(company_name);
                        mDrawerLayout.closeDrawer(mDrawerList);
                        mapbottomView.setVisibility(View.VISIBLE);
                        newa2Fragment.tabClick(String.valueOf(3));
                        break;
                    case R.id.viewall:
                        //  menuItem.setIcon(R.drawable.map_hide_all_ico);
                        menuItem.setTitle("View All");
                        toolbar.setTitle(company_name);
                        // menu.findItem(R.id.viewall).setIcon(R.drawable.map_hide_all_ico);
                        mDrawerLayout.closeDrawer(mDrawerList);
                        mapbottomView.setVisibility(View.VISIBLE);
                        newa2Fragment.tabClick(String.valueOf(4));
                        break;
                }
                return true;
            }
        });
        hotel = (RelativeLayout) findViewById(R.id.shareDoc);
        hotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toolbar.setTitle("Hotel");
                HotelsFragment hotelsFragment = new HotelsFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.content_frame, hotelsFragment).commit();
                collapseFabMenu();
            }
        });
        tour = (RelativeLayout) findViewById(R.id.shareLayout);
        tour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toolbar.setTitle("Optionals");
                TourFragment tourFragment = new TourFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.content_frame, tourFragment).commit();
                collapseFabMenu();
            }
        });
        sos = (RelativeLayout) findViewById(R.id.shareLay);
        sos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toolbar.setTitle("SOS");
                SOSFragment sosFragment = new SOSFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.content_frame, sosFragment).commit();
                collapseFabMenu();
            }
        });
        document = (RelativeLayout) findViewById(R.id.createLayout);
        document.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toolbar.setTitle("Documents");
                DocumentFragment documentFragment = new DocumentFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.content_frame, documentFragment).commit();
                collapseFabMenu();
            }
        });
    }

    private void getAnimations() {
        fabOpenAnimation = AnimationUtils.loadAnimation(this, R.anim.fab_open);
        fabCloseAnimation = AnimationUtils.loadAnimation(this, R.anim.fab_close);
    }

    public void ItinearyFragment() {
        ItinearyFragment newFragment = new ItinearyFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, newFragment);
        transaction.addToBackStack(null);
        // Commit the transaction
        transaction.commit();
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    private void selectItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                Intent intent = new Intent(this, ProfileActivity.class);
                startActivity(intent);
                mDrawerLayout.closeDrawer(mDrawerList);
                //*  fragment = new ProfileFragment();*//*
                mapbottomView.setVisibility(View.GONE);
                break;

            case 1:
                fragment = new ItinearyFragment();
                mapbottomView.setVisibility(View.GONE);
                break;
            case 2:
                fragment = new MyGroupFragment();
                mapbottomView.setVisibility(View.GONE);
                break;
            case 3:
                fragment = new Poi_Fragment();
                mapbottomView.setVisibility(View.GONE);
                break;
            case 4:
                fragment = new OptionalFragment();
                mapbottomView.setVisibility(View.GONE);
                break;
            case 5:
                fragment = new FlightsFragment();
                mapbottomView.setVisibility(View.GONE);
                break;
            case 6:
                fragment = new HotelsFragment();
                mapbottomView.setVisibility(View.GONE);
                break;
            case 7:
                fragment = new DocumentFragment();
                mapbottomView.setVisibility(View.GONE);
                break;
            case 8:
                fragment = new WeatherFragment();
                mapbottomView.setVisibility(View.GONE);
                break;
            case 9:
                /*fragment = new MapFragment();
                Bundle arg = new Bundle();
                arg.putString("itemNumber", "0");
                fragment.setArguments(arg);*/

                mapbottomView.setVisibility(View.VISIBLE);
                if (fragment == null) {
                  //  newa2Fragment = new NewMapsActivity();
                    fragment = new MapFragment();
                    Bundle arg = new Bundle();
                    arg.putString("itemNumber", "0");
                    FragmentTransaction transactio = getSupportFragmentManager().beginTransaction();
                    fragment.setArguments(arg);
                    transactio.replace(R.id.content_frame, fragment, "newa2fragment").commit();
                } else {
                    FragmentTransaction transactio = getSupportFragmentManager().beginTransaction();
                    transactio.replace(R.id.content_frame, fragment, "newa2fragment").commit();
                   // fragment.tabClick(String.valueOf(0));
                    ((MapFragment) fragment).tabClick(String.valueOf(0));
                }

                //old
                /*newa2Fragment = new NewMapsActivity();
                Bundle arg = new Bundle();
                arg.putString("itemNumber", "0");
                FragmentTransaction transactio = getSupportFragmentManager().beginTransaction();
                newa2Fragment.setArguments(arg);*/

                //new
              /*  drawerItem = new DataModel[17];
                drawerItem[16] = new DataModel(R.drawable.settings_ico, "Settings");
                mDrawerLayout.closeDrawer(mDrawerList);
                mapbottomView.setVisibility(View.VISIBLE);
                toolbar.setTitle(company_name);
                mDrawerLayout.closeDrawer(mDrawerList);
                mapbottomView.setVisibility(View.VISIBLE);*/

               /* fragment = new MapFragment();
                Bundle arg = new Bundle();
                arg.putString("itemNumber", "0");
                fragment.setArguments(arg);*/
                //mapbottomView.setVisibility(View.GONE);
                break;
            case 10:
                fragment = new TourFragment();
                mapbottomView.setVisibility(View.GONE);
                break;
            case 11:
                fragment = new SOSFragment();
                mapbottomView.setVisibility(View.GONE);
                break;
            case 12:
                fragment = new TConditionFragment();
                mapbottomView.setVisibility(View.GONE);
                break;
            case 13:
                fragment = new SupportFragment();
                mapbottomView.setVisibility(View.GONE);
                break;
            case 14:
                fragment = new FeedbackFragment();
                mapbottomView.setVisibility(View.GONE);
                break;
            case 15:
                fragment = new SettingFragment();
                mapbottomView.setVisibility(View.GONE);
                break;

            case 16:
                fragment = new PlaCard();
                mapbottomView.setVisibility(View.GONE);
                break;
            default:
                fragment = new ItinearyFragment();
                mapbottomView.setVisibility(View.GONE);
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
            mDrawerList.setItemChecked(position, true);
            mDrawerList.setSelection(position);
            setTitle(mNavigationDrawerItemTitles[position]);
            //   setTitle(mBottomDrawerItemTitles[position]);
            //setBottomNavigationViewTitle(mBottomDrawer);
            mDrawerLayout.closeDrawer(mDrawerList);

        } else {
            Log.e("MainActivity", "Error in creating fragment");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.notification, menu);
        return true;
    }
    /*public  void setmDrawerTitle(CharSequence title)
{
    mDrawerTitle =title;
    getSupportActionBar().setTitle(mDrawerTitle);
}*/
   /* @Override
    public void setBottomNavigationViewTitle(CharSequence mBottomDrawer) {
        mBottomDrawer = mBottomDrawer;
        getSupportActionBar().setTitle(mBottomDrawer);
    }*/

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    void setupToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    void setupDrawerToggle() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.app_name, R.string.app_name);
        //This is necessary to change the icon of the Drawer Toggle upon state change.
        mDrawerToggle.syncState();
    }

    private void expandFabMenu() {
        ViewCompat.animate(binding.baseFloatingActionButton).rotation(45.0F).withLayer().setDuration(300).setInterpolator(new OvershootInterpolator(10.0F)).start();
        binding.createLayout.startAnimation(fabOpenAnimation);
        binding.shareLayout.startAnimation(fabOpenAnimation);
        binding.shareLay.startAnimation(fabOpenAnimation);
        // binding.shareL.startAnimation(fabOpenAnimation);
        binding.shareDoc.startAnimation(fabOpenAnimation);
   /*     binding.createFab.setClickable(true);
        binding.shareFab.setClickable(true);*/
        isFabMenuOpen = true;
    }

    private void collapseFabMenu() {
        ViewCompat.animate(binding.baseFloatingActionButton).rotation(0.0F).withLayer().setDuration(300).setInterpolator(new OvershootInterpolator(10.0F)).start();
        binding.createLayout.startAnimation(fabCloseAnimation);
        binding.shareLayout.startAnimation(fabCloseAnimation);
        binding.shareLay.startAnimation(fabCloseAnimation);
        //   binding.shareL.startAnimation(fabCloseAnimation);
        binding.shareDoc.startAnimation(fabCloseAnimation);
     /*   binding.createFab.setClickable(false);
        binding.shareFab.setClickable(false);*/
        isFabMenuOpen = false;
    }

    public class FabHandler {
        public void onBaseFabClick(View view) {
            if (isFabMenuOpen)
                collapseFabMenu();
            else
                expandFabMenu();
        }
    }

    @Override
    public void onBackPressed() {//ctrl+alt+l = format
        if (bottomNavigationView.getSelectedItemId() == R.id.itinery) {
           // popupExit();

        } else {
            bottomNavigationView.setSelectedItemId(R.id.itinery);
            ItinearyFragment();
        }

       /* if (isFabMenuOpen)
            collapseFabMenu();
        else
            super.onBackPressed();*/
    }

    public void popupExit() {
        new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Exit")
                .setMessage("Are you sure you want to exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).setNegativeButton("No", null).show();
    }




  /*  @Override
    public void onRestart() {
        super.onRestart();
        Log.d("onRestart", "onRestart");
        ItinearyFragment();
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.d("onResume", "onResume");
        ItinearyFragment();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    // Called at the end of the visible lifetime.
    @Override
    public void onStop() {
        super.onStop();
    }*/


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("onDestroy", "onDestroy");
    }
}

package com.example.steven.joetzandroid.Activities;

import android.app.Activity;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.steven.joetzandroid.Adapters.NavDrawerListAdapter;
import com.example.steven.joetzandroid.Domain.NavDrawerItem;
import com.example.steven.joetzandroid.R;
import com.firebase.client.Firebase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class StartActivity extends FragmentActivity{

    private final String TAG = "StartActitivty";
    private ImageView imgJoetz;
    private TextView beschrijvingTxtView;
    private ImageView binnenlandView;
    private ImageView inleefView;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private ListView drawerList;
    private LinearLayout drawerLinear;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;

    private String[] navMenuTitles;
    private TypedArray navMenuIcons;

    private ArrayList<NavDrawerItem> navDrawerItems;
    private NavDrawerListAdapter adapter;

    //hasmap die de fragmenten bevat met als key de string die in de lijst staat, later gewoon Fragment openen uit hashmap
    //private HashMap<String, Fragment> fragmentHashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //code weg voor testen van drawer menu
        //setContentView(R.layout.activity_start);
        /*imgJoetz = (ImageView)findViewById(R.id.imageView);
        beschrijvingTxtView = (TextView)findViewById(R.id.beschrijvingTxtView);

        beschrijvingTxtView.setText("Vakanties voor kinderen en jongeren. Provincie West-Vlaanderen");

        binnenlandView = (ImageView)findViewById(R.id.binnelandImage);
        inleefView = (ImageView)findViewById(R.id.inleefImage);
        */
        Firebase.setAndroidContext(this);
        setContentView(R.layout.activity_main);

        mTitle = mDrawerTitle = getTitle();


        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);

        navMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);
        drawerLinear = (LinearLayout)findViewById(R.id.drawer_linear);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerList = (ListView)findViewById(R.id.list_slidermenu);
        navDrawerItems = new ArrayList<NavDrawerItem>();
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[0],navMenuIcons.getResourceId(0,-1)));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[1],navMenuIcons.getResourceId(1,-1)));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[2],navMenuIcons.getResourceId(2,-1)));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[3],navMenuIcons.getResourceId(3,-1)));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[4],navMenuIcons.getResourceId(4,-1)));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[5],navMenuIcons.getResourceId(5,-1)));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[6],navMenuIcons.getResourceId(6,-1)));
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[7],navMenuIcons.getResourceId(7,-1)));

        navMenuIcons.recycle();

        adapter = new NavDrawerListAdapter(getApplicationContext(),navDrawerItems);
        drawerList.setAdapter(adapter);

        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,
                R.drawable.ic_drawer,
                R.string.app_name,
                R.string.app_name){
            public void onDrawerClosed(View view){
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu();
            }
            public void onDrawerOpenend(View view)
            {
                getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu();
            }

        };
        drawerLayout.setDrawerListener(drawerToggle);
        if(savedInstanceState == null)
        {
            LoginFragment f = new LoginFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.frame_container, f).commit();
        }
    }
/*
    public void binnenLandClicked(View view)
    {
        Log.d(TAG,"BinnenlandClicked");
    }
    public void inleefClicked(View view)
    {
        Log.d(TAG,"inleefClicked");
    }
*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.start, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        if (drawerToggle.onOptionsItemSelected(item))
        {
            Log.d(TAG,"drawerToggle onOptionItemSelected");
            return true;
        }
        switch (item.getItemId())
        {
            case R.id.action_login:
                LoginFragment f = new LoginFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.frame_container,f).commit();

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //na het aanroepen van invalidateOptionsMenu()
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean drawerOpen = drawerLayout.isDrawerOpen(drawerLinear);
        menu.findItem(R.id.action_login).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);
        getActionBar().setTitle(mTitle);
    }
}

package com.beanielab.template;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.beanielab.template.Fragment.StartFragment;
import com.beanielab.template.Util.ApiASyncTask;
import com.beanielab.template.Util.Tasks.GetDataTask;

import java.util.ArrayList;

public class MainFragmentActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private FrameLayout fragment;
    private int fragmentId = R.id.fragmentFL;
    private GetDataTask getDataTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_fragment);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        toolbar.setTitle("");

        setSupportActionBar(toolbar);

        fragment = (FrameLayout) findViewById(R.id.fragmentFL);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Apply first fragment
        replaceFragment(new StartFragment(), null, StartFragment.class.getSimpleName());
    }

    private void doApiCall() {
        if (getDataTask != null && getDataTask.isFetching()) {
            return;
        }

        getDataTask = new GetDataTask(new ApiASyncTask.ASyncListener<ArrayList<String>>() {
            @Override
            public void onSuccess(ArrayList<String> result) {
            }

            @Override
            public void onError(int code) {
            }

            @Override
            public void onUnAuthorized(int code) {
            }

            @Override
            public void onAlways() {
            }
        });

        getDataTask.execute();
    }

    public void replaceFragment(Fragment newFragment, Bundle data, String transactionName) {
        if (data != null) {
            newFragment.setArguments(data);
        }

        final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(fragmentId, newFragment, transactionName);
        ft.addToBackStack(transactionName);
        ft.commit();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Log.d("", "" + id);

        if (id == R.id.menu_start) {
            replaceFragment(new StartFragment(), null, StartFragment.class.getSimpleName());
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.demo.shuaiwang.magicdemo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.demo.shuaiwang.magicdemo.activity.BaseActivity;
import com.demo.shuaiwang.magicdemo.fragment.BaseFragment;
import com.demo.shuaiwang.magicdemo.fragment.DiscoveryFragment;
import com.demo.shuaiwang.magicdemo.fragment.MessageFragment;
import com.demo.shuaiwang.magicdemo.fragment.NowFragment;

/**
 * TODO
 */
public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";
    private DrawerLayout mDrawerLayout;
    public  TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

         tabLayout = (TabLayout) findViewById(R.id.tabs);
        //tabLayout.setupWithViewPager(viewPager);

        //tabLayout.setupWithViewPager(nowFragment.viewPager);
        NowFragment nowFragment =  NowFragment.newInstance("str1", "str2");
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_fragment_container,nowFragment,null);
        fragmentTransaction.commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sample_actions, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {



                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                Fragment fragment = null;
                Class fragmentClass = NowFragment.class;
                int id = menuItem.getItemId();

                if (id == R.id.nav_now) {
                    Log.i(TAG,"---->> onNavigationItemSelected nav_now");
                    fragmentClass = NowFragment.class;

                } else if (id == R.id.nav_discovery) {
                    Log.i(TAG,"---->> onNavigationItemSelected nav_discovery");
                    fragmentClass = DiscoveryFragment.class;
                } else if (id == R.id.nav_message) {
                    Log.i(TAG,"---->> onNavigationItemSelected nav_message");
                    fragmentClass = MessageFragment.class;
                } else if (id == R.id.nav_mine) {
                    Log.i(TAG,"---->> onNavigationItemSelected nav_mine");
                    fragmentClass = BaseFragment.class;
                }

                try {
                    fragment = (Fragment) fragmentClass.newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                ft.replace(R.id.main_fragment_container, fragment);
                ft.commit();
                menuItem.setChecked(true);
                mDrawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }



}

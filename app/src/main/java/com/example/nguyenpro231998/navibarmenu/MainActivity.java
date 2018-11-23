package com.example.nguyenpro231998.navibarmenu;

import android.content.res.Configuration;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.example.nguyenpro231998.navibarmenu.Adapter.CustomAdapter;
import com.example.nguyenpro231998.navibarmenu.Clicker.FragmentNavigation;
import com.example.nguyenpro231998.navibarmenu.Interface.Navigation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private String mActivityTitle;
    private String[] items;

    private ExpandableListView expandableListView;
    private ExpandableListAdapter adapter;
    private List<String> lsTitle;
    private Map<String,List<String>> lsChild;
    private Navigation navigationManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mActivityTitle = getTitle().toString();
        expandableListView = (ExpandableListView) findViewById(R.id.nvlist);
        navigationManager = FragmentNavigation.getmInstance(this);
        initItems();

        View listHeaderView = getLayoutInflater().inflate(R.layout.nav_header,null,false);
        expandableListView.addHeaderView(listHeaderView);
        getDada();
        addDrawersItem();
        setupDrawer();

        if(savedInstanceState == null)
               selectFirstItemAsDefault();
               getSupportActionBar().setDisplayHomeAsUpEnabled(true);
               getSupportActionBar().setHomeButtonEnabled(true);
               getSupportActionBar().setTitle("Nguyễn Anh Nguyên");


    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    private void selectFirstItemAsDefault() {
        if(navigationManager != null){
               String firstItem = lsTitle.get(0);
               navigationManager.showFragment(firstItem);
               getSupportActionBar().setTitle(firstItem);
        }
    }

    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close)
        {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("Nguyễn Anh Nguyên");
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getSupportActionBar().setTitle(mActivityTitle);
                invalidateOptionsMenu();
            }
        };
        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);

    }

    private void addDrawersItem(){
        adapter = new CustomAdapter(this,lsTitle,lsChild);
        expandableListView.setAdapter(adapter);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                getSupportActionBar().setTitle(lsTitle.get(groupPosition).toString());
            }
        });
        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                getSupportActionBar().setTitle("Nguyễn Anh Nguyên");
            }
        });
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                String selectedItem = ((List)(lsChild.get(lsTitle.get(groupPosition)))).get(childPosition).toString();

                if(items[0].equals(lsTitle.get(groupPosition))){
                    navigationManager.showFragment(selectedItem);
                }
                else {
                    throw  new  IllegalArgumentException("Not Support fragment");
                }
                mDrawerLayout.closeDrawer(GravityCompat.START);
                return false;
            }
        });
    }
    private void getDada() {
         List<String> title = Arrays.asList("Android Programing", "Xamarin Programing", "IOS Programing");
         List<String> child = Arrays.asList("Beginner", "Intermediate", "Advanced", "Professional");
         lsChild = new TreeMap<>();
         lsChild.put(title.get(0),child);
         lsChild.put(title.get(1),child);
         lsChild.put(title.get(2),child);

         lsTitle = new ArrayList<>(lsChild.keySet());

    }

    private void initItems() {
    items = new String[]{"Android Programing", "Xamarin Programing", "IOS Programing"};
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(mDrawerToggle.onOptionsItemSelected(item)){
           return  true;
        }
        return super.onOptionsItemSelected(item);
    }
}

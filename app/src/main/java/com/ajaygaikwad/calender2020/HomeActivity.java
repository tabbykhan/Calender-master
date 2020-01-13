package com.ajaygaikwad.calender2020;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.ajaygaikwad.calender2020.Adapter.CustomExpandableListAdapter;
import com.ajaygaikwad.calender2020.Fragments.CalenderFragment;
import com.ajaygaikwad.calender2020.Fragments.HolidayFragment;
import com.ajaygaikwad.calender2020.Fragments.MuhuratFragment;
import com.ajaygaikwad.calender2020.Fragments.NotesFragment;
import com.ajaygaikwad.calender2020.Fragments.ReminderFragment;
import com.ajaygaikwad.calender2020.Pojo.ExpandableListModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    private ExpandableListAdapter mExpandableListAdapter;
    private ArrayList<ExpandableListModel> mExpandableListTitle;
    private HashMap<String, ArrayList<String>> mExpandableListData;
    Toolbar toolbar;
    ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private ExpandableListView mExpandableListView;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private FragmentManager fragmentManager;
    private long lastPressedTime;
    private static final int PERIOD = 2000;
    View currentView = null;
    int mAvailableBalance;
    ImageView btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);

        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = preferences.edit();
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        //set the back arrow in the toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(R.string.app_name);
        }

        mTitle = mDrawerTitle = getTitle();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mExpandableListView = findViewById(R.id.navList);
        mExpandableListView.setIndicatorBounds(mExpandableListView.getRight() + 120, mExpandableListView.getWidth());
        setupToolbar();

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);

        prepareListData();

        fragmentManager = getSupportFragmentManager();
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        setupDrawerToggle();

        fragmentManager.beginTransaction().replace(R.id.content_frame, CalenderFragment.newInstance()).commit();

        mExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                int index = parent.getFlatListPosition(ExpandableListView.getPackedPositionForChild(groupPosition, childPosition));
                parent.setItemChecked(index, true);


                return true;
            }
        });


        mExpandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {

                switch (groupPosition) {
                    case 0:
                        fragmentManager.beginTransaction().replace(R.id.content_frame, CalenderFragment.newInstance()).commit();
                        getSupportActionBar().setTitle("Calender 2020");
                        mDrawerLayout.closeDrawers();
                        break;
                    case 1:
                        fragmentManager.beginTransaction().replace(R.id.content_frame, HolidayFragment.newInstance()).commit();
                        getSupportActionBar().setTitle("Holiday");
                        mDrawerLayout.closeDrawers();
                        break;
                    case 2:
                        fragmentManager.beginTransaction().replace(R.id.content_frame, MuhuratFragment.newInstance()).commit();
                        getSupportActionBar().setTitle("Muhurat");
                        mDrawerLayout.closeDrawers();
                        break;
                    case 3:
                        fragmentManager.beginTransaction().replace(R.id.content_frame, NotesFragment.newInstance()).commit();
                        getSupportActionBar().setTitle("Notes");
                        mDrawerLayout.closeDrawers();
                        break;

                    case 4:
                        fragmentManager.beginTransaction().replace(R.id.content_frame, ReminderFragment.newInstance()).commit();
                        getSupportActionBar().setTitle("Reminder");
                        mDrawerLayout.closeDrawers();
                        break;

                    case 5:
                        Intent send = new Intent(Intent.ACTION_SEND);
                        send.setType("text/plane");
                        String shareBody = "Download Calender 2020 Android App";
                        String shareSub = "Download Calender 2020 Android App \n https://play.google.com/store/apps/details?id=com.ajaygaikwad.calender2020";
                        send.putExtra(Intent.EXTRA_SUBJECT, shareBody);
                        send.putExtra(Intent.EXTRA_TEXT, shareSub);
                        startActivity(Intent.createChooser(send, "Share with"));
                        mDrawerLayout.closeDrawers();
                        break;

                }
                return false;
            }
        });

        mExpandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                for (int i = 0; i < mExpandableListView.getCount(); i++) {
                    if (i != groupPosition) {
                        mExpandableListView.collapseGroup(i);
                    }
                }
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        if (fragments != null) {
            for (Fragment fragment : fragments) {
                fragment.onRequestPermissionsResult(requestCode, permissions, grantResults);
            }
        }
    }

    void setupDrawerToggle() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.app_name, R.string.app_name);
        //This is necessary to change the icon of the Drawer Toggle upon state change.
        mDrawerToggle.syncState();
    }

    private void prepareListData() {

        mExpandableListTitle = new ArrayList<ExpandableListModel>();
        mExpandableListData = new HashMap<String, ArrayList<String>>();

        // Adding child data

        mExpandableListTitle.add(new ExpandableListModel(R.drawable.ic_drawer_home, "Calender"));
        mExpandableListTitle.add(new ExpandableListModel(R.drawable.ic_drawer_holiday, "Holiday"));
        mExpandableListTitle.add(new ExpandableListModel(R.drawable.ic_drawer_muhurat, "Muhurat"));
        mExpandableListTitle.add(new ExpandableListModel(R.drawable.ic_drawer_notes, "Notes"));
        mExpandableListTitle.add(new ExpandableListModel(R.drawable.ic_drawer_reminder, "Reminder"));
        mExpandableListTitle.add(new ExpandableListModel(R.drawable.ic__drawer_share, "Share"));


        ArrayList<String> allTransactions = new ArrayList<String>();


        mExpandableListData.put(mExpandableListTitle.get(0).title, allTransactions);
        mExpandableListData.put(mExpandableListTitle.get(1).title, allTransactions);
        mExpandableListData.put(mExpandableListTitle.get(2).title, allTransactions);
        mExpandableListData.put(mExpandableListTitle.get(3).title, allTransactions);
        mExpandableListData.put(mExpandableListTitle.get(4).title, allTransactions);
        mExpandableListData.put(mExpandableListTitle.get(5).title, allTransactions);


        mExpandableListAdapter = new CustomExpandableListAdapter(HomeActivity.this, mExpandableListTitle, mExpandableListData);
        mExpandableListView.setAdapter(mExpandableListAdapter);
    }

    void setupToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            switch (event.getAction()) {
                case KeyEvent.ACTION_DOWN:
                    if (event.getDownTime() - lastPressedTime < PERIOD) {
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "Press again to exit.",
                                Toast.LENGTH_SHORT).show();
                        lastPressedTime = event.getEventTime();
                    }
                    return true;
            }
        }
        return false;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
//            case R.id.logout:
//
//                 showAlertDialog();
//                 break;
        }
    }
}

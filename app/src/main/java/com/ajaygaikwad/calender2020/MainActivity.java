package com.ajaygaikwad.calender2020;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.ajaygaikwad.calender2020.Fragments.CalenderFragment;
import com.ajaygaikwad.calender2020.Fragments.HolidayFragment;
import com.ajaygaikwad.calender2020.Fragments.MuhuratFragment;
import com.ajaygaikwad.calender2020.Fragments.NotesFragment;
import com.ajaygaikwad.calender2020.Fragments.ReminderFragment;
import com.google.android.material.navigation.NavigationView;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = preferences.edit();


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame, new CalenderFragment()).addToBackStack("tag").commit();

        //getMessage();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            Fragment f = getSupportFragmentManager().findFragmentById(R.id.frame);
            if (f instanceof CalenderFragment) {
                try {
                    SweetAlertDialog pDialog = new SweetAlertDialog(MainActivity.this, SweetAlertDialog.WARNING_TYPE);
                    pDialog.setTitleText("Really Exit");
                    pDialog.setContentText("Sure you want to exit");
                    pDialog.setConfirmText("Yes");
                    pDialog.setCancelText("No");
                    pDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            /*Intent a = new Intent(Intent.ACTION_MAIN);
                            a.addCategory(Intent.CATEGORY_HOME);
                            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(a);*/
                            finish();
                            //finishAffinity();
                        }
                    });
                    pDialog.setCancelClickListener(null);

                    pDialog.show();
                    pDialog.setCancelable(false);
                } catch (Exception x) {
                    new AlertDialog.Builder(this)
                            .setTitle("Really Exit")
                            .setMessage("Sure you want to exit")
                            .setNegativeButton(android.R.string.no, null)
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface arg0, int arg1) {
                                    /*Intent a = new Intent(Intent.ACTION_MAIN);
                                    a.addCategory(Intent.CATEGORY_HOME);
                                    a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(a);*/
                                    finish();
                                    finishAffinity();
                                }
                            }).create().show();
                }
            } else {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.frame, new CalenderFragment()).addToBackStack("").commit();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_cal) {

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frame, new CalenderFragment()).addToBackStack("").commit();

            // Handle the camera action
        } else if (id == R.id.nav_holi) {

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frame, new HolidayFragment()).addToBackStack("").commit();


        } else if (id == R.id.nav_muhurt) {

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frame, new MuhuratFragment()).addToBackStack("").commit();

        } else if (id == R.id.nav_notes) {

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frame, new NotesFragment()).addToBackStack("").commit();

        } else if (id == R.id.nav_reminder) {

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frame, new ReminderFragment()).addToBackStack("").commit();

        } else if (id == R.id.nav_share) {

            Intent send = new Intent(Intent.ACTION_SEND);
            send.setType("text/plane");
            String shareBody = "Download Calender 2020 Android App";
            String shareSub = "Download Calender 2020 Android App \n https://play.google.com/store/apps/details?id=com.ajaygaikwad.calender2020";
            send.putExtra(Intent.EXTRA_SUBJECT, shareBody);
            send.putExtra(Intent.EXTRA_TEXT, shareSub);
            startActivity(Intent.createChooser(send, "Share with"));

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}

package de.dikodam.drivewithme;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.NumberPicker;

public class DriveActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drive);
        Toolbar toolbar = (Toolbar) findViewById(R.id.drive_toolbar);
        toolbar.setTitle(R.string.title_activity_drive);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_drive);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);

        NumberPicker numberPicker = (NumberPicker) findViewById(R.id.numberPicker);
        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(Integer.MAX_VALUE);
        numberPicker.setWrapSelectorWheel(false);

        SharedPreferences sharedPrefs = getSharedPreferences(getString(R.string.preferences_key), Context.MODE_PRIVATE);
        int saveMileage = sharedPrefs.getInt(getString(R.string.mileage_preference), 0);
        numberPicker.setValue(saveMileage);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this::handleNavigationItemSelected);

        toggle.syncState();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_drive);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences.Editor editor = getSharedPreferences(getString(R.string.preferences_key), Context.MODE_PRIVATE).edit();
        NumberPicker numberPicker = (NumberPicker) findViewById(R.id.numberPicker);
        int newMileagePreference = numberPicker.getValue();
        editor.putInt(getString(R.string.mileage_preference), newMileagePreference);
        editor.apply();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.drive, menu);
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

    public boolean handleNavigationItemSelected(MenuItem menuItem) {
        int id = menuItem.getItemId();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_drive);

        switch (id) {
            case R.id.nav_drive:
                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_refuel:
                startActivity(new Intent(this, RefuelActivity.class));
                break;
            case R.id.nav_passengers:
                startActivity(new Intent(this, PassengersActivity.class));
                break;

            case R.id.nav_share:
                //TODO
                break;
            case R.id.nav_send:
                //TODO
                break;
            default:
                break;
        }
        return true;
    }

}

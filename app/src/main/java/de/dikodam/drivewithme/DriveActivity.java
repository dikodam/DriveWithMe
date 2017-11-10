package de.dikodam.drivewithme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class DriveActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drive);
        Toolbar toolbar = (Toolbar) findViewById(R.id.drive_toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_drive);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);

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

    public void startActivityFromDrawer(MenuItem menuItem) {
        int id = menuItem.getItemId();

        String logTag = "APP:NAVI_BAR";
        String formatString = "nav item %s was chosen";

        switch (id) {
            case R.id.nav_drive:
                Log.d(logTag, String.format(formatString, "DRIVE"));
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_drive);
                drawer.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_refuel:
                //TODO change/leave activity (content?) to refueling
                Log.d(logTag, String.format(formatString, "REFUEL"));
                startActivity(new Intent(this, RefuelActivity.class));
                break;
            case R.id.nav_passengers:
                //TODO change/leave activity (content?) to passengers management
                Log.d(logTag, String.format(formatString, "PASSENGERS"));
                break;

            case R.id.nav_share:
                //TODO
                Log.d(logTag, String.format(formatString, "SHARE"));
                break;
            case R.id.nav_send:
                //TODO
                Log.d(logTag, String.format(formatString, "SEND"));
                break;
            default:
                break;
        }
    }

}

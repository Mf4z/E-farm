package emef4z.gmail.com.e_farm;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class Farmer_Control_Panel extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer__control__panel);



/*
        username = (TextView) findViewById(R.id.usern_show_tv);

        Intent intent = getIntent();
        String name = intent.getStringExtra("userdata");
        username.setText(name);*/





        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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

        if (id == R.id.nav_diagnose) {

           Intent myIntent = new Intent(getBaseContext(),Diagnose.class);
            startActivity(myIntent);
        }
        else if (id == R.id.nav_trade) {

           Intent myIntent = new Intent(getBaseContext(),Trade_Platform.class);
            startActivity(myIntent);

        } else if (id == R.id.nav_store) {

           Intent myIntent = new Intent(getBaseContext(),Farmer_storage_zone.class);
            startActivity(myIntent);

        }

        else if (id == R.id.nav_aboutus) {

           Intent myIntent = new Intent(getBaseContext(),about.class);
            startActivity(myIntent);

        }

        else if (id == R.id.nav_contactus) {

            Intent myIntent = new Intent(getBaseContext(),contact.class);
            startActivity(myIntent);


        }

        else if (id == R.id.farmer_Logout) {

            Intent myIntent = new Intent(getBaseContext(),Login.class);
            startActivity(myIntent);
            finish();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void movetoDiagnosis(View view)
    {
        Intent myIntent = new Intent(getBaseContext(),Diagnose.class);
        startActivity(myIntent);

    }

    public void movetoTradeZone(View view)
    {
        Intent myIntent = new Intent(getBaseContext(),Trade_Platform.class);
        startActivity(myIntent);
    }

    public void movetoStorage(View view)
    {
        Intent myIntent = new Intent(getBaseContext(),Farmer_storage_zone.class);
        startActivity(myIntent);
    }
}

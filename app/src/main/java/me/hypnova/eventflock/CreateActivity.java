package me.hypnova.eventflock;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.Calendar;
import java.util.TimeZone;
import java.util.Locale;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Siavash Samiei on 9/17/2016.
 */
public class CreateActivity extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
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

    public void createEvent(View view) {
        String name = findViewById(R.id.name).toString();
        String description = findViewById(R.id.description).toString();
        String location = findViewById(R.id.location).toString();
        Calendar time = Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault());

        DatePicker d_picker = (DatePicker)findViewById(R.id.date);
        time.set(Calendar.YEAR, d_picker.getYear());
        time.set(Calendar.MONTH, d_picker.getMonth());
        time.set(Calendar.DAY_OF_MONTH, d_picker.getDayOfMonth());

        TimePicker t_picker = (TimePicker)findViewById(R.id.time);
        time.set(Calendar.HOUR_OF_DAY,t_picker.getHour());
        time.set(Calendar.MINUTE,t_picker.getMinute());

        Event e = new Event(name,description,location,time);
        String key = MainActivity.database.getReference("group/").push().getKey();
        List <Character> list = new ArrayList<Character>();

        for(int k=0;k<key.length();k++)
            list.add(key.charAt(k));

        Collections.shuffle(list);

        key = "";

        for (int c=0;c<6;c++)
            key += list.get(c);

        MainActivity.database.getReference().child("group/").child(key).setValue(e);
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setTitle("Generated Code");
        builder1.setMessage("Your code is " + key + ". Share it with others who want to join your event!");
        builder1.setCancelable(true);
        builder1.setNeutralButton(android.R.string.ok,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home){
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }
        else if (id == R.id.nav_message) {
            // Handle the camera action
        } else if (id == R.id.nav_create) {
            Intent i = new Intent(this,CreateActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_join) {

        } else if (id == R.id.nav_exit) {
            AuthUI.getInstance()
                    .signOut(this).
                    addOnCompleteListener (new OnCompleteListener<Void>(){
                        public void onComplete (@NonNull Task<Void> task){
                            startActivity(new Intent(CreateActivity.this, MainActivity.class));
                            finish();
                        }});

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

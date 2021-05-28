package com.example.project.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;

import com.example.project.R;
import com.example.project.sharepreference.SharedPreferencesManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String role ="";

        if (SharedPreferencesManager.getLoginResponseValue(this) != null) {
            role = SharedPreferencesManager.getLoginResponseValue(this).getRole();
        }


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        //set up navigation bar for each role
        NavigationView navigationView = findViewById(R.id.nav_view);
        if (role.equals("Admin")) {
            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.admin_menu_drawer);
        } else if (role.equals("Trainer")) {
            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.trainer_menu_drawer);
        } else if (role.equals("Trainee")) {
            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.trainee_menu_drawer);
        }

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_assignment, R.id.nav_class, R.id.nav_module, R.id.nav_enrollment, R.id.nav_feedback, R.id.nav_question, R.id.nav_result, R.id.nav_contact)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_activity, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
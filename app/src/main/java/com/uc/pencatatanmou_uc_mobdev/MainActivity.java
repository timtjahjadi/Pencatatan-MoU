package com.uc.pencatatanmou_uc_mobdev;

import androidx.appcompat.app.AppCompatActivity;
import androidx.customview.widget.Openable;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private NavController navControllers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
//        if(getSupportActionBar()!=null) {
//            this.getSupportActionBar().hide();
//        }
        BottomNavigationView navigationView = findViewById(R.id.navview);

        AppBarConfiguration configuration = new AppBarConfiguration
                .Builder(R.id.loginFragment, R.id.nav_mou, R.id.nav_real, R.id.nav_profile)
                .build();

        navControllers = Navigation.findNavController(this, R.id.fragment_nav);

        navControllers.addOnDestinationChangedListener((controller, destination, arguments) -> {
            if (destination.getId() == R.id.nav_mou || destination.getId() == R.id.nav_real || destination.getId() == R.id.nav_profile) {
                navigationView.setVisibility(View.VISIBLE);
            } else {
                navigationView.setVisibility(View.GONE);
            }
        });

        NavigationUI.setupActionBarWithNavController(this, navControllers, configuration);
        NavigationUI.setupWithNavController(navigationView, navControllers);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navControllers, (Openable) null);
    }
}
package com.uc.pencatatanmou_uc_mobdev;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

<<<<<<< Updated upstream
=======
    private NavController navController;

    public static Context contextOfApplication;
    public static Context getContextOfApplication()
    {
        return contextOfApplication;
    }
>>>>>>> Stashed changes
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
<<<<<<< Updated upstream
=======
        contextOfApplication = getApplicationContext();

        BottomNavigationView navigationView = findViewById(R.id.navView);

        AppBarConfiguration configuration = new AppBarConfiguration
                .Builder(R.id.loginFragment, R.id.nav_mou, R.id.nav_real, R.id.nav_profile)
                .build();

        navController = Navigation.findNavController(this, R.id.fragment);

        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            if (destination.getId() == R.id.nav_mou || destination.getId() == R.id.nav_real || destination.getId() == R.id.nav_profile) {
                navigationView.setVisibility(View.VISIBLE);
            } else {
                navigationView.setVisibility(View.GONE);
            }
        });

        NavigationUI.setupActionBarWithNavController(this, navController, configuration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, (Openable) null);
>>>>>>> Stashed changes
    }
}
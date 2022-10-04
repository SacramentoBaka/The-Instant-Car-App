package com.example.firestoreapp.Authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.firestoreapp.About.AboutFragment;
import com.example.firestoreapp.Help.ContactUsFragment;
import com.example.firestoreapp.Help.HelpFragment;
import com.example.firestoreapp.Operations.CostFragment;
import com.example.firestoreapp.Operations.ManReservationFragment;
import com.example.firestoreapp.Operations.ManTermFragment;
import com.example.firestoreapp.Operations.NewReservationFragment;
import com.example.firestoreapp.Operations.PromotionFragment;
import com.example.firestoreapp.R;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private FirebaseUser user;
    private DatabaseReference reference;
    private String userId;

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private ActionBarDrawerToggle toggle;
    private View hView;
    private BottomAppBar bottomAppBar;
    public BottomNavigationView bottomNavigationView;
    private FloatingActionButton floatingActionButton;

    private TextView account;


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_drawer);
        bottomAppBar = findViewById(R.id.bottomAppBar);
        bottomNavigationView = findViewById(R.id.bottom_NavigationView);
        bottomNavigationView.setBackground(null);
        bottomNavigationView.getMenu().getItem(2).setEnabled(false);
        hView = navigationView.getHeaderView(0);
        account = (TextView) hView.findViewById(R.id.user_email);
        floatingActionButton = findViewById(R.id.floatingButton);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open_menu, R.string.close_menu);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bottomNavigationView.getMenu().getItem(2).setChecked(false);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new NewReservationFragment()).commit();
            }
        });

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.bottom_nav_home:
                        Log.i("Menu_Drawer_Tag", "Home item is clicked");
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.bottom_nav_Settings:
                        Log.i("Menu_Drawer_Tag", "Home item is clicked");
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SettingsFragment()).commit();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.bottom_nav_about:
                        Log.i("Menu_Drawer_Tag", "Home item is clicked");
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AboutFragment()).commit();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.bottom_nav_help:
                        Log.i("Menu_Drawer_Tag", "Profile item is clicked");
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HelpFragment()).commit();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }

                return true;
            }
        });

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.nav_home:
                        Log.i("Menu_Drawer_Tag", "Home item is clicked");
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
                        bottomNavigationView.getMenu().getItem(0).setChecked(true);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_profile:
                        Log.i("Menu_Drawer_Tag", "Profile item is clicked");
                        bottomNavigationView.getMenu().getItem(2).setChecked(false);
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProfileFragment()).commit();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_settings:
                        Log.i("Menu_Drawer_Tag", "Settings item is clicked");
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SettingsFragment()).commit();
                        bottomNavigationView.getMenu().getItem(1).setChecked(true);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_new_reserve:
                        Log.i("Menu_Drawer_Tag", "New Reservation item is clicked");
                        bottomNavigationView.getMenu().getItem(2).setChecked(false);
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new NewReservationFragment()).commit();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_manage_reserve:
                        Log.i("Menu_Drawer_Tag", "Manage Reservation item is clicked");
                        bottomNavigationView.getMenu().getItem(2).setChecked(false);
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ManReservationFragment()).commit();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_manage_term:
                        Log.i("Menu_Drawer_Tag", "Manage Term Reservation item is clicked");
                        bottomNavigationView.getMenu().getItem(2).setChecked(false);
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ManTermFragment()).commit();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_cost:
                        Log.i("Menu_Drawer_Tag", "Cost item is clicked");
                        bottomNavigationView.getMenu().getItem(2).setChecked(false);
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CostFragment()).commit();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_promotion:
                        Log.i("Menu_Drawer_Tag", "Promotion item is clicked");
                        bottomNavigationView.getMenu().getItem(2).setChecked(false);
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new PromotionFragment()).commit();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_contact_Us:
                        Log.i("Menu_Drawer_Tag", "ContactUs item is clicked");
                        bottomNavigationView.getMenu().getItem(2).setChecked(false);
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ContactUsFragment()).commit();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_help:
                        Log.i("Menu_Drawer_Tag", "Help item is clicked");
                        bottomNavigationView.getMenu().getItem(3).setChecked(true);
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HelpFragment()).commit();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_about:
                        Log.i("Menu_Drawer_Tag", "About item is clicked");
                        bottomNavigationView.getMenu().getItem(4).setChecked(true);
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AboutFragment()).commit();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_sign_out:
                        Log.i("Menu_Drawer_Tag", "Sign Out item is clicked");
                        FirebaseAuth.getInstance().signOut();
                        SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("remember", "false");
                        editor.apply();
                        startActivity(new Intent(MainActivity.this, Login.class));
                        finish();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                }
                return true;
            }
        });

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userId = user.getUid();


        reference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if (userProfile != null) {

                    //Get access
                    hView = navigationView.getHeaderView(0);
                    String user_name = userProfile.fullName;
                    account.setText(user_name.toUpperCase());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(MainActivity.this, "something went wrong", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
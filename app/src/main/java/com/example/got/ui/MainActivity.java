package com.example.got.ui;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.example.got.R;
import com.example.got.adapter.SectionsPagerAdapter;
import com.example.got.adapter.SignInAdapter;
import com.example.got.model.Translate;
import com.example.got.repos.TranslateRepository;
import com.example.got.vm.MainActivityViewModel;
import com.example.got.vm.TranslateResultViewModel;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    SignInAdapter signInAdapter;
    private static final int RC_SIGN_IN = 42;
    private DrawerLayout drawerLayout;
    private TextView navDrawerTitle;
    private TextView navDrawerSubtitle;
    private MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(myToolbar);


        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);

        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        signInAdapter = new SignInAdapter();

        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, myToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);

        navDrawerTitle = (TextView) headerView.findViewById(R.id.nav_drawer_title);
        navDrawerSubtitle = (TextView) headerView.findViewById(R.id.nav_drawer_subtitle);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_clear_history:
                        viewModel.clearHistory();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        makeToast(R.string.toast_clear_translation);
                        return true;
                    case R.id.nav_sign_out:
                        signInAdapter.signOut(getApplicationContext());
                        startActivityForResult(signInAdapter.getSignInIntent(), RC_SIGN_IN);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        makeToast("You are logged out now");
                        return true;
                    default:
                        return false;
                }
            }
        });


        if (!signInAdapter.checkIfSignedIn()) {
            startActivityForResult(signInAdapter.getSignInIntent(), RC_SIGN_IN);
        } else {
            navDrawerTitle.setText(signInAdapter.getName());
            navDrawerSubtitle.setText(signInAdapter.getEmail());
        }
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    public void makeToast(String text){
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    public void makeToast(int resId){
        Toast.makeText(this, resId, Toast.LENGTH_SHORT).show();
    }

}
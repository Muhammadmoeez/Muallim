package com.example.muallim;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

public class AdminDashboard extends AppCompatActivity {

    


    private TabLayout tabLayout;
    private ViewPager viewPager;

    private Toolbar toolbar;
    private AdView mAdView;

    LinearLayout cardViewFirst, cardViewSecond;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);


        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        cardViewFirst = (LinearLayout)findViewById(R.id.firstLayout);
        cardViewSecond = (LinearLayout) findViewById(R.id.secondLayout);


        cardViewFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(AdminDashboard.this, UpdateMyAdminProfile.class);
                startActivity(intent);
            }
        });


        cardViewSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminDashboard.this, AdminTeacherDashboard.class);
                startActivity(intent);
            }
        });

//        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
//        viewPager = (ViewPager) findViewById(R.id.viewPager);

        toolbar = (Toolbar) findViewById(R.id.toolBara);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);


//        ViewPagerAdapter  adapter = new ViewPagerAdapter(getSupportFragmentManager());
//        adapter.AddFragment(new FragmentAdmin(),"Admin");
//        adapter.AddFragment(new FragmentTeacherForAdmin(),"Teacher");
//        adapter.AddFragment(new FragmentStudent(),"Student");
//        adapter.AddFragment(new FragmentTuitionForAdmin(),"Tuition");
//
//
//
//
//        viewPager.setAdapter(adapter);
//        tabLayout.setupWithViewPager(viewPager);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        int id =item.getItemId();

        if (id == R.id.addNewProfile){

            final CharSequence[] items={"Admin", "Teacher", "Tuition", "Cancel"};
            AlertDialog.Builder builder = new AlertDialog.Builder(AdminDashboard.this);
            builder.setTitle("Registration");
            builder.setItems(items, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    if (items[i].equals("Admin"))
                    {
                        Intent intent = new Intent(AdminDashboard.this, AdminRegistrations.class);
                        startActivity(intent);


                    }

                    else if (items[i].equals("Teacher"))
                    {
                        Intent intent = new Intent(AdminDashboard.this, TeacherRegistrations.class);
                        startActivity(intent);

                    }
                    else if (items[i].equals("Tuition"))
                    {
                        Intent intent = new Intent(AdminDashboard.this, TuitionForTeacher.class);
                        startActivity(intent);

                    }

                    else if (items[i].equals("Cancel")){
                        dialogInterface.dismiss();
                    }

                }
            });
            builder.show();


        }

//        else if (id == R.id.settingAdminProfile){
//
//            Intent intent= new Intent(AdminDashboard.this, UpdateMyAdminProfile.class);
//            startActivity(intent);
//
//        }




        else if (id == R.id.logoutAdminProfile){

            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(AdminDashboard.this, LogIn.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }


        return super.onOptionsItemSelected(item);
    }
}
package com.example.virajjoshi.bookmypanditji;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class WelcomeUser extends AppCompatActivity {



    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private CardView cardView1, cardView2, cardView3, cardView4;

    private List<ListItem> listItems;

    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_user);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String useremail = user.getEmail();



        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        cardView1 = (CardView) findViewById(R.id.panditBooking);
        cardView2 = (CardView) findViewById(R.id.video);
        cardView3 = (CardView) findViewById(R.id.shlok);

        cardView4 = (CardView) findViewById(R.id.typesOfPooja);



        viewPager = (ViewPager) findViewById(R.id.viewPager);   // Slide Show Up
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(viewPagerAdapter);

        listItems = new ArrayList<>();     // TO add recycler view info


        for (int i = 0; i < 10; i++) {

            if (i == 0) {

                ListItem listItem = new ListItem(
                        "Heading ", "Hello", cardView1, cardView2, cardView3
                );
                listItems.add(listItem);
            }



            adapter = new MyAdpater(listItems, this);
            recyclerView.setAdapter(adapter);

            BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
            bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()){
                        case R.id.home1:
                          //  Toast.makeText(getApplicationContext(),"Hello",Toast.LENGTH_SHORT).show();
                          //  startActivity(new Intent(WelcomeUser.this,MediaStore.Video.class));  //
                                startActivity(new Intent(WelcomeUser.this,WelcomeUser.class));
                                finish();
                                break;


                        case R.id.home2:
                            startActivity(new Intent(WelcomeUser.this,ContactUs.class));
                            break;

                        /*
                        case R.id.home3:
                            startActivity(new Intent(WelcomeUser.this,AboutUs.class));
                            break;

                            */

                        case R.id.account:
                            startActivity(new Intent(WelcomeUser.this,UserProfile.class));
                            break;
                    }
                    return true;
                }
            });


            Timer timer = new Timer();
            timer.scheduleAtFixedRate(new MyTimerTask(), 2000, 4000);
        }

    }

    public class MyTimerTask extends TimerTask {
        @Override
        public void run() {
            WelcomeUser.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (viewPager.getCurrentItem() == 0) {
                        viewPager.setCurrentItem(1);
                    } else if (viewPager.getCurrentItem() == 1) {
                        viewPager.setCurrentItem(2);
                    } else {
                        viewPager.setCurrentItem(0);
                    }
                }
            });
        }
    }


    public void VideoPooja(View view) {
        startActivity(new Intent(WelcomeUser.this,Video.class));
    }


    public void Pandit(View view){
        startActivity(new Intent(WelcomeUser.this,PanditBooking.class));
    }

    public void ItemsReq(View view){
        startActivity(new Intent(WelcomeUser.this,ItemsReqForPooja.class));
    }

    public void Shylok(View view){
        startActivity(new Intent(WelcomeUser.this,VideoAct.class));
    }


    @Override
    public void onBackPressed() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(WelcomeUser.this);
        builder.setMessage("Do You Want To Close App ");

        // builder.setTitle("Hello");
       // builder.setCancelable(true);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(WelcomeUser.this,UserRegistration.class));
                finish();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });


        AlertDialog alert = builder.create();
        alert.show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.pandit_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){


            // here all list of pandit will be available...
            case R.id.panditProfilemenu:
                startActivity(new Intent(WelcomeUser.this,ShowImagesActivity.class));
                break;

            case R.id.aboutUs:
                startActivity(new Intent(WelcomeUser.this,AboutUs.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
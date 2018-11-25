package com.example.virajjoshi.bookmypanditji;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class VideoAct extends AppCompatActivity {

    private static final String TAG = "Main2Activity";

    private ViewPager mviewPager;

    private SectionsPageAdapter mSectionspageadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_act);

        mviewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mviewPager);

        TabLayout tabyout = (TabLayout) findViewById(R.id.tabs);
        tabyout.setupWithViewPager(mviewPager);
    }

    public void setupViewPager(ViewPager viewPager){
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new Tab1Fragment(),"गणपती");
        adapter.addFragment(new Tab2Fragment()," श्री महालक्ष्म्यष्टकम् ");
        adapter.addFragment(new Tab3Fragment(),"विष्णुसहस्रनाम");
        adapter.addFragment(new Tab4Fragment()," श्रीसूक्तम् ");
        viewPager.setAdapter(adapter);
    }
}

package com.example.samsung.suggestionsysteminfair;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        SectionPagerAdapter adapter = new SectionPagerAdapter(getSupportFragmentManager());
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }


    public class SectionPagerAdapter extends FragmentStatePagerAdapter {

        public SectionPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            Fragment fragment = null;
            HashMap<String, List<Venue>> data = new ExpandableListDataPump().getData();

            String type;

            switch (position) {
                case 0:
                    type = "Exhibitions";
                    break;
                case 1:
                    type = "Museums";
                    break;
                case 2:
                    type = "Fair";
                    break;
                default:
                    type = "Exhibitions";
                    break;
            }

            List<String> list = new ArrayList<>();
            for(Venue venue : data.get(type)){
                list.add(venue.getName());
            }
            fragment = MainActivity2Fragment.newInstance(list, type);

            return fragment;
        }


        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Exhibitions";
                case 1:
                    return "Museums";
                case 2:
                default:
                    return "Fair";
            }
        }
    }
}
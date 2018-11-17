package com.efdalincesu.blog.View;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.efdalincesu.blog.R;
import com.efdalincesu.blog.Utils.AuthTask;
import com.efdalincesu.blog.View.Adapters.CustomFragmentAdapter;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    BottomNavigationView bottomNavigationView;
    CustomFragmentAdapter fragmentAdapter;

    BottomNavigationView.OnNavigationItemSelectedListener listener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            int id = item.getItemId();

            switch (id) {

                case R.id.home:
                    viewPager.setCurrentItem(0);
                    break;
                case R.id.category:
                    viewPager.setCurrentItem(1);
                    break;
                case R.id.favorite:
                    viewPager.setCurrentItem(2);
                    break;
                case R.id.profile:
                    viewPager.setCurrentItem(3);
                    break;


            }


            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();


    }

    public void initialize() {

        AuthTask.init(getApplicationContext());
        viewPager = findViewById(R.id.viewPager);
        bottomNavigationView = findViewById(R.id.bottomNav);
        fragmentAdapter = new CustomFragmentAdapter(getSupportFragmentManager());
        viewPager.setAdapter(fragmentAdapter);
        bottomNavigationView.setOnNavigationItemSelectedListener(listener);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }


}

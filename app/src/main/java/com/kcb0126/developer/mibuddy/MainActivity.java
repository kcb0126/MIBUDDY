package com.kcb0126.developer.mibuddy;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.kcb0126.developer.mibuddy.fragments.CommunityFragment;
import com.kcb0126.developer.mibuddy.fragments.HomeFragment;
import com.kcb0126.developer.mibuddy.fragments.TalkNowFragment;
import com.kcb0126.developer.mibuddy.utils.BottomNavigationViewHelper;
import com.kcb0126.developer.mibuddy.utils.OnFragmentInteractionListener;

public class MainActivity extends AppCompatActivity implements OnFragmentInteractionListener {

    BottomNavigationView navigationView;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    HomeFragment homeFragment = new HomeFragment();
                    transaction.replace(R.id.main_fragment, homeFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                    return true;
                case R.id.navigation_talk_now:
                    TalkNowFragment talkNowFragment = new TalkNowFragment();
                    transaction.replace(R.id.main_fragment, talkNowFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                    return true;
                case R.id.navigation_community:
                    CommunityFragment communityFragment = new CommunityFragment();
                    transaction.replace(R.id.main_fragment, communityFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                    return true;
                case R.id.navigation_icons:
                    Toast.makeText(MainActivity.this, R.string.title_icons, Toast.LENGTH_LONG).show();
                    return true;
                case R.id.navigation_me:
                    Toast.makeText(MainActivity.this, R.string.title_me, Toast.LENGTH_LONG).show();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Make bottom navigation view plain
        navigationView = (BottomNavigationView)findViewById(R.id.navigation);
        new BottomNavigationViewHelper().disableShiftMode(navigationView);
        navigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        gotoHome();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    // Function like SetTab()
    public void gotoHome() {
        navigationView.setSelectedItemId(R.id.navigation_home);
    }
    public void gotoTalkNow() {
        navigationView.setSelectedItemId(R.id.navigation_talk_now);
    }
    public void gotoCommunity() {
        navigationView.setSelectedItemId(R.id.navigation_community);
    }
    public void gotoIcons() {
        navigationView.setSelectedItemId(R.id.navigation_icons);
    }
    public void gotoMe() {
        navigationView.setSelectedItemId(R.id.navigation_me);
    }
}

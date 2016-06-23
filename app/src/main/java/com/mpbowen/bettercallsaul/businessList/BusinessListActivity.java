package com.mpbowen.bettercallsaul.businessList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.mpbowen.bettercallsaul.R;

public class BusinessListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_list);

        // Set up Collapsing Toolbar

        initFragment(BusinessListFragment.newInstance());
    }

    private void initFragment(Fragment businessListFragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.contentFrame, businessListFragment);
        fragmentTransaction.commit();
    }

}

package com.mpbowen.bettercallsaul.businessDetail;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mpbowen.bettercallsaul.R;

import butterknife.Bind;

public class BusinessDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_detail);

        initFragment(BusinessDetailFragment.newInstance());
    }

    private void initFragment(Fragment businessDetailFragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.detailContentFrame, businessDetailFragment);
        fragmentTransaction.commit();
    }

}

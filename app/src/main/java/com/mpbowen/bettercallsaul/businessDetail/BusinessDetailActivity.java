package com.mpbowen.bettercallsaul.businessDetail;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mpbowen.bettercallsaul.R;
import com.mpbowen.bettercallsaul.models.Business;

import java.util.ArrayList;

import butterknife.Bind;

public class BusinessDetailActivity extends AppCompatActivity {

    public static final String EXTRA_NAME = "business_name";
    public static final String EXTRA_IMAGE_URL = "business_image_url";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_detail);

        Intent intent = getIntent();
        final String businessName = intent.getStringExtra(EXTRA_NAME);
        final String businessImageUrl = intent.getStringExtra(EXTRA_IMAGE_URL);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(businessName);

        loadBackdrop(businessImageUrl);

        initFragment(BusinessDetailFragment.newInstance());
    }

    private void loadBackdrop(String businessImageUrl) {
        final ImageView imageView = (ImageView) findViewById(R.id.backdrop);
        if (businessImageUrl == null) {
            Glide.with(this).load(R.drawable.saul_goodman).centerCrop().into(imageView);
        } else {
            Glide.with(this).load(businessImageUrl).centerCrop().into(imageView);
        }
    }

    private void initFragment(Fragment businessDetailFragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.detailContentFrame, businessDetailFragment);
        fragmentTransaction.commit();
    }

}

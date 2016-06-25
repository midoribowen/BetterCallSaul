package com.mpbowen.bettercallsaul.businessDetail;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mpbowen.bettercallsaul.R;



public class BusinessDetailFragment extends Fragment {


    public BusinessDetailFragment() {
        // Required empty public constructor
    }

    public static BusinessDetailFragment newInstance() {
        return new BusinessDetailFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_business_detail, container, false);
    }

}

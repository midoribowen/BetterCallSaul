package com.mpbowen.bettercallsaul.adapters;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mpbowen.bettercallsaul.Constants;
import com.mpbowen.bettercallsaul.R;
import com.mpbowen.bettercallsaul.businessDetail.BusinessDetailActivity;
import com.mpbowen.bettercallsaul.models.Business;

import org.parceler.Parcels;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BusinessViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.businessImageView) ImageView mBusinessImageView;
    @Bind(R.id.businessNameTextView) TextView mBusinessNameTextView;
    @Bind(R.id.businessCategoryTextView) TextView mBusinessCategoryTextView;
    @Bind(R.id.businessAddressTextView) TextView mBusinessAddressTextView;
    @Bind(R.id.businessRatingTextView) TextView mBusinessRatingTextView;

    public final View mView;
    public String mBusinessNameString;
    public String mBusinessImageUrlString;
    public Business mBusiness;
    private WeakReference <Context> mContext;
    private List<Business> mBusinesses = new ArrayList<>();


    public BusinessViewHolder(View itemView, List<Business> businesses) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mView = itemView;
        mContext = new WeakReference<Context>(itemView.getContext());
        mBusinesses = businesses;

    }


    public void bindBusiness(Business business) {
        // Bind with Glide
        if (business.getImageUrl() != null) {
            Glide.with(mContext.get())
                    .load(business.getLargeImageUrl())
                    .override(Constants.MAX_WIDTH, Constants.MAX_HEIGHT)
                    .centerCrop()
                    .into(mBusinessImageView);
        } else {
            Glide.with(mContext.get())
                    .load(R.drawable.saul_goodman)
                    .centerCrop()
                    .into(mBusinessImageView);
        }

        // Bind views for legal_list_item
        mBusinessNameTextView.setText(business.getName());
        mBusinessCategoryTextView.setText(business.getCategoriesAsString());
        mBusinessAddressTextView.setText(TextUtils.join(", ", business.getLocation().getAddress()));
        mBusinessRatingTextView.setText("Rating: " + business.getRating() + "/5");
    }

}
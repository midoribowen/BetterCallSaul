package com.mpbowen.bettercallsaul.adapters;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mpbowen.bettercallsaul.Constants;
import com.mpbowen.bettercallsaul.R;
import com.mpbowen.bettercallsaul.models.Business;
import com.mpbowen.bettercallsaul.models.Location;

import org.parceler.Parcels;

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

    private Context mContext;
    private List<Business> mBusinesses = new ArrayList<>();


    public BusinessViewHolder(View itemView, List<Business> businesses) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mContext = itemView.getContext();
        mBusinesses = businesses;

//        itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int itemPosition = getLayoutPosition();
//                Intent intent = new Intent(mContext, LegalDetailActivity.class);
//                intent.putExtra("position", itemPosition + "");
//                intent.putExtra("businesses", Parcels.wrap(mBusinesses));
//                mContext.startActivity(intent);
//            }
//        });
    }


    public void bindBusiness(Business business) {
        // Bind with Glide
        // TODO: Use Constants.MAX_WIDTH, Constants.MAX_HEIGHT) for detail view?
        // TODO: Use getLargeImageUrl() - fix bug ?
        if (business.getImageUrl() != null) {
            Glide.with(mContext)
                    .load(business.getImageUrl())
                    .centerCrop()
                    .into(mBusinessImageView);
        } else {
            Glide.with(mContext)
                    .load(R.drawable.saul_goodman)
                    .centerCrop()
                    .into(mBusinessImageView);
        }

        // Bind views for legal_list_item
        mBusinessNameTextView.setText(business.getName());
        mBusinessCategoryTextView.setText(business.getCategoriesAsString());
        mBusinessRatingTextView.setText("Rating: " + business.getRating() + "/5");
    }

}

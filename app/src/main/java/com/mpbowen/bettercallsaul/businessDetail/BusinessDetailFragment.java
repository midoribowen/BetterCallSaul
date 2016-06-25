package com.mpbowen.bettercallsaul.businessDetail;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mpbowen.bettercallsaul.R;
import com.mpbowen.bettercallsaul.models.Business;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;


public class BusinessDetailFragment extends Fragment implements View.OnClickListener {

    @Bind(R.id.businessRatingBar) RatingBar mBusinessRatingBar;
    @Bind(R.id.businessRatingTextView) TextView mBusinessRatingTextView;
    @Bind(R.id.businessCategoryTextView) TextView mBusinessCategoryTextView;

    @Bind(R.id.businessWebsiteTextView) TextView mBusinessWebsiteTextView;
    @Bind(R.id.businessPhoneTextView) TextView mBusinessPhoneTextView;
    @Bind(R.id.businessAddressTextView) TextView mBusinessAddressTextView;

    @Bind(R.id.businessSnippetImageView) ImageView mBusinessSnippetImageView;
    @Bind(R.id.businessSnippetTextView) TextView mBusinessSnippetTextView;

    private Business mBusiness;

    public BusinessDetailFragment() {
        // Required empty public constructor
    }

    public static BusinessDetailFragment newInstance(Business business) {
        BusinessDetailFragment businessDetailFragment = new BusinessDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("business", Parcels.wrap(business));
        businessDetailFragment.setArguments(args);
        return businessDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBusiness = Parcels.unwrap(getArguments().getParcelable("business"));
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_business_detail, container, false);

        ButterKnife.bind(this, view);

        mBusinessRatingBar.setIsIndicator(true);
        mBusinessRatingBar.setRating(mBusiness.getRating().floatValue());
        mBusinessRatingTextView.setText(Double.toString(mBusiness.getRating()) + "/5");
        mBusinessCategoryTextView.setText(mBusiness.getCategoriesAsString());

        mBusinessWebsiteTextView.setOnClickListener(this);
        mBusinessPhoneTextView.setText(mBusiness.getDisplayPhone());
        mBusinessPhoneTextView.setOnClickListener(this);
        mBusinessAddressTextView.setText(TextUtils.join(", ", mBusiness.getLocation().getDisplayAddress()));
        mBusinessAddressTextView.setOnClickListener(this);

        if (mBusiness.getSnippetImageUrl() != null) {
            Glide.with(getContext())
                    .load(mBusiness.getSnippetImageUrl())
                    .centerCrop()
                    .into(mBusinessSnippetImageView);
        } else {
            Glide.with(getContext())
                    .load(R.drawable.saul_goodman)
                    .centerCrop()
                    .into(mBusinessSnippetImageView);
        }

        mBusinessSnippetTextView.setText(mBusiness.getSnippetText());

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.businessWebsiteTextView:
                Intent webIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(mBusiness.getMobileUrl()));
                startActivity(webIntent);
                break;
            case R.id.businessPhoneTextView:
                Intent phoneIntent = new Intent(Intent.ACTION_DIAL,
                        Uri.parse("tel:" + mBusiness.getPhone()));
                startActivity(phoneIntent);
                break;
            case R.id.businessAddressTextView:
                Intent mapIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("geo:" + mBusiness.getLocation().getCoordinate().getLatitude()
                                         + "," + mBusiness.getLocation().getCoordinate().getLongitude()
                                         + "?q=" + mBusiness.getLocation().getAddress()));
                startActivity(mapIntent);
                break;
        }
    }

}

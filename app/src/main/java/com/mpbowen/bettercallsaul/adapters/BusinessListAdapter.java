package com.mpbowen.bettercallsaul.adapters;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mpbowen.bettercallsaul.R;
import com.mpbowen.bettercallsaul.businessDetail.BusinessDetailActivity;
import com.mpbowen.bettercallsaul.models.Business;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public class BusinessListAdapter extends RecyclerView.Adapter<BusinessViewHolder> {

    private List<Business> mBusinesses = new ArrayList<>();
    private Context mContext;

    public BusinessListAdapter(Context context, List<Business> businesses) {
        mContext = context;
        mBusinesses = businesses;
    }

    @Override
    public BusinessViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.business_list_item, parent, false);
        BusinessViewHolder viewHolder = new BusinessViewHolder(view, mBusinesses);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final BusinessViewHolder holder, int position) {
        holder.bindBusiness(mBusinesses.get(position));

        holder.mBusinessNameString = mBusinesses.get(position).getName();
        if (mBusinesses.get(position).getImageUrl() !=null ) {
            holder.mBusinessImageUrlString = mBusinesses.get(position).getLargeImageUrl();
        }

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, BusinessDetailActivity.class);
                intent.putExtra(BusinessDetailActivity.EXTRA_NAME, holder.mBusinessNameString);
                intent.putExtra(BusinessDetailActivity.EXTRA_IMAGE_URL, holder.mBusinessImageUrlString);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mBusinesses.size();
    }

}

package com.mpbowen.bettercallsaul.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mpbowen.bettercallsaul.R;
import com.mpbowen.bettercallsaul.models.Business;

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
    public void onBindViewHolder(BusinessViewHolder holder, int position) {
        holder.bindBusiness(mBusinesses.get(position));
    }

    @Override
    public int getItemCount() {
        return mBusinesses.size();
    }

}

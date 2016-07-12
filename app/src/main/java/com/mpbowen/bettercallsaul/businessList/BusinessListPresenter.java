package com.mpbowen.bettercallsaul.businessList;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.mpbowen.bettercallsaul.Constants;
import com.mpbowen.bettercallsaul.adapters.BusinessListAdapter;
import com.mpbowen.bettercallsaul.models.Business;
import com.mpbowen.bettercallsaul.models.SearchResponse;
import com.mpbowen.bettercallsaul.services.YelpAPI;
import com.mpbowen.bettercallsaul.services.YelpAPIFactory;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BusinessListPresenter implements BusinessListInterface.Presenter {

    public static final String TAG = BusinessListPresenter.class.getSimpleName();

    private BusinessListInterface.View mBusinessListView;
    public ArrayList<Business> mBusinesses = new ArrayList<>();

    public BusinessListPresenter(BusinessListInterface.View businessListView) {
        mBusinessListView = businessListView;
    }

    // Put getBusinesses method here
    @Override
    public void getBusinesses(String location) {
        String term = "legal services";
        String limit = "15";
        YelpAPIFactory apiFactory = new YelpAPIFactory(Constants.YELP_CONSUMER_KEY, Constants.YELP_CONSUMER_SECRET, Constants.YELP_TOKEN, Constants.YELP_TOKEN_SECRET);
        YelpAPI yelpAPI = apiFactory.createAPI();
        Call<SearchResponse> call = yelpAPI.search(term, location, limit);
        Callback<SearchResponse> callback = new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                SearchResponse searchResponse = response.body();

                // TODO: Fix this so that searchResponse is checked?
                try {
                    mBusinesses = new ArrayList(searchResponse.getBusinesses());
                    mBusinessListView.displayBusinesses(mBusinesses);
                } catch (NullPointerException exception) {
                    exception.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                // TODO: Do something with the throwable
                Log.d(TAG, t.toString());
            }
        };
        call.enqueue(callback);
    }

}

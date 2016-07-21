package com.mpbowen.bettercallsaul.businessList;


import android.util.Log;

import com.mpbowen.bettercallsaul.Constants;
import com.mpbowen.bettercallsaul.exception.exceptions.YelpAPIError;
import com.mpbowen.bettercallsaul.models.Business;
import com.mpbowen.bettercallsaul.models.SearchResponse;
import com.mpbowen.bettercallsaul.services.YelpAPI;
import com.mpbowen.bettercallsaul.services.YelpAPIFactory;

import java.io.IOException;
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

    @Override
    public void getBusinesses(String location) {
        String term = "legal services";
        String limit = "15";
        final YelpAPIFactory apiFactory = new YelpAPIFactory(Constants.YELP_CONSUMER_KEY, Constants.YELP_CONSUMER_SECRET, Constants.YELP_TOKEN, Constants.YELP_TOKEN_SECRET);
        final YelpAPI yelpAPI = apiFactory.createAPI();
        Call<SearchResponse> call = yelpAPI.search(term, location, limit);
        Callback<SearchResponse> callback = new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {

                if (response.isSuccessful() && response.body() != null) {
                    SearchResponse searchResponse = response.body();
                    mBusinesses = new ArrayList<>(searchResponse.getBusinesses());
                    mBusinessListView.displayBusinesses(mBusinesses);
                }

            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable throwable) {
                if (throwable instanceof YelpAPIError) {
                    YelpAPIError error = (YelpAPIError) throwable;
                    Log.d("API ERROR CODE: ", Integer.toString(error.getCode()));
                    Log.d("API ERROR TEXT: ", error.getText());
                    Log.d("API ERROR ID: ", error.getId());

                    mBusinessListView.displayError(error);
                } else if (throwable instanceof IOException) {
                    IOException ioe = (IOException) throwable;
                    Log.d("NETWORK ERROR!", ioe.toString() + " Uh oh! Check your network connection!");
                    // TODO: Toast for network error
                }
            }
        };

        call.enqueue(callback);

    }

}

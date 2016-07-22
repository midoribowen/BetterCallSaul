package com.mpbowen.bettercallsaul.services;


import com.mpbowen.bettercallsaul.Constants;
import com.mpbowen.bettercallsaul.exception.ErrorHandlingInterceptor;
import com.mpbowen.bettercallsaul.models.SearchResponse;

import java.lang.annotation.Annotation;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import se.akerfeldt.okhttp.signpost.OkHttpOAuthConsumer;
import se.akerfeldt.okhttp.signpost.SigningInterceptor;

public class YelpAPIFactory {
    private String YELP_API_BASE_URL = Constants.YELP_BASE_URL;

    private OkHttpClient httpClient;

    public YelpAPIFactory(String consumerKey, String consumerSecret, String token, String tokenSecret) {
        OkHttpOAuthConsumer consumer = new OkHttpOAuthConsumer(consumerKey, consumerSecret);
        consumer.setTokenWithSecret(token, tokenSecret);

        this.httpClient = new OkHttpClient.Builder()
                .addInterceptor(new SigningInterceptor(consumer))
                .addInterceptor(new ErrorHandlingInterceptor())
                .build();
    }

    public YelpAPI createAPI() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getAPIBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .client(this.httpClient)
                .build();

        return retrofit.create(YelpAPI.class);
    }

    public String getAPIBaseUrl() {
        return YELP_API_BASE_URL;
    }

}

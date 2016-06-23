package com.mpbowen.bettercallsaul.services;


import com.mpbowen.bettercallsaul.models.SearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface YelpAPI {
    @GET("/v2/search")
    Call<SearchResponse> search(
            @Query("term") String term,
            @Query("location") String location,
            @Query("limit") String limit
    );
}

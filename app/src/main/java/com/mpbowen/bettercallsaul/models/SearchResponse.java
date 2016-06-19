
package com.mpbowen.bettercallsaul.models;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class SearchResponse {


    public Integer total;

    public List<Business> businesses = new ArrayList<>();

    public SearchResponse() {
    }

    public SearchResponse(Integer total, ArrayList<Business> businesses) {
        this.total = total;
        this.businesses = businesses;
    }


    public Integer getTotal() {
        return total;
    }


    public void setTotal(Integer total) {
        this.total = total;
    }


    public List<Business> getBusinesses() {
        return businesses;
    }


    public void setBusinesses(ArrayList<Business> businesses) {
        this.businesses = businesses;
    }

}

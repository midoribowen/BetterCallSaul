package com.mpbowen.bettercallsaul.businessList;

import com.mpbowen.bettercallsaul.exception.exceptions.Error;
import com.mpbowen.bettercallsaul.exception.exceptions.YelpAPIError;
import com.mpbowen.bettercallsaul.models.Business;

import java.io.IOException;
import java.util.ArrayList;

public interface BusinessListInterface {

    interface View {
        void displayBusinesses(ArrayList<Business> businesses);
        void displayAPIError(YelpAPIError yelpAPIError, Error error);
        void displayNetworkError(String message);
    }

    interface Presenter {
        void getBusinesses(String query);
    }

}

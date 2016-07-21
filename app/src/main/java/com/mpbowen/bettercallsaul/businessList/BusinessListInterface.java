package com.mpbowen.bettercallsaul.businessList;

import com.mpbowen.bettercallsaul.exception.exceptions.YelpAPIError;
import com.mpbowen.bettercallsaul.models.Business;

import java.util.ArrayList;

public interface BusinessListInterface {

    interface View {
        void displayBusinesses(ArrayList<Business> businesses);
        void displayError(YelpAPIError error);
    }

    interface Presenter {
        void getBusinesses(String query);
    }

}

package com.mpbowen.bettercallsaul.businessList;

import com.mpbowen.bettercallsaul.models.Business;

import java.util.ArrayList;

public interface BusinessListInterface {

    interface View {
        // display businesses?
        void displayBusinesses(ArrayList<Business> businesses);
    }

    interface Presenter {
        // search businesses?
        void getBusinesses(String query);
    }

}

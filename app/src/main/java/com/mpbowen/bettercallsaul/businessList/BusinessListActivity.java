package com.mpbowen.bettercallsaul.businessList;

import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.mpbowen.bettercallsaul.Constants;
import com.mpbowen.bettercallsaul.R;
import com.mpbowen.bettercallsaul.adapters.BusinessListAdapter;
import com.mpbowen.bettercallsaul.models.Business;
import com.mpbowen.bettercallsaul.models.SearchResponse;
import com.mpbowen.bettercallsaul.services.YelpAPI;
import com.mpbowen.bettercallsaul.services.YelpAPIFactory;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BusinessListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_list);
    }

}

package com.mpbowen.bettercallsaul.businessList;


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mpbowen.bettercallsaul.Constants;
import com.mpbowen.bettercallsaul.R;
import com.mpbowen.bettercallsaul.adapters.BusinessListAdapter;
import com.mpbowen.bettercallsaul.exception.exceptions.YelpAPIError;
import com.mpbowen.bettercallsaul.models.Business;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


public class BusinessListFragment extends Fragment implements BusinessListInterface.View {

    public static final String TAG = BusinessListFragment.class.getSimpleName();

    private SharedPreferences.Editor mEditor;

    private BusinessListPresenter mBusinessListPresenter;
    private ProgressDialog mSearchProgressDialog;

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    public ArrayList<Business> mBusinesses = new ArrayList<>();

    public BusinessListFragment() {
        // Required empty public constructor
    }

    public static BusinessListFragment newInstance() {
        return new BusinessListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_business_list, container, false);
        ButterKnife.bind(this, view);

        mSearchProgressDialog = new ProgressDialog(getContext());
        mSearchProgressDialog.setTitle("Loading...");
        mSearchProgressDialog.setMessage("Searching for businesses...");
        mSearchProgressDialog.setCancelable(false);
        mSearchProgressDialog.show();

        mBusinessListPresenter = new BusinessListPresenter(this);

        final SharedPreferences mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        final String mRecentAddress = mSharedPreferences.getString(Constants.PREFERENCES_LOCATION_KEY, null);
        mEditor = mSharedPreferences.edit();
        mEditor.apply();
        if (mRecentAddress != null) {
            mBusinessListPresenter.getBusinesses(mRecentAddress);
        }

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_search, menu);

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                addToSharedPreferences(query);
                mBusinessListPresenter.getBusinesses(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void addToSharedPreferences(String location) {
        mEditor.putString(Constants.PREFERENCES_LOCATION_KEY, location).apply();
    }

    @Override
    public void displayBusinesses(final ArrayList<Business> businesses) {
        mBusinesses = businesses;
        final BusinessListAdapter mAdapter = new BusinessListAdapter(getContext(), mBusinesses);
        mRecyclerView.setAdapter(mAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mSearchProgressDialog.dismiss();
    }

    @Override
    public void displayError(YelpAPIError error) {
        mSearchProgressDialog.dismiss();

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
        alertDialogBuilder.setTitle(error.getCode() + " Error");
        alertDialogBuilder.setMessage(error.getText());
        alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast apologyToast = Toast.makeText(getActivity(), "Sorry about that!", Toast.LENGTH_LONG);
                apologyToast.show();
            }
        });
        alertDialogBuilder.setNegativeButton("Report Error", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //TODO: Send error data to Firebase

                Toast gratitudeToast = Toast.makeText(getActivity(), "Thank you!", Toast.LENGTH_LONG);
                gratitudeToast.show();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

}

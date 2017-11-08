package com.ingic.pnl.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ingic.pnl.R;
import com.ingic.pnl.entities.CompanyDetailEnt;
import com.ingic.pnl.entities.CompanyModel;
import com.ingic.pnl.entities.FavoritesEnt;
import com.ingic.pnl.fragments.abstracts.BaseFragment;
import com.ingic.pnl.global.WebServiceConstants;
import com.ingic.pnl.helpers.UIHelper;
import com.ingic.pnl.ui.adapters.ArrayListAdapter;
import com.ingic.pnl.ui.viewbinders.abstracts.FavoritesItemBinder;
import com.ingic.pnl.ui.views.AnyTextView;
import com.ingic.pnl.ui.views.TitleBar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by saeedhyder on 10/19/2017.
 */
public class FavouriteFragment extends BaseFragment {

    @BindView(R.id.txt_no_data)
    AnyTextView txtNoData;
    @BindView(R.id.lv_notifications)
    ListView lvNotifications;
    Unbinder unbinder;

    private ArrayList<FavoritesEnt> favoritesEnt;

    private ArrayListAdapter<FavoritesEnt> adapter;
    private ArrayList<FavoritesEnt> userCollection;


    public static FavouriteFragment newInstance() {
        Bundle args = new Bundle();

        FavouriteFragment fragment = new FavouriteFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
        adapter = new ArrayListAdapter<FavoritesEnt>(getDockActivity(), new FavoritesItemBinder(getDockActivity(), prefHelper));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favourite, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        serviceHelper.enqueueCall(webService.getFavouriteList(prefHelper.getUserID()), WebServiceConstants.FAVOURITELIST);

        //setFavouriteData();
        listner();
    }

    @Override
    public void ResponseSuccess(Object result, String Tag, String message) {
        switch (Tag) {
            case WebServiceConstants.FAVOURITELIST:
                favoritesEnt = (ArrayList<FavoritesEnt>) result;
                userCollection=favoritesEnt;
                bindData(favoritesEnt);

                break;


        }
    }
    @Override
    public void ResponseFailure(String tag) {
        switch (tag){
            case WebServiceConstants.FAVOURITELIST:
                txtNoData.setVisibility(View.VISIBLE);
                lvNotifications.setVisibility(View.GONE);
                break;
        }
    }
    private void listner() {

        lvNotifications.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                getDockActivity().addDockableFragment(CompanyDetailFragment.newInstance(userCollection.get(position).getId(),userCollection.get(position).getName()),"CompanyDetailFragment");
            }
        });
    }


    private void bindData(ArrayList<FavoritesEnt> userCollection) {



        adapter.clearList();
        lvNotifications.setAdapter(adapter);
        adapter.addAll(userCollection);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setTitleBar(TitleBar titleBar) {
        super.setTitleBar(titleBar);
        titleBar.hideButtons();
        titleBar.setSubHeading(getString(R.string.favorites));
        titleBar.showBackButton();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}

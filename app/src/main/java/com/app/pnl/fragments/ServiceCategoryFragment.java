package com.app.pnl.fragments;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.app.pnl.R;
import com.app.pnl.entities.PopularEnt;
import com.app.pnl.entities.servicesGridViewEnt;
import com.app.pnl.fragments.abstracts.BaseFragment;
import com.app.pnl.interfaces.RecyclerViewItemListener;
import com.app.pnl.retrofit.GsonFactory;
import com.app.pnl.ui.viewbinders.viewbinders.PopularBinder;
import com.app.pnl.ui.views.AnyTextView;
import com.app.pnl.ui.views.CustomRecyclerView;
import com.app.pnl.ui.views.TitleBar;
import com.google.gson.Gson;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created on 10/19/2017.
 */
public class ServiceCategoryFragment extends BaseFragment implements RecyclerViewItemListener {
    @BindView(R.id.txt_no_data)
    AnyTextView txtNoData;
    @BindView(R.id.lv_companies)
    CustomRecyclerView lvCompanies;
    Unbinder unbinder;
    private ArrayList<PopularEnt> userCollections;
    private static String ServiceName="servicename";
    private servicesGridViewEnt entity;
    private String titleName="Hospital";

    public static ServiceCategoryFragment newInstance() {
        Bundle args = new Bundle();

        ServiceCategoryFragment fragment = new ServiceCategoryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static ServiceCategoryFragment newInstance(servicesGridViewEnt servicesGridViewEnt) {
        Bundle args = new Bundle();
        args.putString(ServiceName, new Gson().toJson(servicesGridViewEnt));
        ServiceCategoryFragment fragment = new ServiceCategoryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            ServiceName=getArguments().getString(ServiceName);
        }
        if(!ServiceName.equals("")){
            entity=new Gson().fromJson(ServiceName, servicesGridViewEnt.class);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_service_category, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        BindData();
        titleName=entity.getText();


    }

    private void BindData() {
        userCollections = new ArrayList<>();
        userCollections.add(new PopularEnt(R.drawable.company, "AA Company", getString(R.string.lorem_ipsum), "22 street,France", "+422 123456789"));
        userCollections.add(new PopularEnt(R.drawable.company, "AA Company", getString(R.string.lorem_ipsum), "22 street,France", "+422 123456789"));
        userCollections.add(new PopularEnt(R.drawable.company, "AA Company", getString(R.string.lorem_ipsum), "22 street,France", "+422 123456789"));
        userCollections.add(new PopularEnt(R.drawable.company, "AA Company", getString(R.string.lorem_ipsum), "22 street,France", "+422 123456789"));
        lvCompanies.BindRecyclerView(new PopularBinder(this), userCollections,
                new LinearLayoutManager(getDockActivity(), LinearLayoutManager.VERTICAL, false), new DefaultItemAnimator());

        if (userCollections.size() <= 0) {
            txtNoData.setVisibility(View.VISIBLE);
            lvCompanies.setVisibility(View.GONE);
        } else {
            txtNoData.setVisibility(View.GONE);
            lvCompanies.setVisibility(View.VISIBLE);

        }
    }

    @Override
    public void setTitleBar(TitleBar titleBar) {
        super.setTitleBar(titleBar);
        titleBar.hideButtons();
        titleBar.setSubHeading(titleName);
        titleBar.showBackButton();
    }

    @Override
    public void onRecyclerItemClicked(Object Ent, int position) {
        getDockActivity().replaceDockableFragment(CompanyDetailFragment.newInstance(), "CompanyDetailFragment");
    }
}
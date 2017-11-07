package com.ingic.pnl.fragments;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ingic.pnl.R;
import com.ingic.pnl.entities.PopularEnt;
import com.ingic.pnl.entities.ServiceEnt;
import com.ingic.pnl.entities.servicesGridViewEnt;
import com.ingic.pnl.fragments.abstracts.BaseFragment;
import com.ingic.pnl.global.WebServiceConstants;
import com.ingic.pnl.interfaces.RecyclerViewItemListener;
import com.ingic.pnl.ui.viewbinders.viewbinders.PopularBinder;
import com.ingic.pnl.ui.views.AnyTextView;
import com.ingic.pnl.ui.views.CustomRecyclerView;
import com.ingic.pnl.ui.views.TitleBar;
import com.google.gson.Gson;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created on 10/19/2017.
 */
public class ServiceCategoryFragment extends BaseFragment implements RecyclerViewItemListener {
    private static String ServiceName = "servicename";
    @BindView(R.id.txt_no_data)
    AnyTextView txtNoData;
    @BindView(R.id.lv_companies)
    CustomRecyclerView lvCompanies;
    Unbinder unbinder;
    private ArrayList<PopularEnt> userCollections;
    private ArrayList<PopularEnt> popularEnts;
    private ServiceEnt entity;
    private String titleName = "Hospital";
    private String id="";

    public static ServiceCategoryFragment newInstance() {
        Bundle args = new Bundle();

        ServiceCategoryFragment fragment = new ServiceCategoryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static ServiceCategoryFragment newInstance(ServiceEnt servicesGridViewEnt) {
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
            ServiceName = getArguments().getString(ServiceName);
        }
        if (ServiceName != null && !ServiceName.equals("")) {
            entity = new Gson().fromJson(ServiceName, ServiceEnt.class);
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_service_category, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (entity != null){
            titleName = entity.getName();
            id= String.valueOf(entity.getId());

        }

        serviceHelper.enqueueCall(webService.getCategoryDetail(id), WebServiceConstants.CATEGORYDETAIL);


    }

    @Override
    public void ResponseSuccess(Object result, String Tag, String message) {
        switch (Tag) {
            case WebServiceConstants.CATEGORYDETAIL:
                popularEnts = (ArrayList<PopularEnt>) result;
                BindData(popularEnts);

                break;


        }
    }

    @Override
    public void ResponseFailure(String tag) {
        switch (tag) {
            case WebServiceConstants.CATEGORYDETAIL:
                txtNoData.setVisibility(View.VISIBLE);
                lvCompanies.setVisibility(View.GONE);
                break;


        }
    }

    private void BindData(ArrayList<PopularEnt> popularEnts) {
        userCollections = new ArrayList<>();
       /* userCollections.add(new PopularEnt(R.drawable.company, "AA Company", getString(R.string.lorem_ipsum), "22 street,France", "+422 123456789"));
        userCollections.add(new PopularEnt(R.drawable.company, "AA Company", getString(R.string.lorem_ipsum), "22 street,France", "+422 123456789"));
        userCollections.add(new PopularEnt(R.drawable.company, "AA Company", getString(R.string.lorem_ipsum), "22 street,France", "+422 123456789"));
        userCollections.add(new PopularEnt(R.drawable.company, "AA Company", getString(R.string.lorem_ipsum), "22 street,France", "+422 123456789"));*/
        lvCompanies.BindRecyclerView(new PopularBinder(this), popularEnts,
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
    public void onRecyclerItemClicked(Object Ent, int position) {
        getDockActivity().replaceDockableFragment(CompanyDetailFragment.newInstance(), "CompanyDetailFragment");
    }
}
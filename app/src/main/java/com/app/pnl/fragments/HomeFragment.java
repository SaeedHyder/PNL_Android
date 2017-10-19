package com.app.pnl.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.app.pnl.R;
import com.app.pnl.fragments.abstracts.BaseFragment;
import com.app.pnl.ui.views.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class HomeFragment extends BaseFragment {


    @BindView(R.id.btn_popular)
    LinearLayout btnPopular;
    @BindView(R.id.btn_services)
    LinearLayout btnServices;
    @BindView(R.id.btn_companies)
    LinearLayout btnCompanies;
    @BindView(R.id.btn_favourites)
    LinearLayout btnFavourites;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setTitleBar(TitleBar titleBar) {
        super.setTitleBar(titleBar);
        titleBar.hideButtons();
        titleBar.showMenuButton();
        titleBar.setSubHeading(getString(R.string.home));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }


    @OnClick({R.id.btn_popular, R.id.btn_services, R.id.btn_companies, R.id.btn_favourites})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_popular:
                getDockActivity().addDockableFragment(SortingByFragment.newInstance(),SortingByFragment.class.getName());
                break;
            case R.id.btn_services:
                getDockActivity().addDockableFragment(RateAndWriteFragment.newInstance(),RateAndWriteFragment.class.getName());
                break;
            case R.id.btn_companies:
                getDockActivity().addDockableFragment(FavouriteFragment.newInstance(),FavouriteFragment.class.getName());
                break;
            case R.id.btn_favourites:
                getDockActivity().addDockableFragment(CompanyDetailFragment.newInstance(),CompanyDetailFragment.class.getName());
                break;
        }
    }
}


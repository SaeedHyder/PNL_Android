package com.app.pnl.fragments;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.app.pnl.R;
import com.app.pnl.entities.ServiceEnt;
import com.app.pnl.fragments.abstracts.BaseFragment;
import com.app.pnl.interfaces.RecyclerViewItemListener;
import com.app.pnl.ui.viewbinders.viewbinders.ServicesBinder;
import com.app.pnl.ui.views.CustomRecyclerView;
import com.app.pnl.ui.views.TitleBar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created on 10/19/2017.
 */
public class ServicesFragment extends BaseFragment implements RecyclerViewItemListener {
    @BindView(R.id.rcy_services)
    CustomRecyclerView rcyServices;
    @BindView(R.id.btn_all_services)
    Button btnAllServices;
    private ArrayList<ServiceEnt> userCollections;

    public static ServicesFragment newInstance() {
        Bundle args = new Bundle();

        ServicesFragment fragment = new ServicesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_services, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindData();

    }

    private void bindData() {

        userCollections.add(new ServiceEnt(R.drawable.hospital, getString(R.string.hospital)));
        userCollections.add(new ServiceEnt(R.drawable.restaurant, getString(R.string.restaurant)));
        userCollections.add(new ServiceEnt(R.drawable.bakery, getString(R.string.bakery)));
        userCollections.add(new ServiceEnt(R.drawable.spa, getString(R.string.spa)));
        userCollections.add(new ServiceEnt(R.drawable.cafe, getString(R.string.cafe)));
        userCollections.add(new ServiceEnt(R.drawable.casino, getString(R.string.casino)));
        userCollections.add(new ServiceEnt(R.drawable.construaction, getString(R.string.construction)));
        userCollections.add(new ServiceEnt(R.drawable.store, getString(R.string.store)));
        userCollections.add(new ServiceEnt(R.drawable.law, getString(R.string.law)));
        userCollections.add(new ServiceEnt(R.drawable.traveling, getString(R.string.traveling)));
        userCollections.add(new ServiceEnt(R.drawable.finance, getString(R.string.finance)));
        userCollections.add(new ServiceEnt(R.drawable.pet, getString(R.string.pets)));
        rcyServices.BindRecyclerView(new ServicesBinder(this), userCollections,
                new GridLayoutManager(getDockActivity(), 3), new DefaultItemAnimator());

    }

    @Override
    public void setTitleBar(TitleBar titleBar) {
        super.setTitleBar(titleBar);
        titleBar.hideButtons();
        titleBar.setSubHeading(getString(R.string.services));
        titleBar.showBackButton();
        titleBar.showSearchBar(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @OnClick(R.id.btn_all_services)
    public void onViewClicked() {
    }

    @Override
    public void onRecyclerItemClicked(Object Ent, int position) {

    }
}

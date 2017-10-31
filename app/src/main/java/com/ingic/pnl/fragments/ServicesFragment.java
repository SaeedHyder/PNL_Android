package com.ingic.pnl.fragments;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;

import com.ingic.pnl.R;
import com.ingic.pnl.entities.ServiceEnt;
import com.ingic.pnl.entities.servicesGridViewEnt;
import com.ingic.pnl.fragments.abstracts.BaseFragment;
import com.ingic.pnl.helpers.UIHelper;
import com.ingic.pnl.helpers.Utils;
import com.ingic.pnl.interfaces.RecyclerViewItemListener;
import com.ingic.pnl.ui.adapters.ArrayListAdapter;
import com.ingic.pnl.ui.viewbinders.ServicesGridViewItemBinder;
import com.ingic.pnl.ui.views.AnyTextView;
import com.ingic.pnl.ui.views.ExpandableGridView;
import com.ingic.pnl.ui.views.TitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created on 10/19/2017.
 */
public class ServicesFragment extends BaseFragment implements RecyclerViewItemListener {

    @BindView(R.id.btn_all_services)
    Button btnAllServices;
    @BindView(R.id.rcy_services)
    ExpandableGridView rcyServices;
    @BindView(R.id.txt_no_data)
    AnyTextView txtNoData;
    private ArrayList<ServiceEnt> userCollections;

    private ArrayListAdapter<servicesGridViewEnt> adapter;
    private List<servicesGridViewEnt> dataCollection = new ArrayList<>();

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
        adapter = new ArrayListAdapter<servicesGridViewEnt>(getDockActivity(), new ServicesGridViewItemBinder(getDockActivity(), prefHelper));
    }

    @Override
    public void setTitleBar(final TitleBar titleBar) {
        super.setTitleBar(titleBar);
        titleBar.hideButtons();
        titleBar.setSubHeading(getString(R.string.services));
        titleBar.showBackButton();
        titleBar.getEditTextViewSearch(R.id.edt_search).setText("");
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
        }, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIHelper.showShortToastInCenter(getDockActivity(), getString(R.string.beta));
                Utils.HideKeyBoard(getDockActivity());
            }
        },getString(R.string.search_category));
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

        setHomeData();
        listner();

    }

    private void listner() {

        rcyServices.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                getDockActivity().addDockableFragment(ServiceCategoryFragment.newInstance(dataCollection.get(position)),"ServiceCategoryFragment");
            }
        });
    }

    private void setHomeData() {

        dataCollection = new ArrayList<>();
        dataCollection.add(new servicesGridViewEnt("drawable://"+R.drawable.hospital, getString(R.string.hospital),R.drawable.hospital));
        dataCollection.add(new servicesGridViewEnt("drawable://"+R.drawable.restaurant, getString(R.string.restaurant),R.drawable.restaurant));
        dataCollection.add(new servicesGridViewEnt("drawable://"+R.drawable.bakery, getString(R.string.bakery),R.drawable.bakery));
        dataCollection.add(new servicesGridViewEnt("drawable://"+R.drawable.spa, getString(R.string.spa),R.drawable.spa));
        dataCollection.add(new servicesGridViewEnt("drawable://"+R.drawable.cafe, getString(R.string.cafe),R.drawable.cafe));
        dataCollection.add(new servicesGridViewEnt("drawable://"+R.drawable.casino, getString(R.string.casino),R.drawable.casino));
        dataCollection.add(new servicesGridViewEnt("drawable://"+R.drawable.construaction, getString(R.string.construction),R.drawable.construaction));
        dataCollection.add(new servicesGridViewEnt("drawable://"+R.drawable.store, getString(R.string.store),R.drawable.store));
        dataCollection.add(new servicesGridViewEnt("drawable://"+R.drawable.law, getString(R.string.law),R.drawable.law));
        dataCollection.add(new servicesGridViewEnt("drawable://"+R.drawable.traveling, getString(R.string.traveling),R.drawable.traveling));
        dataCollection.add(new servicesGridViewEnt("drawable://"+R.drawable.finance, getString(R.string.finance),R.drawable.finance));
        dataCollection.add(new servicesGridViewEnt("drawable://"+R.drawable.pet, getString(R.string.pets),R.drawable.pet));


        if (dataCollection.size() <= 0) {
            txtNoData.setVisibility(View.VISIBLE);
            rcyServices.setVisibility(View.GONE);
        } else {
            txtNoData.setVisibility(View.GONE);
            rcyServices.setVisibility(View.VISIBLE);

        }

        adapter.clearList();
        rcyServices.setAdapter(adapter);
        adapter.addAll(dataCollection);
        adapter.notifyDataSetChanged();

    }

    private void bindData() {
        userCollections = new ArrayList<>();
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
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getDockActivity(), 3);

/*        rcyServices.BindRecyclerView(new ServicesBinder(this), userCollections, layoutManager
                , new DefaultItemAnimator());*/

    }

    @OnClick(R.id.btn_all_services)
    public void onViewClicked() {
        getDockActivity().replaceDockableFragment(ViewAllServicesFragment.newInstance(), ViewAllServicesFragment.class.getSimpleName());
    }

    @Override
    public void onRecyclerItemClicked(Object Ent, int position) {

    }



}
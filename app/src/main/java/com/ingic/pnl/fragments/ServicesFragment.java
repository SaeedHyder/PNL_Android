package com.ingic.pnl.fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;

import com.ingic.pnl.R;
import com.ingic.pnl.entities.ServiceEnt;
import com.ingic.pnl.fragments.abstracts.BaseFragment;
import com.ingic.pnl.global.WebServiceConstants;
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

    private ArrayList<ServiceEnt> servicesEnt = new ArrayList<>();

    private ArrayListAdapter<ServiceEnt> adapter;
    private List<ServiceEnt> dataCollection = new ArrayList<>();

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
        adapter = new ArrayListAdapter<ServiceEnt>(getDockActivity(), new ServicesGridViewItemBinder(getDockActivity(), prefHelper));
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

        serviceHelper.enqueueCall(webService.getSerivesList(), WebServiceConstants.SERVICESLIST);


        listner();

    }

    @Override
    public void ResponseSuccess(Object result, String Tag, String message) {
        switch (Tag) {
            case WebServiceConstants.SERVICESLIST:
                servicesEnt = (ArrayList<ServiceEnt>) result;
                setHomeData(servicesEnt);
                break;

        }
    }

    @Override
    public void ResponseFailure(String tag) {
        switch (tag){
            case WebServiceConstants.SERVICESLIST:
                txtNoData.setVisibility(View.VISIBLE);
                rcyServices.setVisibility(View.GONE);
                break;
        }
    }

    private void listner() {

        rcyServices.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                getDockActivity().addDockableFragment(ServiceCategoryFragment.newInstance(dataCollection.get(position)), "ServiceCategoryFragment");
            }
        });
    }

    private void setHomeData(ArrayList<ServiceEnt> servicesEnt) {

        dataCollection = new ArrayList<>();
         /* dataCollection.add(new servicesGridViewEnt("drawable://"+R.drawable.hospital, getString(R.string.hospital),R.drawable.hospital));
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
        dataCollection.add(new servicesGridViewEnt("drawable://"+R.drawable.pet, getString(R.string.pets),R.drawable.pet));*/

        dataCollection = servicesEnt;

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


    @OnClick(R.id.btn_all_services)
    public void onViewClicked() {
        getDockActivity().replaceDockableFragment(ViewAllServicesFragment.newInstance(), ViewAllServicesFragment.class.getSimpleName());
    }

    @Override
    public void onRecyclerItemClicked(Object Ent, int position) {

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
        }, getString(R.string.search_category));
    }


}

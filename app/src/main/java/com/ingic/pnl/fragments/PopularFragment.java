package com.ingic.pnl.fragments;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ingic.pnl.R;
import com.ingic.pnl.entities.PopularEnt;
import com.ingic.pnl.fragments.abstracts.BaseFragment;
import com.ingic.pnl.global.WebServiceConstants;
import com.ingic.pnl.interfaces.RecyclerViewItemListener;
import com.ingic.pnl.ui.viewbinders.viewbinders.PopularBinder;
import com.ingic.pnl.ui.views.AnyTextView;
import com.ingic.pnl.ui.views.CustomRecyclerView;
import com.ingic.pnl.ui.views.TitleBar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created on 10/19/2017.
 */
public class PopularFragment extends BaseFragment implements RecyclerViewItemListener {
    @BindView(R.id.txt_no_data)
    AnyTextView txtNoData;
    @BindView(R.id.lv_companies)
    CustomRecyclerView lvCompanies;
    private ArrayList<PopularEnt> userCollections;
    private ArrayList<PopularEnt> popularEnts;
    private PopularEnt popularEnt = new PopularEnt();

    public static PopularFragment newInstance() {
        Bundle args = new Bundle();

        PopularFragment fragment = new PopularFragment();
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
    public void ResponseSuccess(Object result, String Tag, String message) {
        switch (Tag) {
            case WebServiceConstants.MOSTPOPULARLIST:
                popularEnts = (ArrayList<PopularEnt>) result;
                BindData(popularEnts);

                break;


        }
    }

    @Override
    public void ResponseFailure(String tag) {
        switch (tag) {
            case WebServiceConstants.MOSTPOPULARLIST:
                txtNoData.setVisibility(View.VISIBLE);
                lvCompanies.setVisibility(View.GONE);

                break;


        }
    }

    @Override
    public void setTitleBar(TitleBar titleBar) {
        super.setTitleBar(titleBar);
        titleBar.hideButtons();
        titleBar.setSubHeading(getString(R.string.most_popular));
        titleBar.showBackButton();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_popular, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        serviceHelper.enqueueCall(webService.getMostPopularList(prefHelper.getUserID()), WebServiceConstants.MOSTPOPULARLIST);


    }

    private void BindData(ArrayList<PopularEnt> popularEnts) {
       /*  userCollections = new ArrayList<>();
       userCollections.add(new PopularEnt(R.drawable.company, "AA Company", getString(R.string.lorem_ipsum), "22 street,France", "+422 123456789"));
        userCollections.add(new PopularEnt(R.drawable.company, "AA Company", getString(R.string.lorem_ipsum), "22 street,France", "+422 123456789"));
        userCollections.add(new PopularEnt(R.drawable.company, "AA Company", getString(R.string.lorem_ipsum), "22 street,France", "+422 123456789"));
        userCollections.add(new PopularEnt(R.drawable.company, "AA Company", getString(R.string.lorem_ipsum), "22 street,France", "+422 123456789"));
      */


        lvCompanies.BindRecyclerView(new PopularBinder(this), popularEnts,
                new LinearLayoutManager(getDockActivity(), LinearLayoutManager.VERTICAL, false), new DefaultItemAnimator());

    }

    @Override
    public void onRecyclerItemClicked(Object Ent, int position) {
        popularEnt = (PopularEnt) Ent;
        getDockActivity().replaceDockableFragment(CompanyDetailFragment.newInstance(popularEnt.getId(), popularEnt.getName()), "CompanyDetailFragment");
    }


}
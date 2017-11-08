package com.ingic.pnl.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ToggleButton;

import com.ingic.pnl.R;
import com.ingic.pnl.entities.CompanyDetailEnt;
import com.ingic.pnl.entities.ReviewsEnt;
import com.ingic.pnl.fragments.abstracts.BaseFragment;
import com.ingic.pnl.global.WebServiceConstants;
import com.ingic.pnl.helpers.UIHelper;
import com.ingic.pnl.ui.adapters.ArrayListAdapter;
import com.ingic.pnl.ui.viewbinders.abstracts.ReviewsItemBinder;
import com.ingic.pnl.ui.views.AnyTextView;
import com.ingic.pnl.ui.views.TitleBar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by saeedhyder on 10/19/2017.
 */
public class ReviewsFragment extends BaseFragment {
    @BindView(R.id.txt_no_data)
    AnyTextView txtNoData;
    @BindView(R.id.lv_reviews)
    ListView lvReviews;
    Unbinder unbinder;

    private static String COMPANYIDKEY = "companyidkey";
    private int companyId;
    private ArrayList<ReviewsEnt> reviewsEnts;

    private ArrayListAdapter<ReviewsEnt> adapter;
    private ArrayList<ReviewsEnt> userCollection;

    public static ReviewsFragment newInstance() {
        Bundle args = new Bundle();

        ReviewsFragment fragment = new ReviewsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static ReviewsFragment newInstance(int companyId) {
        Bundle args = new Bundle();
        args.putInt(COMPANYIDKEY,companyId);
        ReviewsFragment fragment = new ReviewsFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            companyId = getArguments().getInt(COMPANYIDKEY);
        }
        adapter = new ArrayListAdapter<ReviewsEnt>(getDockActivity(), new ReviewsItemBinder(getDockActivity(), prefHelper));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reviews, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        serviceHelper.enqueueCall(webService.getReviewList(companyId), WebServiceConstants.REVIEWSLIST);

    }

    @Override
    public void ResponseSuccess(Object result, String Tag, String message) {
        switch (Tag) {
            case WebServiceConstants.REVIEWSLIST:
                reviewsEnts=(ArrayList<ReviewsEnt>)result;
                bindData(reviewsEnts);
                break;
        }
    }

    @Override
    public void ResponseFailure(String tag) {
        switch (tag) {
            case WebServiceConstants.REVIEWSLIST:
                txtNoData.setVisibility(View.VISIBLE);
                lvReviews.setVisibility(View.GONE);
                break;


        }
    }


    private void bindData(ArrayList<ReviewsEnt> userCollection) {


        adapter.clearList();
        lvReviews.setAdapter(adapter);
        adapter.addAll(userCollection);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setTitleBar(TitleBar titleBar) {
        super.setTitleBar(titleBar);
        titleBar.hideButtons();
        titleBar.setSubHeading(getString(R.string.reviews_));
        titleBar.showBackButton();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}

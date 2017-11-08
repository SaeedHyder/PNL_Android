package com.ingic.pnl.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ingic.pnl.R;
import com.ingic.pnl.entities.ReviewsEnt;
import com.ingic.pnl.fragments.abstracts.BaseFragment;
import com.ingic.pnl.global.WebServiceConstants;
import com.ingic.pnl.ui.adapters.ArrayListAdapter;
import com.ingic.pnl.ui.viewbinders.abstracts.ReviewsItemBinder;
import com.ingic.pnl.ui.views.AnyTextView;
import com.ingic.pnl.ui.views.TitleBar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created on 10/19/2017.
 */
public class ReviewHistory extends BaseFragment {
    @BindView(R.id.txt_no_data)
    AnyTextView txtNoData;
    @BindView(R.id.lv_review_history)
    ListView lvReviewHistory;
    Unbinder unbinder;
    private ArrayList<ReviewsEnt> reviewsEnts;

    public static ReviewHistory newInstance() {
        Bundle args = new Bundle();

        ReviewHistory fragment = new ReviewHistory();
        fragment.setArguments(args);
        return fragment;
    }

    private ArrayListAdapter<ReviewsEnt> adapter;
    private ArrayList<ReviewsEnt> userCollection;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new ArrayListAdapter<ReviewsEnt>(getDockActivity(),new ReviewsItemBinder(getDockActivity(),prefHelper));
        if (getArguments() != null) {
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_review_history, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        serviceHelper.enqueueCall(webService.getAllReviewsOnProfile(prefHelper.getUserID()), WebServiceConstants.REVIEWSLIST);
       // setReviewsData();

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
                lvReviewHistory.setVisibility(View.GONE);
                break;


        }
    }

    @Override
    public void setTitleBar(TitleBar titleBar) {
        super.setTitleBar(titleBar);
        titleBar.hideButtons();
        titleBar.setSubHeading(getString(R.string.review_history));
        titleBar.showBackButton();
    }



    private void bindData(ArrayList<ReviewsEnt> userCollection) {


        adapter.clearList();
        lvReviewHistory.setAdapter(adapter);
        adapter.addAll(userCollection);
        adapter.notifyDataSetChanged();
    }


}
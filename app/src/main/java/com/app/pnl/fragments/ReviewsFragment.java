package com.app.pnl.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.app.pnl.R;
import com.app.pnl.entities.FavoritesEnt;
import com.app.pnl.entities.ReviewsEnt;
import com.app.pnl.fragments.abstracts.BaseFragment;
import com.app.pnl.ui.adapters.ArrayListAdapter;
import com.app.pnl.ui.viewbinders.abstracts.ReviewsItemBinder;
import com.app.pnl.ui.views.AnyTextView;
import com.app.pnl.ui.views.TitleBar;

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

    private ArrayListAdapter<ReviewsEnt> adapter;
    private ArrayList<ReviewsEnt> userCollection;

    public static ReviewsFragment newInstance() {
        Bundle args = new Bundle();

        ReviewsFragment fragment = new ReviewsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
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
        setReviewsData();
    }

    private void setReviewsData() {
        userCollection = new ArrayList<>();

        userCollection.add(new ReviewsEnt("Garry Smith","asdasdasdasdasdasdasdasdasd",4));
        userCollection.add(new ReviewsEnt("Garry Smith","asdasdasdasdasdasdasdasdasd",4));
        userCollection.add(new ReviewsEnt("Garry Smith","asdasdasdasdasdasdasdasdasd",4));

        bindData(userCollection);
    }

    private void bindData(ArrayList<ReviewsEnt> userCollection) {

        if (userCollection.size() <= 0) {
            txtNoData.setVisibility(View.VISIBLE);
            lvReviews.setVisibility(View.GONE);
        } else {
            txtNoData.setVisibility(View.GONE);
            lvReviews.setVisibility(View.VISIBLE);

        }

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

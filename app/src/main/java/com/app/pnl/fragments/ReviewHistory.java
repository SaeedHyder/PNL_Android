package com.app.pnl.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.app.pnl.R;
import com.app.pnl.entities.ReviewsEnt;
import com.app.pnl.fragments.abstracts.BaseFragment;
import com.app.pnl.ui.adapters.ArrayListAdapter;
import com.app.pnl.ui.viewbinders.abstracts.ReviewsItemBinder;
import com.app.pnl.ui.views.AnyTextView;

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
        setReviewsData();

    }

    private void setReviewsData() {
        userCollection = new ArrayList<>();

        userCollection.add(new ReviewsEnt("Garry Smith", "asdasdasdasdasdasdasdasdasd", 4));
        userCollection.add(new ReviewsEnt("Garry Smith", "asdasdasdasdasdasdasdasdasd", 4));
        userCollection.add(new ReviewsEnt("Garry Smith", "asdasdasdasdasdasdasdasdasd", 4));

        bindData(userCollection);
    }

    private void bindData(ArrayList<ReviewsEnt> userCollection) {

        if (userCollection.size() <= 0) {
            txtNoData.setVisibility(View.VISIBLE);
            lvReviewHistory.setVisibility(View.GONE);
        } else {
            txtNoData.setVisibility(View.GONE);
            lvReviewHistory.setVisibility(View.VISIBLE);

        }

        adapter.clearList();
        lvReviewHistory.setAdapter(adapter);
        adapter.addAll(userCollection);
        adapter.notifyDataSetChanged();
    }


}
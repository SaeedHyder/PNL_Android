package com.app.pnl.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.app.pnl.R;
import com.app.pnl.entities.FavoritesEnt;
import com.app.pnl.entities.SortingByEnt;
import com.app.pnl.fragments.abstracts.BaseFragment;
import com.app.pnl.ui.adapters.ArrayListAdapter;
import com.app.pnl.ui.viewbinders.abstracts.SortingByItemBinder;
import com.app.pnl.ui.views.AnyTextView;
import com.app.pnl.ui.views.TitleBar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by saeedhyder on 10/19/2017.
 */
public class SortingByFragment extends BaseFragment {
    @BindView(R.id.txt_no_data)
    AnyTextView txtNoData;
    @BindView(R.id.lv_sortingBy)
    ListView lvSortingBy;
    Unbinder unbinder;

    private String sorting="A";

    private ArrayListAdapter<SortingByEnt> adapter;
    private ArrayList<SortingByEnt> userCollection;

    public static SortingByFragment newInstance() {
        Bundle args = new Bundle();

        SortingByFragment fragment = new SortingByFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
        adapter = new ArrayListAdapter<SortingByEnt>(getDockActivity(), new SortingByItemBinder(getDockActivity(), prefHelper));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sortign_by, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setSortingData();
    }

    private void setSortingData() {
        userCollection = new ArrayList<>();

        userCollection.add(new SortingByEnt("drawable://"+R.drawable.company,"AA Company",getString(R.string.lorem_ipsum),"22 street,France","+422 123456789"));
        userCollection.add(new SortingByEnt("drawable://"+R.drawable.company,"AA Company",getString(R.string.lorem_ipsum),"22 street,France","+422 123456789"));
        userCollection.add(new SortingByEnt("drawable://"+R.drawable.company,"AA Company",getString(R.string.lorem_ipsum),"22 street,France","+422 123456789"));

        bindData(userCollection);
    }

    private void bindData(ArrayList<SortingByEnt> userCollection) {

        if (userCollection.size() <= 0) {
            txtNoData.setVisibility(View.VISIBLE);
            lvSortingBy.setVisibility(View.GONE);
        } else {
            txtNoData.setVisibility(View.GONE);
            lvSortingBy.setVisibility(View.VISIBLE);

        }

        adapter.clearList();
        lvSortingBy.setAdapter(adapter);
        adapter.addAll(userCollection);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setTitleBar(TitleBar titleBar) {
        super.setTitleBar(titleBar);
        titleBar.hideButtons();
        titleBar.setSubHeading(getString(R.string.sorting_by) + " " +sorting);
        titleBar.showBackButton();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}

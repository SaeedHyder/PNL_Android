package com.app.pnl.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.app.pnl.R;
import com.app.pnl.entities.CompaniesEnt;
import com.app.pnl.entities.FavoritesEnt;
import com.app.pnl.fragments.abstracts.BaseFragment;
import com.app.pnl.ui.adapters.ArrayListAdapter;
import com.app.pnl.ui.viewbinders.abstracts.FavoritesItemBinder;
import com.app.pnl.ui.views.AnyTextView;
import com.app.pnl.ui.views.TitleBar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by saeedhyder on 10/19/2017.
 */
public class FavouriteFragment extends BaseFragment {

    @BindView(R.id.txt_no_data)
    AnyTextView txtNoData;
    @BindView(R.id.lv_notifications)
    ListView lvNotifications;
    Unbinder unbinder;

    private ArrayListAdapter<FavoritesEnt> adapter;
    private ArrayList<FavoritesEnt> userCollection;


    public static FavouriteFragment newInstance() {
        Bundle args = new Bundle();

        FavouriteFragment fragment = new FavouriteFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
        adapter = new ArrayListAdapter<FavoritesEnt>(getDockActivity(), new FavoritesItemBinder(getDockActivity(), prefHelper));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favourite, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setFavouriteData();
        listner();
    }

    private void listner() {

        lvNotifications.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                getDockActivity().addDockableFragment(CompanyDetailFragment.newInstance(),"CompanyDetailFragment");
            }
        });
    }

    private void setFavouriteData() {
        userCollection = new ArrayList<>();

        userCollection.add(new FavoritesEnt("drawable://"+R.drawable.company,"AA Company",getString(R.string.lorem_ipsum),"22 street,France","+422 123456789"));
        userCollection.add(new FavoritesEnt("drawable://"+R.drawable.company,"AA Company",getString(R.string.lorem_ipsum),"22 street,France","+422 123456789"));
        userCollection.add(new FavoritesEnt("drawable://"+R.drawable.company,"AA Company",getString(R.string.lorem_ipsum),"22 street,France","+422 123456789"));
        userCollection.add(new FavoritesEnt("drawable://"+R.drawable.company,"AA Company",getString(R.string.lorem_ipsum),"22 street,France","+422 123456789"));

        bindData(userCollection);
    }

    private void bindData(ArrayList<FavoritesEnt> userCollection) {

        if (userCollection.size() <= 0) {
            txtNoData.setVisibility(View.VISIBLE);
            lvNotifications.setVisibility(View.GONE);
        } else {
            txtNoData.setVisibility(View.GONE);
            lvNotifications.setVisibility(View.VISIBLE);

        }

        adapter.clearList();
        lvNotifications.setAdapter(adapter);
        adapter.addAll(userCollection);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setTitleBar(TitleBar titleBar) {
        super.setTitleBar(titleBar);
        titleBar.hideButtons();
        titleBar.setSubHeading(getString(R.string.favorites));
        titleBar.showBackButton();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}

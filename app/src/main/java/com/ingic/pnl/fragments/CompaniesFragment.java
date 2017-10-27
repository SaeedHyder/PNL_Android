package com.ingic.pnl.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ingic.pnl.R;
import com.ingic.pnl.entities.CompaniesEnt;
import com.ingic.pnl.fragments.abstracts.BaseFragment;
import com.ingic.pnl.ui.adapters.ArrayListAdapter;
import com.ingic.pnl.ui.viewbinders.abstracts.CompaniesItemBinder;
import com.ingic.pnl.ui.views.AnyTextView;
import com.ingic.pnl.ui.views.TitleBar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by saeedhyder on 10/19/2017.
 */
public class CompaniesFragment extends BaseFragment {


    @BindView(R.id.txt_no_data)
    AnyTextView txtNoData;
    @BindView(R.id.lv_companies)
    ListView lvCompanies;
    Unbinder unbinder;

    private ArrayListAdapter<CompaniesEnt> adapter;
    private ArrayList<CompaniesEnt> userCollection;

    public static CompaniesFragment newInstance() {
        Bundle args = new Bundle();

        CompaniesFragment fragment = new CompaniesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
        adapter = new ArrayListAdapter<CompaniesEnt>(getDockActivity(), new CompaniesItemBinder(getDockActivity(), prefHelper));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_companies, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setCompaniesData();
        listners();

    }

    private void listners() {

        lvCompanies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                getDockActivity().addDockableFragment(SortingByFragment.newInstance(userCollection.get(position)),"SortingByFragment");
            }
        });
    }

    private void setCompaniesData() {
        userCollection = new ArrayList<>();

        userCollection.add(new CompaniesEnt("A"));
        userCollection.add(new CompaniesEnt("B"));
        userCollection.add(new CompaniesEnt("C"));
        userCollection.add(new CompaniesEnt("D"));

    bindData(userCollection);
    }

    private void bindData(ArrayList<CompaniesEnt> userCollection) {

        if (userCollection.size() <= 0) {
            txtNoData.setVisibility(View.VISIBLE);
            lvCompanies.setVisibility(View.GONE);
        } else {
            txtNoData.setVisibility(View.GONE);
            lvCompanies.setVisibility(View.VISIBLE);

        }

        adapter.clearList();
        lvCompanies.setAdapter(adapter);
        adapter.addAll(userCollection);
        adapter.notifyDataSetChanged();
    }


    @Override
    public void setTitleBar(TitleBar titleBar) {
        super.setTitleBar(titleBar);
        titleBar.hideButtons();
        titleBar.setSubHeading(getString(R.string.companies));
        titleBar.showBackButton();
        titleBar.getEditTextViewSearch(R.id.edt_search).setText("");

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}

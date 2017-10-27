package com.ingic.pnl.fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ingic.pnl.R;
import com.ingic.pnl.entities.ViewAllServicesEnt;
import com.ingic.pnl.fragments.abstracts.BaseFragment;
import com.ingic.pnl.helpers.UIHelper;
import com.ingic.pnl.helpers.Utils;
import com.ingic.pnl.ui.adapters.ArrayListAdapter;
import com.ingic.pnl.ui.viewbinders.abstracts.ViewAllServicesItemBinder;
import com.ingic.pnl.ui.views.AnyTextView;
import com.ingic.pnl.ui.views.TitleBar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by saeedhyder on 10/19/2017.
 */
public class ViewAllServicesFragment extends BaseFragment {

    @BindView(R.id.txt_no_data)
    AnyTextView txtNoData;
    @BindView(R.id.lv_all_services)
    ListView lvAllServices;
    Unbinder unbinder;

    private ArrayListAdapter<ViewAllServicesEnt> adapter;
    private ArrayList<ViewAllServicesEnt> userCollection;

    public static ViewAllServicesFragment newInstance() {
        Bundle args = new Bundle();

        ViewAllServicesFragment fragment = new ViewAllServicesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
        adapter = new ArrayListAdapter<ViewAllServicesEnt>(getDockActivity(), new ViewAllServicesItemBinder(getDockActivity(), prefHelper));
    }

    @Override
    public void setTitleBar(TitleBar titleBar) {
        super.setTitleBar(titleBar);
        titleBar.hideButtons();
        titleBar.setSubHeading(getString(R.string.services));
        titleBar.getEditTextViewSearch(R.id.edt_search).setText("");
        titleBar.showBackButton();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_all_services, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setViewAllServicesData();
        listners();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void listners() {
        lvAllServices.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                getDockActivity().addDockableFragment(ServiceCategoryFragment.newInstance(), "SortingByFragment");
            }
        });
    }

    private void setViewAllServicesData() {

        userCollection = new ArrayList<>();

        userCollection.add(new ViewAllServicesEnt("Service Category 1"));
        userCollection.add(new ViewAllServicesEnt("Service Category 2"));
        userCollection.add(new ViewAllServicesEnt("Service Category 3"));
        userCollection.add(new ViewAllServicesEnt("Service Category 4"));

        bindData(userCollection);
    }

    private void bindData(ArrayList<ViewAllServicesEnt> userCollection) {

        if (userCollection.size() <= 0) {
            txtNoData.setVisibility(View.VISIBLE);
            lvAllServices.setVisibility(View.GONE);
        } else {
            txtNoData.setVisibility(View.GONE);
            lvAllServices.setVisibility(View.VISIBLE);

        }

        adapter.clearList();
        lvAllServices.setAdapter(adapter);
        adapter.addAll(userCollection);
        adapter.notifyDataSetChanged();

    }
}

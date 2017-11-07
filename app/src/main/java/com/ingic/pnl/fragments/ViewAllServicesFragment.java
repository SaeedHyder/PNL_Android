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
import com.ingic.pnl.entities.ServiceEnt;
import com.ingic.pnl.fragments.abstracts.BaseFragment;
import com.ingic.pnl.global.WebServiceConstants;
import com.ingic.pnl.helpers.Utils;
import com.ingic.pnl.ui.adapters.ArrayListAdapter;
import com.ingic.pnl.ui.viewbinders.abstracts.ViewAllServicesItemBinder;
import com.ingic.pnl.ui.views.AnyTextView;
import com.ingic.pnl.ui.views.TitleBar;

import java.util.ArrayList;
import java.util.regex.Pattern;

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

    private ArrayListAdapter<ServiceEnt> adapter;
    private ArrayList<ServiceEnt> userCollection;

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
        adapter = new ArrayListAdapter<ServiceEnt>(getDockActivity(), new ViewAllServicesItemBinder(getDockActivity(), prefHelper));
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

        serviceHelper.enqueueCall(webService.getAllServices(), WebServiceConstants.SERVICESLIST);
        listners();
    }

    @Override
    public void ResponseSuccess(Object result, String Tag, String message) {
        switch (Tag) {
            case WebServiceConstants.SERVICESLIST:
                bindData((ArrayList<ServiceEnt>) result);
                break;

        }
    }

    @Override
    public void ResponseFailure(String tag) {
        switch (tag){
            case WebServiceConstants.SERVICESLIST:
                txtNoData.setVisibility(View.VISIBLE);
                lvAllServices.setVisibility(View.GONE);
                break;
        }

    }

    private void bindData(ArrayList<ServiceEnt> data) {

        userCollection = new ArrayList<>();

        userCollection = data;

        if (data.size() <= 0) {
            txtNoData.setVisibility(View.VISIBLE);
            lvAllServices.setVisibility(View.GONE);
        } else {
            txtNoData.setVisibility(View.GONE);
            lvAllServices.setVisibility(View.VISIBLE);

        }

        adapter.clearList();
        lvAllServices.setAdapter(adapter);
        adapter.addAll(data);
        adapter.notifyDataSetChanged();

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
                getDockActivity().addDockableFragment(ServiceCategoryFragment.newInstance(userCollection.get(position)), "SortingByFragment");
            }
        });
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
                localSearch(getSearchedArray(s.toString()));
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   UIHelper.showShortToastInCenter(getDockActivity(), getString(R.string.beta));
                Utils.HideKeyBoard(getDockActivity());
            }
        }, getString(R.string.search_category));
    }

    public ArrayList<ServiceEnt> getSearchedArray(String keyword) {
        if (userCollection.isEmpty()) {
            return new ArrayList<>();
        }

        ArrayList<ServiceEnt> arrayList = new ArrayList<>();

        for (ServiceEnt item : userCollection) {
            String UserName = "";
            if (item != null) {
                UserName = item.getName();
            }
            if (Pattern.compile(Pattern.quote(keyword), Pattern.CASE_INSENSITIVE).matcher(UserName).find()) {
                /*UserName.contains(keyword)*/
                arrayList.add(item);
            }
        }
        return arrayList;

    }

    private void localSearch(ArrayList<ServiceEnt> data) {

        userCollection = new ArrayList<>();
        userCollection = data;
        adapter.clearList();
        adapter.addAll(data);
        adapter.notifyDataSetChanged();

    }

}

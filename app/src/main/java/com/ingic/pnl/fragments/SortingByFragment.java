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
import com.ingic.pnl.entities.CompaniesEnt;
import com.ingic.pnl.entities.ServiceEnt;
import com.ingic.pnl.entities.SortingByEnt;
import com.ingic.pnl.fragments.abstracts.BaseFragment;
import com.ingic.pnl.global.WebServiceConstants;
import com.ingic.pnl.helpers.UIHelper;
import com.ingic.pnl.helpers.Utils;
import com.ingic.pnl.ui.adapters.ArrayListAdapter;
import com.ingic.pnl.ui.viewbinders.abstracts.SortingByItemBinder;
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
public class SortingByFragment extends BaseFragment {
    private static String SortingNmae = "SortingNmae";
    @BindView(R.id.txt_no_data)
    AnyTextView txtNoData;
    @BindView(R.id.lv_sortingBy)
    ListView lvSortingBy;
    Unbinder unbinder;
    private CompaniesEnt entity;

    private String sortingid = "A";

    private ArrayListAdapter<SortingByEnt> adapter;
    private ArrayList<SortingByEnt> userCollection;

    public static SortingByFragment newInstance(String companiesLetter) {
        Bundle args = new Bundle();
        args.putString(SortingNmae, companiesLetter);
        SortingByFragment fragment = new SortingByFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            SortingNmae = getArguments().getString(SortingNmae);
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
        serviceHelper.enqueueCall(webService.getCompaniesByCaracter(SortingNmae), WebServiceConstants.LIST_COMPANY_BY_CARACTER);
        listners();
    }

    @Override
    public void ResponseSuccess(Object result, String Tag, String message) {
        switch (Tag) {
            case WebServiceConstants.LIST_COMPANY_BY_CARACTER:
                bindData((ArrayList<SortingByEnt>) result);
                userCollection=((ArrayList<SortingByEnt>) result);
                break;
        }
    }

    @Override
    public void ResponseFailure(String tag) {
        switch (tag) {
            case WebServiceConstants.LIST_COMPANY_BY_CARACTER:
                txtNoData.setVisibility(View.VISIBLE);
                lvSortingBy.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void listners() {

        lvSortingBy.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                getDockActivity().addDockableFragment(CompanyDetailFragment.newInstance(userCollection.get(position).getId(),userCollection.get(position).getName()), "CompanyDetailFragment");
            }
        });
    }

    private void bindData(ArrayList<SortingByEnt> userCollection) {


        adapter.clearList();
        lvSortingBy.setAdapter(adapter);
        adapter.addAll(userCollection);
        adapter.notifyDataSetChanged();
    }



    @Override
    public void setTitleBar(TitleBar titleBar) {
        super.setTitleBar(titleBar);
        titleBar.hideButtons();
        //titleBar.setSubHeading(getString(R.string.sorting_by) + " " +sortingid);
        titleBar.setSubHeading(getString(R.string.companies));
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
                // UIHelper.showShortToastInCenter(getDockActivity(), "Will be implemented in Beta version");
                Utils.HideKeyBoard(getDockActivity());
            }
        }, getString(R.string.search_company));
        titleBar.showBackButton();
    }

    public ArrayList<SortingByEnt> getSearchedArray(String keyword) {
        if (userCollection.isEmpty()) {
            return new ArrayList<>();
        }

        ArrayList<SortingByEnt> arrayList = new ArrayList<>();

        for (SortingByEnt item : userCollection) {
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

    private void localSearch(ArrayList<SortingByEnt> data) {

        adapter.clearList();
        adapter.addAll(data);
        adapter.notifyDataSetChanged();

    }
}

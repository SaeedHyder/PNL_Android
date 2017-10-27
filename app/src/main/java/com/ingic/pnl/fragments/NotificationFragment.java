package com.ingic.pnl.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ingic.pnl.R;
import com.ingic.pnl.entities.NotificationEnt;
import com.ingic.pnl.fragments.abstracts.BaseFragment;
import com.ingic.pnl.ui.adapters.ArrayListAdapter;
import com.ingic.pnl.ui.viewbinders.abstracts.NotificationItemBinder;
import com.ingic.pnl.ui.views.AnyTextView;
import com.ingic.pnl.ui.views.TitleBar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by saeedhyder on 10/19/2017.
 */
public class NotificationFragment extends BaseFragment {

    @BindView(R.id.txt_no_data)
    AnyTextView txtNoData;
    @BindView(R.id.lv_notifications)
    ListView lvNotifications;
    Unbinder unbinder;


    private ArrayListAdapter<NotificationEnt> adapter;
    private ArrayList<NotificationEnt> userCollection;
    private int count = 6;

    public static NotificationFragment newInstance() {
        Bundle args = new Bundle();

        NotificationFragment fragment = new NotificationFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
        adapter = new ArrayListAdapter<NotificationEnt>(getDockActivity(), new NotificationItemBinder(getDockActivity(), prefHelper));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notification, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setNotificationData();
    }

    private void setNotificationData() {
        userCollection = new ArrayList<>();

        userCollection.add(new NotificationEnt("Garry Smith",getString(R.string.small_ipsum),"6:00 PM"));
        userCollection.add(new NotificationEnt("Garry Smith",getString(R.string.small_ipsum),"6:00 PM"));
        userCollection.add(new NotificationEnt("Garry Smith",getString(R.string.small_ipsum),"6:00 PM"));


        bindData(userCollection);
    }

    private void bindData(ArrayList<NotificationEnt> userCollection) {

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
        titleBar.setSubHeading(getString(R.string.notifications) +" "+ "(" + count + ")");
        titleBar.showBackButton();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}

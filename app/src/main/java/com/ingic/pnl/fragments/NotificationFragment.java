package com.ingic.pnl.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ingic.pnl.R;
import com.ingic.pnl.entities.NotificationEnt;
import com.ingic.pnl.entities.NotificationItemEnt;
import com.ingic.pnl.fragments.abstracts.BaseFragment;
import com.ingic.pnl.global.WebServiceConstants;
import com.ingic.pnl.helpers.DialogHelper;
import com.ingic.pnl.helpers.UIHelper;
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


    private ArrayListAdapter<NotificationItemEnt> adapter;
    private ArrayList<NotificationItemEnt> userCollection;
    private int count = 0;
    private TitleBar mtitlebar;

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
        adapter = new ArrayListAdapter<NotificationItemEnt>(getDockActivity(), new NotificationItemBinder(getDockActivity(), prefHelper));
    }

    @Override
    public void ResponseSuccess(Object result, String Tag, String message) {
        switch (Tag) {
            case WebServiceConstants.GET_NOTIFICATIONS:
                NotificationEnt ent = (NotificationEnt) result;
                userCollection = ent.getNotifications();
                if (ent.getUnReadCount() > 0) {
                    mtitlebar.setSubHeading(getString(R.string.notifications) + " " + "(" + ent.getUnReadCount() + ")");
                    mtitlebar.invalidate();
                } else {
                    mtitlebar.setSubHeading(getString(R.string.notifications));
                    mtitlebar.invalidate();
                }
                bindData(userCollection);
                break;
            case WebServiceConstants.CLEAR_NOTIFICATIONS:
                serviceHelper.enqueueCall(webService.getNotificationList(prefHelper.getUserID()), WebServiceConstants.GET_NOTIFICATIONS);
                break;
        }
    }

    @Override
    public void ResponseFailure(String tag) {
        switch (tag) {
            case WebServiceConstants.GET_NOTIFICATIONS:
                txtNoData.setVisibility(View.VISIBLE);
                lvNotifications.setVisibility(View.GONE);
                mtitlebar.setSubHeading(getString(R.string.notifications));
                mtitlebar.invalidate();

                break;


        }
    }

    @Override
    public void setTitleBar(TitleBar titleBar) {
        super.setTitleBar(titleBar);
        titleBar.hideButtons();
        mtitlebar = titleBar;
        titleBar.showClearButton(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogHelper dialog = new DialogHelper(getMainActivity());
                dialog.initlogout(R.layout.logout_dialog, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        serviceHelper.enqueueCall(webService.clearAllNotification(prefHelper.getUserID()), WebServiceConstants.CLEAR_NOTIFICATIONS);
                        dialog.hideDialog();
                    }
                }, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        UIHelper.hideSoftKeyboard(getDockActivity(), v);
                        dialog.hideDialog();
                    }
                }, getString(R.string.clear_notification), getString(R.string.clear_message));
                dialog.showDialog();

            }
        });
        titleBar.setSubHeading(getString(R.string.notifications));
        titleBar.showBackButton();
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
        serviceHelper.enqueueCall(webService.getNotificationList(prefHelper.getUserID()), WebServiceConstants.GET_NOTIFICATIONS);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void setNotificationData(ArrayList<NotificationItemEnt> result) {
        userCollection = new ArrayList<>();
        userCollection.addAll(result);
        bindData(userCollection);
    }

    private void bindData(ArrayList<NotificationItemEnt> userCollection) {

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
}

package com.ingic.pnl.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ingic.pnl.R;
import com.ingic.pnl.fragments.abstracts.BaseFragment;
import com.ingic.pnl.helpers.DialogHelper;
import com.ingic.pnl.helpers.UIHelper;
import com.ingic.pnl.ui.views.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class SideMenuFragment extends BaseFragment {

    @BindView(R.id.btn_home)
    LinearLayout btnHome;
    @BindView(R.id.btn_notifications)
    LinearLayout btnNotifications;
    @BindView(R.id.btn_favourites)
    LinearLayout btnFavourites;
    @BindView(R.id.btn_review_history)
    LinearLayout btnReviewHistory;
    @BindView(R.id.btn_setting)
    LinearLayout btnSetting;
    @BindView(R.id.btn_logout)
    LinearLayout btnLogout;
    Unbinder unbinder;

    public static SideMenuFragment newInstance() {
        return new SideMenuFragment();

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void setTitleBar(TitleBar titleBar) {
        super.setTitleBar(titleBar);
        titleBar.hideTitleBar();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sidemenu, container, false);

        ButterKnife.bind(this, view);
        return view;

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    @OnClick({R.id.btn_home, R.id.btn_notifications, R.id.btn_favourites, R.id.btn_review_history, R.id.btn_setting, R.id.btn_logout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_home:
                getDockActivity().popBackStackTillEntry(0);
                getDockActivity().replaceDockableFragment(HomeFragment.newInstance(), "HomeFragment");
                break;
            case R.id.btn_notifications:
//                getDockActivity().replaceDockableFragment(NotificationFragment.newInstance(), "NotificationFragment");
                UIHelper.showShortToastInCenter(getDockActivity(),"Will be implemented in Future Version");
                break;
            case R.id.btn_favourites:
                getDockActivity().replaceDockableFragment(FavouriteFragment.newInstance(), "FavouriteFragment");
                break;
            case R.id.btn_review_history:
                getDockActivity().replaceDockableFragment(ReviewHistory.newInstance(), "ReviewHistory");
                break;
            case R.id.btn_setting:
                getDockActivity().replaceDockableFragment(SettingFragment.newInstance(), "SettingFragment");
                break;
            case R.id.btn_logout:
                hideKeyboard();
                final DialogHelper dialog = new DialogHelper(getDockActivity());
                dialog.initlogout(R.layout.logout_dialog, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        prefHelper.setLoginStatus(false);
                        hideKeyboard();
                        getDockActivity().popBackStackTillEntry(0);
                        getDockActivity().replaceDockableFragment(LoginFragment.newInstance(), "LoginFragment");
                        dialog.hideDialog();
                    }
                }, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.hideDialog();
                    }
                });
                dialog.showDialog();

                break;
        }
    }
}

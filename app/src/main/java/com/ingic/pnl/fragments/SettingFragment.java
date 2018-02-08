package com.ingic.pnl.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

import com.ingic.pnl.R;
import com.ingic.pnl.fragments.abstracts.BaseFragment;
import com.ingic.pnl.global.WebServiceConstants;
import com.ingic.pnl.helpers.UIHelper;
import com.ingic.pnl.ui.views.AnyTextView;
import com.ingic.pnl.ui.views.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by saeedhyder on 10/19/2017.
 */
public class SettingFragment extends BaseFragment {

    @BindView(R.id.toggleLanguage)
    ToggleButton toggleLanguage;
    @BindView(R.id.languageLayout)
    LinearLayout languageLayout;
    @BindView(R.id.tv_change_password)
    AnyTextView tvChangePassword;
    @BindView(R.id.edt_rate_app)
    AnyTextView edtRateApp;
    @BindView(R.id.btn_update)
    Button btnUpdate;
    Unbinder unbinder;

    public static SettingFragment newInstance() {
        Bundle args = new Bundle();

        SettingFragment fragment = new SettingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }

    }

    @Override
    public void ResponseSuccess(Object result, String Tag, String message) {
        switch (Tag) {
            case WebServiceConstants.CHANGE_NOTIFICATION_STATUS:
                prefHelper.setUserNotificationStatus(toggleLanguage.isChecked());
                UIHelper.showShortToastInCenter(getDockActivity(), message + "");
                break;
        }
    }


    @Override
    public void setTitleBar(TitleBar titleBar) {
        super.setTitleBar(titleBar);
        titleBar.hideButtons();
        titleBar.setSubHeading(getString(R.string.settings));
        titleBar.showBackButton();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        unbinder = ButterKnife.bind(this, view);
        toggleListner();
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void toggleListner() {
        toggleLanguage.setChecked(prefHelper.getNotificationStauts());
        toggleLanguage.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                serviceHelper.enqueueCall(webService.changeNotificationStatus(prefHelper.getUserID(), isChecked), WebServiceConstants.CHANGE_NOTIFICATION_STATUS);
            }
        });
    }

    @OnClick({R.id.tv_change_password, R.id.edt_rate_app, R.id.btn_update})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_change_password:
                getDockActivity().replaceDockableFragment(ChangePasswordFragment.newInstance(), ChangePasswordFragment.class.getSimpleName());
                break;
            case R.id.edt_rate_app:
                getDockActivity().replaceDockableFragment(EditProfileFragment.newInstance(), EditProfileFragment.class.getSimpleName());
                break;
            case R.id.btn_update:
                getDockActivity().popBackStackTillEntry(0);
                getDockActivity().replaceDockableFragment(HomeFragment.newInstance(), HomeFragment.class.getSimpleName());
                break;
        }
    }
}

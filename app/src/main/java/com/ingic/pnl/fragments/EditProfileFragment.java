package com.ingic.pnl.fragments;

import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import com.ingic.pnl.R;
import com.ingic.pnl.fragments.abstracts.BaseFragment;
import com.ingic.pnl.global.WebServiceConstants;
import com.ingic.pnl.helpers.UIHelper;
import com.ingic.pnl.ui.views.AnyEditTextView;
import com.ingic.pnl.ui.views.TitleBar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created on 10/23/2017.
 */
public class EditProfileFragment extends BaseFragment {
    @BindView(R.id.edt_full_name)
    AnyEditTextView edtFullName;
    @BindView(R.id.edt_email)
    AnyEditTextView edtEmail;
    @BindView(R.id.edt_phone)
    AnyEditTextView edtPhone;
    @BindView(R.id.edt_city)
    AnyEditTextView edtCity;
    @BindView(R.id.btn_update)
    Button btnUpdate;
    @BindView(R.id.main_parent)
    LinearLayout main_parent;
    Unbinder unbinder;

    public static EditProfileFragment newInstance() {
        Bundle args = new Bundle();

        EditProfileFragment fragment = new EditProfileFragment();
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
            case WebServiceConstants.UPDATEPROFILE:
                prefHelper.setPhoneNum(edtPhone.getText().toString());
                prefHelper.setCity(edtCity.getText().toString());
                prefHelper.setUserName(edtFullName.getText().toString());
                prefHelper.setUserEmail(edtEmail.getText().toString());
                UIHelper.showShortToastInCenter(getDockActivity(), message);
                getDockActivity().popBackStackTillEntry(0);
                getDockActivity().replaceDockableFragment(HomeFragment.newInstance(), "HomeFragment");
                break;
        }
    }

    @Override
    public void setTitleBar(TitleBar titleBar) {
        super.setTitleBar(titleBar);
        titleBar.hideButtons();
        titleBar.setSubHeading(getString(R.string.edit_profile));
        titleBar.showBackButton();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_profile, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        edtFullName.setText(prefHelper.getUserName() + "");
        if (!prefHelper.getCity().equals("null"))
            edtCity.setText(prefHelper.getCity() + "");
        if (!prefHelper.getPhoneNum().equals("null"))
            edtPhone.setText(prefHelper.getPhoneNum() + "");
        if (!prefHelper.getUserEmail().equals("null"))
            edtEmail.setText(prefHelper.getUserEmail() + "");
        main_parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btn_update)
    public void onViewClicked() {
        if (isvalidated()) {
            // UIHelper.showShortToastInCenter(getDockActivity(), getString(R.string.profie_update_message));
            serviceHelper.enqueueCall(webService.editProfile(prefHelper.getUserID(), edtFullName.getText().toString(),
                    edtPhone.getText().toString(), edtPhone.getText().toString()), WebServiceConstants.UPDATEPROFILE);
        }
    }

    private boolean isvalidated() {
        if (edtFullName.getText() == null || edtFullName.getText().toString().isEmpty()) {
            if (edtFullName.requestFocus()) {
                getMainActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
            }
            edtFullName.setError(getString(R.string.enter_name));
            return false;
        } else if (edtEmail.getText() == null || (edtEmail.getText().toString().isEmpty()) ||
                !(Patterns.EMAIL_ADDRESS.matcher(edtEmail.getText().toString()).matches())) {
            if (edtEmail.requestFocus()) {
                getMainActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
            }
            edtEmail.setError(getString(R.string.enter_valid_email));
            return false;
        } else if (!edtPhone.getText().toString().equals("") && edtPhone.getText().toString().length() <11) {
            if (edtPhone.requestFocus()) {
                getMainActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
            }
            edtPhone.setError(getString(R.string.numberLength));
            return false;
        } else if (!edtCity.getText().toString().equals("") && edtCity.getText().toString().length() <2) {
            if (edtCity.requestFocus()) {
                getMainActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
            }
            edtCity.setError(getString(R.string.cityLength));
            return false;
        } else {
            return true;
        }
    }
}
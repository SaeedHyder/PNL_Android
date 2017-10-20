package com.app.pnl.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.app.pnl.R;
import com.app.pnl.fragments.abstracts.BaseFragment;
import com.app.pnl.ui.views.AnyEditTextView;
import com.app.pnl.ui.views.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by saeedhyder on 10/19/2017.
 */
public class ChangePasswordFragment extends BaseFragment {

    @BindView(R.id.edt_old_password)
    AnyEditTextView edtCurrentPassword;
    @BindView(R.id.edt_new_password)
    AnyEditTextView edtNewPassword;
    @BindView(R.id.edt_cfm_new_password)
    AnyEditTextView edtConfirmPassword;
    @BindView(R.id.btn_update)
    Button btnUpdate;
    Unbinder unbinder;


    public static ChangePasswordFragment newInstance() {
        Bundle args = new Bundle();

        ChangePasswordFragment fragment = new ChangePasswordFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_change_password, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private boolean isvalidate() {
        if (edtCurrentPassword.getText() == null || (edtCurrentPassword.getText().toString().isEmpty())) {
            edtCurrentPassword.setError(getString(R.string.enter_password));
            return false;
        } else if (edtNewPassword.getText() == null || (edtNewPassword.getText().toString().isEmpty())) {
            edtNewPassword.setError(getString(R.string.enter_password));
            return false;
        } else if (edtNewPassword.getText().toString().length() < 6) {
            edtNewPassword.setError(getString(R.string.passwordLength));
            return false;
        } else if (edtConfirmPassword.getText() == null || (edtConfirmPassword.getText().toString().isEmpty())) {
            edtConfirmPassword.setError(getString(R.string.enter_password));
            return false;
        } else if (edtConfirmPassword.getText().toString().length() < 6) {
            edtConfirmPassword.setError(getString(R.string.passwordLength));
            return false;

        } else if (!edtConfirmPassword.getText().toString().equals(edtNewPassword.getText().toString())) {
            edtConfirmPassword.setError(getString(R.string.does_not_match));
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void setTitleBar(TitleBar titleBar) {
        super.setTitleBar(titleBar);
        titleBar.hideButtons();
        titleBar.setSubHeading(getString(R.string.change_password_));
        titleBar.showBackButton();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btn_update)
    public void onViewClicked() {
        if (isvalidate()) {
            getDockActivity().addDockableFragment(HomeFragment.newInstance(), HomeFragment.class.getName());
        }
    }
}

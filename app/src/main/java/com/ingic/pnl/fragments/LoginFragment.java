package com.ingic.pnl.fragments;

import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import com.ingic.pnl.R;
import com.ingic.pnl.entities.UserIDEnt;
import com.ingic.pnl.fragments.abstracts.BaseFragment;
import com.ingic.pnl.global.WebServiceConstants;
import com.ingic.pnl.helpers.UIHelper;
import com.ingic.pnl.ui.views.AnyEditTextView;
import com.ingic.pnl.ui.views.AnyTextView;
import com.ingic.pnl.ui.views.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class LoginFragment extends BaseFragment {


    @BindView(R.id.edt_email)
    AnyEditTextView edtEmail;
    @BindView(R.id.edt_password)
    AnyEditTextView edtPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.btn_login_facebook)
    FrameLayout btnLoginFacebook;
    @BindView(R.id.btn_login_google)
    FrameLayout btnLoginGoogle;
    @BindView(R.id.txt_signUp)
    AnyTextView txtSignUp;
    Unbinder unbinder;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    private void setListeners() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);
        return view;

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        setListeners();
        getMainActivity().lockDrawer();

    }

    @Override
    public void onResume() {
        super.onResume();
        getDockActivity().lockDrawer();
    }

    @Override
    public void ResponseSuccess(Object result, String Tag, String message) {
        switch (Tag) {
            case WebServiceConstants.LOGIN:
                UserIDEnt userID = (UserIDEnt) result;
                prefHelper.setUserID(userID.getUserId() + "");
                getDockActivity().popBackStackTillEntry(0);
                prefHelper.setLoginStatus(true);
                getDockActivity().replaceDockableFragment(HomeFragment.newInstance(), "HomeFragment");
                break;
        }
    }

    @Override
    public void setTitleBar(TitleBar titleBar) {
        // TODO Auto-generated method stub
        super.setTitleBar(titleBar);
        titleBar.hideButtons();
        titleBar.setSubHeading(getString(R.string.log_in));
    }

    @OnClick({R.id.btn_login, R.id.btn_login_facebook, R.id.txt_signUp, R.id.btn_login_google, R.id.btn_forgot_password})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                if (isvalidated()) {
                    serviceHelper.enqueueCall(webService.makeUserLogin(edtEmail.getText().toString(), edtPassword.getText().toString()),
                            WebServiceConstants.LOGIN);
                }
                break;
            case R.id.btn_login_facebook:
                UIHelper.showShortToastInCenter(getDockActivity(), "Will be implemented in Beta Version");
                break;
            case R.id.txt_signUp:
                getDockActivity().replaceDockableFragment(RegisterFragment.newInstance(), "RegisterFragment");
                break;
            case R.id.btn_login_google:
                UIHelper.showShortToastInCenter(getDockActivity(), "Will be implemented in Beta Version");
                break;
            case R.id.btn_forgot_password:
                getDockActivity().replaceDockableFragment(ForgotPasswordFragment.newInstance(), "ForgotPasswordFragment");
                break;
        }
    }

    private boolean isvalidated() {
        if (edtEmail.getText() == null || (edtEmail.getText().toString().isEmpty()) ||
                !(Patterns.EMAIL_ADDRESS.matcher(edtEmail.getText().toString()).matches())) {
            edtEmail.setError(getString(R.string.enter_valid_email));
            return false;
        } else if (edtPassword.getText().toString().isEmpty()) {
            edtPassword.setError(getString(R.string.enter_password));
            return false;
        } else if (edtPassword.getText().toString().length() < 6) {
            edtPassword.setError(getString(R.string.passwordLength));
            return false;
        } else {
            return true;
        }
    }
}

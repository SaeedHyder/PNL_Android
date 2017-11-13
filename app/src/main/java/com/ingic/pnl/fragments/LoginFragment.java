package com.ingic.pnl.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import com.facebook.CallbackManager;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.ingic.pnl.R;
import com.ingic.pnl.entities.FacebookLoginEnt;
import com.ingic.pnl.entities.UserIDEnt;
import com.ingic.pnl.fragments.abstracts.BaseFragment;
import com.ingic.pnl.global.WebServiceConstants;
import com.ingic.pnl.helpers.FacebookLoginHelper;
import com.ingic.pnl.helpers.GoogleHelper;
import com.ingic.pnl.interfaces.FacebookLoginListener;
import com.ingic.pnl.ui.views.AnyEditTextView;
import com.ingic.pnl.ui.views.AnyTextView;
import com.ingic.pnl.ui.views.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class LoginFragment extends BaseFragment implements GoogleHelper.GoogleHelperInterfce, FacebookLoginListener {

    private static final int RC_SIGN_IN = 007;
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
    private FacebookLoginHelper facebookLoginHelper;
    private CallbackManager callbackManager;
    private GoogleHelper googleHelper;
    private String mSocialMediaPlatform = "";
    private String mSocialMediaID = "";

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    private void setListeners() {

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
                LoginManager.getInstance().logOut();
                googleHelper.googleRevokeAccess();
                googleHelper.googleSignOut();
                UserIDEnt userID = (UserIDEnt) result;
                prefHelper.setUserID(userID.getUser().getUserId() + "");
                prefHelper.setUserName(userID.getUser().getName() + "");
                prefHelper.setCity(userID.getUser().getCity() + "");
                prefHelper.setPhoneNum(userID.getUser().getPhone() + "");
                prefHelper.setUserEmail(userID.getUser().getEmail() + "");
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

    private void setupGoogleSignup() {
        googleHelper = GoogleHelper.getInstance();
        googleHelper.setGoogleHelperInterface(this);
        googleHelper.configGoogleApiClient(this);
    }

    private void setupFacebookLogin() {
        callbackManager = CallbackManager.Factory.create();
        // btnfbLogin.setFragment(this);
        facebookLoginHelper = new FacebookLoginHelper(getDockActivity(), this, this);
        LoginManager.getInstance().registerCallback(callbackManager, facebookLoginHelper);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            googleHelper.handleGoogleResult(requestCode, resultCode, data);
        } else
            callbackManager.onActivityResult(requestCode, resultCode, data);
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
        setupGoogleSignup();
        setupFacebookLogin();
        setListeners();
        getMainActivity().lockDrawer();

    }

    @Override
    public void onStart() {
        super.onStart();
        googleHelper.ConnectGoogleAPi();
        // googleHelper.checkGoogleSeesion();
    }

    @Override
    public void onStop() {
        super.onStop();
        googleHelper.DisconnectGoogleApi();
    }

    private void setSocialmediaLogin(String id) {
        serviceHelper.enqueueCall(webService.makeUserSocialMediaLogin(id), WebServiceConstants.LOGIN);
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
                LoginManager.getInstance().logInWithReadPermissions(LoginFragment.this, facebookLoginHelper.getPermissionNeeds());

                break;
            case R.id.txt_signUp:
                getDockActivity().replaceDockableFragment(RegisterFragment.newInstance(), "RegisterFragment");
                break;
            case R.id.btn_login_google:
                googleHelper.intentGoogleSign();
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

    @Override
    public void onSuccessfulFacebookLogin(FacebookLoginEnt LoginEnt) {
        mSocialMediaPlatform = WebServiceConstants.PLATFORM_FACEBOOK;
        mSocialMediaID = LoginEnt.getFacebookUID();
        setSocialmediaLogin(mSocialMediaID);
    }

    @Override
    public void onGoogleSignInResult(GoogleSignInAccount result) {
        mSocialMediaPlatform = WebServiceConstants.PLATFORM_GOOGLE;
        mSocialMediaID = result.getId();
        setSocialmediaLogin(mSocialMediaID);
    }
}

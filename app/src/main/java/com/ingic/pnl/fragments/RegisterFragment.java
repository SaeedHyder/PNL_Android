package com.ingic.pnl.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;

import com.facebook.CallbackManager;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.iid.FirebaseInstanceId;
import com.ingic.pnl.R;
import com.ingic.pnl.entities.FacebookLoginEnt;
import com.ingic.pnl.entities.UserIDEnt;
import com.ingic.pnl.fragments.abstracts.BaseFragment;
import com.ingic.pnl.global.AppConstants;
import com.ingic.pnl.global.WebServiceConstants;
import com.ingic.pnl.helpers.FacebookLoginHelper;
import com.ingic.pnl.helpers.GoogleHelper;
import com.ingic.pnl.helpers.TokenUpdater;
import com.ingic.pnl.interfaces.FacebookLoginListener;
import com.ingic.pnl.ui.views.AnyEditTextView;
import com.ingic.pnl.ui.views.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created on 10/17/2017.
 */
public class RegisterFragment extends BaseFragment implements GoogleHelper.GoogleHelperInterfce, FacebookLoginListener {
    private static final int RC_SIGN_IN = 007;
    @BindView(R.id.edt_name)
    AnyEditTextView edtName;
    @BindView(R.id.edt_email)
    AnyEditTextView edtEmail;
    @BindView(R.id.edt_password)
    AnyEditTextView edtPassword;
    @BindView(R.id.edt_comfirm_password)
    AnyEditTextView edtComfirmPassword;
    @BindView(R.id.btn_signup)
    Button btnSignup;
    @BindView(R.id.btn_facebook)
    FrameLayout btnFacebook;
    @BindView(R.id.btn_google)
    FrameLayout btnGoogle;
    Unbinder unbinder;
    private FacebookLoginHelper facebookLoginHelper;
    private CallbackManager callbackManager;
    private GoogleHelper googleHelper;
    private String mSocialMediaPlatform = "";
    private String mFacebookSocialMediaID = "";
    private String mGoogleSocialMediaID = "";

    public static RegisterFragment newInstance() {
        Bundle args = new Bundle();

        RegisterFragment fragment = new RegisterFragment();
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
    public void onResume() {
        super.onResume();
        getDockActivity().lockDrawer();
    }

    @Override
    public void ResponseSuccess(Object result, String Tag, String message) {
        switch (Tag) {
            case WebServiceConstants.Register:
                LoginManager.getInstance().logOut();
                googleHelper.googleRevokeAccess();
                googleHelper.googleSignOut();
                prefHelper.setUserID(((UserIDEnt) result).getUser().getUserId() + "");
                UserIDEnt userID = (UserIDEnt) result;
                prefHelper.setUserName(userID.getUser().getName() + "");
                prefHelper.setCity(userID.getUser().getCity() + "");
                prefHelper.setPhoneNum(userID.getUser().getPhone() + "");
                prefHelper.setUserEmail(userID.getUser().getEmail() + "");
                TokenUpdater.getInstance().UpdateToken(getDockActivity(), prefHelper.getUserID(), AppConstants.Device_Type, FirebaseInstanceId.getInstance().getToken());
                getDockActivity().popBackStackTillEntry(0);
                getDockActivity().replaceDockableFragment(LoginFragment.newInstance(), "LoginFragment");

                break;
        }
    }

    @Override
    public void setTitleBar(TitleBar titleBar) {
        super.setTitleBar(titleBar);
        titleBar.hideButtons();
        titleBar.setSubHeading(getString(R.string.sign_up_head));
        titleBar.showBackButton();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signup, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupGoogleSignup();
        setupFacebookLogin();
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

    @OnClick({R.id.btn_signup, R.id.btn_facebook, R.id.btn_google})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_signup:
                if (isvalidated()) {
                    serviceHelper.enqueueCall(webService.makeUserSignup(edtName.getText().toString(),
                            edtEmail.getText().toString(),
                            edtPassword.getText().toString(),
                            edtComfirmPassword.getText().toString(),
                            mFacebookSocialMediaID,mGoogleSocialMediaID ), WebServiceConstants.Register);

                }
                break;
            case R.id.btn_facebook:
                LoginManager.getInstance().logOut();
                LoginManager.getInstance().logInWithReadPermissions(RegisterFragment.this, facebookLoginHelper.getPermissionNeeds());
                break;
            case R.id.btn_google:
                googleHelper.googleRevokeAccess();
                googleHelper.googleSignOut();
                googleHelper.intentGoogleSign();
                break;
        }
    }

    private boolean isvalidated() {
        if (edtName.getText() == null || edtName.getText().toString().isEmpty()) {
            if (edtName.requestFocus()) {
                getMainActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
            }
            edtName.setError(getString(R.string.enter_name));
            return false;
        } else if (edtEmail.getText() == null || (edtEmail.getText().toString().isEmpty()) ||
                !(Patterns.EMAIL_ADDRESS.matcher(edtEmail.getText().toString()).matches())) {
            if (edtEmail.requestFocus()) {
                getMainActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
            }
            edtEmail.setError(getString(R.string.enter_valid_email));
            return false;
        } else if (edtPassword.getText().toString().isEmpty()) {
            if (edtPassword.requestFocus()) {
                getMainActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
            }
            edtPassword.setError(getString(R.string.enter_password));
            return false;
        } else if (edtPassword.getText().toString().length() < 6) {
            if (edtPassword.requestFocus()) {
                getMainActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
            }
            edtPassword.setError(getString(R.string.passwordLength));
            return false;
        } else if (!edtComfirmPassword.getText().toString().equals(edtPassword.getText().toString())) {
            if (edtComfirmPassword.requestFocus()) {
                getMainActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
            }
            edtComfirmPassword.setError(getString(R.string.comfirm_password_error));
            return false;
        } else {
            return true;
        }
    }

    private void clearViews() {
        edtComfirmPassword.setText("");
        edtPassword.setText("");
        edtName.setText("");
        edtEmail.setText("");
    }

    @Override
    public void onGoogleSignInResult(GoogleSignInAccount result) {
        clearViews();
        edtName.setText(result.getDisplayName());
        edtEmail.setText(result.getEmail());
        mSocialMediaPlatform = WebServiceConstants.PLATFORM_GOOGLE;
        mGoogleSocialMediaID = result.getId();
    }

    @Override
    public void onSuccessfulFacebookLogin(FacebookLoginEnt LoginEnt) {
        clearViews();
        edtName.setText(LoginEnt.getFacebookFullName());
        edtEmail.setText(LoginEnt.getFacebookEmail() == null ? "" : LoginEnt.getFacebookEmail());
        mSocialMediaPlatform = WebServiceConstants.PLATFORM_FACEBOOK;
        mFacebookSocialMediaID = LoginEnt.getFacebookUID();
    }
}
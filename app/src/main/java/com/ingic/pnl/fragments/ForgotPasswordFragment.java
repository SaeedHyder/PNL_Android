package com.ingic.pnl.fragments;

import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;

import com.ingic.pnl.R;
import com.ingic.pnl.fragments.abstracts.BaseFragment;
import com.ingic.pnl.ui.views.AnyEditTextView;
import com.ingic.pnl.ui.views.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created on 10/23/2017.
 */
public class ForgotPasswordFragment extends BaseFragment {
    @BindView(R.id.edt_email)
    AnyEditTextView edtEmail;
    @BindView(R.id.btn_send)
    Button btnSend;
    Unbinder unbinder;

    public static ForgotPasswordFragment newInstance() {
        Bundle args = new Bundle();

        ForgotPasswordFragment fragment = new ForgotPasswordFragment();
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
        View view = inflater.inflate(R.layout.fragment_forgot_password, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getMainActivity().lockDrawer();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
    @Override
    public void onResume() {
        super.onResume();
        getDockActivity().lockDrawer();
    }
    @OnClick(R.id.btn_send)
    public void onViewClicked() {
        if (isvalidated()) {
            getDockActivity().popBackStackTillEntry(0);
            getDockActivity().replaceDockableFragment(LoginFragment.newInstance(), "LoginFragment");
        }
    }

    @Override
    public void setTitleBar(TitleBar titleBar) {
        super.setTitleBar(titleBar);
        titleBar.setSubHeading(getString(R.string.forgot_password));
    }

    private boolean isvalidated() {
        if (edtEmail.getText() == null || (edtEmail.getText().toString().isEmpty()) ||
                !(Patterns.EMAIL_ADDRESS.matcher(edtEmail.getText().toString()).matches())) {
            if (edtEmail.requestFocus()) {
                getMainActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
            }
            edtEmail.setError(getString(R.string.enter_valid_email));
            return false;
        } else {
            return true;
        }
    }
}
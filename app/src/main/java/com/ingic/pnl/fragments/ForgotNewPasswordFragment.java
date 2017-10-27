package com.ingic.pnl.fragments;

import com.ingic.pnl.R;
import com.ingic.pnl.fragments.abstracts.BaseFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created on 10/23/2017.
 */
public class ForgotNewPasswordFragment extends BaseFragment {
    public static ForgotNewPasswordFragment newInstance() {
        Bundle args = new Bundle();

        ForgotNewPasswordFragment fragment = new ForgotNewPasswordFragment();
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
        View view = inflater.inflate(R.layout.fragment_password_new, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
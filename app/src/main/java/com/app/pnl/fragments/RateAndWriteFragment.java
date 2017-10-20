package com.app.pnl.fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ToggleButton;

import com.app.pnl.R;
import com.app.pnl.fragments.abstracts.BaseFragment;
import com.app.pnl.ui.views.AnyEditTextView;
import com.app.pnl.ui.views.AnyTextView;
import com.app.pnl.ui.views.CustomRatingBar;
import com.app.pnl.ui.views.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.app.pnl.R.id.edit_write_review;

/**
 * Created by ahmedsyed on 10/19/2017.
 */
public class RateAndWriteFragment extends BaseFragment {
    @BindView(R.id.tv_show_name)
    AnyTextView tvShowName;
    @BindView(R.id.tb_rating)
    ToggleButton tbRating;
    @BindView(R.id.edt_name)
    AnyEditTextView edtName;
    @BindView(edit_write_review)
    AnyEditTextView editWriteReview;
    @BindView(R.id.tv_good)
    AnyTextView tvGood;
    @BindView(R.id.rb_review)
    CustomRatingBar rbReview;
    @BindView(R.id.btn_login)
    Button btnLogin;
    Unbinder unbinder;

    public static RateAndWriteFragment newInstance() {
        Bundle args = new Bundle();

        RateAndWriteFragment fragment = new RateAndWriteFragment();
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
        View view = inflater.inflate(R.layout.fragment_rate_and_review, container, false);
        unbinder = ButterKnife.bind(this, view);
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

    @OnClick(R.id.btn_login)
    public void onViewClicked() {
        if(validate()){
        getDockActivity().replaceDockableFragment(HomeFragment.newInstance(), "HomeFragment");
        }
    }

    @Override
    public void setTitleBar(TitleBar titleBar) {
        super.setTitleBar(titleBar);
        titleBar.hideButtons();
        titleBar.setSubHeading(getString(R.string.rate_review));
        titleBar.showBackButton();
    }

    private boolean validate() {
        return editWriteReview.testValidity();
    }

}

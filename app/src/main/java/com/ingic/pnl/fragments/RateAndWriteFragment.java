package com.ingic.pnl.fragments;

import android.media.Rating;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.ToggleButton;

import com.ingic.pnl.R;
import com.ingic.pnl.fragments.abstracts.BaseFragment;
import com.ingic.pnl.global.WebServiceConstants;
import com.ingic.pnl.helpers.UIHelper;
import com.ingic.pnl.ui.views.AnyEditTextView;
import com.ingic.pnl.ui.views.AnyTextView;
import com.ingic.pnl.ui.views.CustomRatingBar;
import com.ingic.pnl.ui.views.TitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.ingic.pnl.R.id.edit_write_review;

/**
 * Created by ahmedsyed on 10/19/2017.
 */
public class RateAndWriteFragment extends BaseFragment {
    private static String COMPANYIDKEY = "companyidkey";
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
    private int companyId;

    public static RateAndWriteFragment newInstance() {
        Bundle args = new Bundle();

        RateAndWriteFragment fragment = new RateAndWriteFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static RateAndWriteFragment newInstance(int companyId) {
        Bundle args = new Bundle();
        args.putInt(COMPANYIDKEY, companyId);
        RateAndWriteFragment fragment = new RateAndWriteFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            companyId = getArguments().getInt(COMPANYIDKEY);
        }

    }

    @Override
    public void ResponseSuccess(Object result, String Tag, String message) {
        switch (Tag) {
            case WebServiceConstants.CREATEREVIEW:
                UIHelper.showShortToastInCenter(getDockActivity(), message);
                getDockActivity().popBackStackTillEntry(0);
                getDockActivity().replaceDockableFragment(HomeFragment.newInstance(), "HomeFragment");
                loadingFinished();
                break;
        }
    }

    @Override
    public void setTitleBar(TitleBar titleBar) {
        super.setTitleBar(titleBar);
        titleBar.hideButtons();
        titleBar.setSubHeading(getString(R.string.rate_review));
        titleBar.showBackButton();
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
        if (!prefHelper.getUserName().equals("null")){
            edtName.setText(prefHelper.getUserName() + "");}
        
            RatingListners();


    }

    private void RatingListners() {
        rbReview.setOnScoreChanged(new CustomRatingBar.IRatingBarCallbacks() {
            @Override
            public void scoreChanged(float score) {
                if(score<1.0f)
                    rbReview.setScore(1.0f);

                if(score<=2){
                    tvGood.setText(R.string.poor);
                }
                else if(score>2 && score<=4){
                    tvGood.setText(R.string.good);
                }
                else if(score>4){
                    tvGood.setText(R.string.excellent);
                }
                else {
                    tvGood.setText(R.string.good);
                }
            }
        });


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btn_login)
    public void onViewClicked() {
        if (validate()) {
            loadingStarted();
            serviceHelper.enqueueCall(webService.createReview(
                    prefHelper.getUserID(),
                    companyId,
                    (int) (rbReview.getScore()),
                    editWriteReview.getText().toString(),
                    !tbRating.isChecked()),
                    WebServiceConstants.CREATEREVIEW);
            //
        }
    }

    private boolean validate() {
        return (editWriteReview.testValidity() && edtName.testValidity());
    }

}

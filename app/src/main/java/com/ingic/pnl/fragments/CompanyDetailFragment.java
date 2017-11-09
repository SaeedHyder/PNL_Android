package com.ingic.pnl.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

import com.ingic.pnl.R;
import com.ingic.pnl.entities.CompanyDetailEnt;
import com.ingic.pnl.entities.CompanyModel;
import com.ingic.pnl.fragments.abstracts.BaseFragment;
import com.ingic.pnl.global.WebServiceConstants;
import com.ingic.pnl.helpers.DialogHelper;
import com.ingic.pnl.helpers.UIHelper;
import com.ingic.pnl.ui.views.AnyTextView;
import com.ingic.pnl.ui.views.CustomRatingBar;
import com.ingic.pnl.ui.views.TitleBar;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class CompanyDetailFragment extends BaseFragment {
    private static String COMPANYIDKEY = "companyidkey";
    private static String COMPANYIDNAME = "COMPANYIDNAME";
    @BindView(R.id.iv_main)
    ImageView ivMain;
    @BindView(R.id.tv_heading_1)
    AnyTextView tvHeading1;
    @BindView(R.id.tv_company_detail)
    AnyTextView tvCompanyDetail;
    @BindView(R.id.tv_address_details)
    AnyTextView tvAddressDetails;
    @BindView(R.id.tv_mark_favourite)
    AnyTextView tvMarkFavourite;
    @BindView(R.id.tv_contact_details)
    AnyTextView tvContactDetails;
    @BindView(R.id.tv_share_favourite)
    AnyTextView tvShareFavourite;
    @BindView(R.id.tv_website)
    AnyTextView tvWebsite;
    @BindView(R.id.rb_review)
    CustomRatingBar rbReview;
    @BindView(R.id.tv_rating_text)
    AnyTextView tvRatingText;
    @BindView(R.id.tv_write_review)
    AnyTextView tvWriteReview;
    @BindView(R.id.tv_heading_2)
    AnyTextView tvHeading2;
    @BindView(R.id.tv_name)
    AnyTextView tvName;
    @BindView(R.id.rb_review_2)
    CustomRatingBar rbReview2;
    @BindView(R.id.tv_msg_notification)
    AnyTextView tvMsgNotification;
    @BindView(R.id.tv_all_posts)
    AnyTextView tvAllPosts;
    @BindView(R.id.ll_all_posts)
    LinearLayout llAllPosts;
    @BindView(R.id.review_layout)
    LinearLayout llReviewLayout;
    @BindView(R.id.txt_no_data)
    AnyTextView NoReviewTxt;
    @BindView(R.id.mainFrame)
    LinearLayout mainFrame;
    Unbinder unbinder;
    @BindView(R.id.toggleFavourite)
    ToggleButton toggleFavourite;
    private int companyId;
    private String companyName = "Travel Company";
    private CompanyDetailEnt companyDetailEnt;
    private ImageLoader imageLoader;
    private String websiteURL;
    private String latitude;
    private String longitude;
    private String phoneNumber;
    private boolean isFavorite;

    public static CompanyDetailFragment newInstance() {
        Bundle args = new Bundle();

        CompanyDetailFragment fragment = new CompanyDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static CompanyDetailFragment newInstance(int companyId, String companyName) {
        Bundle args = new Bundle();
        args.putInt(COMPANYIDKEY, companyId);
        args.putString(COMPANYIDNAME, companyName);
        CompanyDetailFragment fragment = new CompanyDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            companyId = getArguments().getInt(COMPANYIDKEY);
            companyName = getArguments().getString(COMPANYIDNAME);
        }
        imageLoader = ImageLoader.getInstance();

    }

    @Override
    public void ResponseSuccess(Object result, String Tag, String message) {
        switch (Tag) {
            case WebServiceConstants.COMPANYDETAIL:
                mainFrame.setVisibility(View.VISIBLE);
                companyDetailEnt = (CompanyDetailEnt) result;
                setComapanyDetail(companyDetailEnt);
                websiteURL = companyDetailEnt.getCompanyModel().getWebUrl();
                latitude = String.valueOf(companyDetailEnt.getCompanyModel().getLatitude() + "");
                longitude = String.valueOf(companyDetailEnt.getCompanyModel().getLongitude() + "");
                phoneNumber = String.valueOf(companyDetailEnt.getCompanyModel().getPhone() + "");
                isFavorite = companyDetailEnt.getCompanyModel().getIsMarkedFavorite();
                toggleFavourite.setChecked(isFavorite);
                break;

            case WebServiceConstants.MARKFAVORITE:
                UIHelper.showShortToastInCenter(getDockActivity(), message);
                break;

        }
    }

    @Override
    public void setTitleBar(TitleBar titleBar) {
        super.setTitleBar(titleBar);
        titleBar.hideButtons();
        titleBar.setSubHeading(companyName);
        titleBar.showBackButton();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_company_detail, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mainFrame.setVisibility(View.GONE);
        serviceHelper.enqueueCall(webService.getCompanyDetail(companyId), WebServiceConstants.COMPANYDETAIL);
        listners();


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void listners() {

        toggleFavourite.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                CompanyModel companyModel = new CompanyModel();
                companyModel.setIsMarkedFavorite(isChecked);

                serviceHelper.enqueueCall(webService.markFavorite(prefHelper.getUserID(), companyId, isChecked), WebServiceConstants.MARKFAVORITE);

            }
        });
    }

    private void setComapanyDetail(CompanyDetailEnt companyDetailEnt) {
        if (companyDetailEnt != null) {
            tvCompanyDetail.setText(companyDetailEnt.getCompanyModel().getDescription() + " ");
            tvHeading1.setText(companyDetailEnt.getCompanyModel().getName() + "");
            rbReview.setScore(companyDetailEnt.getCompanyModel().getRating());
            tvRatingText.setText(companyDetailEnt.getCompanyModel().getRating() + "");
            imageLoader.displayImage(companyDetailEnt.getCompanyModel().getImageUrl(), ivMain);

            //Review
            if (companyDetailEnt.getReviewModel() != null) {
                NoReviewTxt.setVisibility(View.GONE);
                llReviewLayout.setVisibility(View.VISIBLE);
                if (companyDetailEnt.getReviewModel().getUserName() != null)
                    tvName.setText(companyDetailEnt.getReviewModel().getUserName() + " ");
                tvMsgNotification.setText(companyDetailEnt.getReviewModel().getAnalysis() + "");
                rbReview2.setScore(companyDetailEnt.getReviewModel().getPoints());
            } else {
                NoReviewTxt.setVisibility(View.VISIBLE);
                llReviewLayout.setVisibility(View.GONE);
            }
        }
    }

    private void ShareMyCompany(String message) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.setType("text/plain");
        sendIntent.putExtra(Intent.EXTRA_TEXT, message);


        if (sendIntent.resolveActivity(getMainActivity().getPackageManager()) != null)
            startActivity(Intent.createChooser(sendIntent, getString(R.string.share_via)));
//        startActivity(sendIntent);
    }

    @OnClick({R.id.tv_address_details, R.id.tv_mark_favourite, R.id.tv_contact_details, R.id.tv_share_favourite, R.id.tv_website, R.id.tv_write_review, R.id.ll_all_posts})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_address_details:
                openMapDialoge();
                break;
            case R.id.tv_mark_favourite:
                toggleFavourite.setChecked(!toggleFavourite.isChecked());
                break;
            case R.id.tv_contact_details:
                openDialerIntent();
                break;
            case R.id.tv_share_favourite:
                ShareMyCompany(tvCompanyDetail.getText().toString() + " " + websiteURL);
                break;
            case R.id.tv_website:
                openWebsite();
                break;
            case R.id.tv_write_review:
                getDockActivity().replaceDockableFragment(RateAndWriteFragment.newInstance(companyId), "RateAndWriteFragment");
                break;
            case R.id.ll_all_posts:
                getDockActivity().replaceDockableFragment(ReviewsFragment.newInstance(companyId), "ReviewsFragment");
                break;
        }
    }

    private void openDialerIntent() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        startActivity(intent);
    }

    private void openMapDialoge() {
        final DialogHelper dialog = new DialogHelper(getDockActivity());
        dialog.mapDialoge(R.layout.map_dialoge, getDockActivity(), latitude, longitude);
        dialog.showDialog();
    }

    private void openWebsite() {
        Uri uriUrl = Uri.parse(websiteURL);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }
}

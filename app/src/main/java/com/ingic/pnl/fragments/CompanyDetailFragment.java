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
    Unbinder unbinder;

    private static String COMPANYIDKEY = "companyidkey";
    @BindView(R.id.toggleFavourite)
    ToggleButton toggleFavourite;
    private int companyId;
    private CompanyDetailEnt companyDetailEnt;
    private ImageLoader imageLoader;
    private String websiteURL;

    public static CompanyDetailFragment newInstance() {
        Bundle args = new Bundle();

        CompanyDetailFragment fragment = new CompanyDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static CompanyDetailFragment newInstance(int companyId) {
        Bundle args = new Bundle();
        args.putInt(COMPANYIDKEY, companyId);
        CompanyDetailFragment fragment = new CompanyDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            companyId = getArguments().getInt(COMPANYIDKEY);
        }
        imageLoader = ImageLoader.getInstance();

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
        serviceHelper.enqueueCall(webService.getCompanyDetail(companyId), WebServiceConstants.COMPANYDETAIL);
        listners();

    }

    private void listners() {

        toggleFavourite.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                serviceHelper.enqueueCall(webService.markFavorite(prefHelper.getUserID(), companyId, isChecked), WebServiceConstants.MARKFAVORITE);

            }
        });
    }

    @Override
    public void ResponseSuccess(Object result, String Tag, String message) {
        switch (Tag) {
            case WebServiceConstants.COMPANYDETAIL:
                companyDetailEnt = (CompanyDetailEnt) result;
                setComapanyDetail(companyDetailEnt);
                websiteURL=companyDetailEnt.getCompanyModel().getWebUrl();
                break;

            case WebServiceConstants.MARKFAVORITE:
                UIHelper.showShortToastInCenter(getDockActivity(), message);
                break;

        }
    }

    private void setComapanyDetail(CompanyDetailEnt companyDetailEnt) {
        tvCompanyDetail.setText(companyDetailEnt.getCompanyModel().getDescription() + " ");
        tvHeading1.setText(companyDetailEnt.getCompanyModel().getName() + "");
        //  imageLoader.displayImage(companyDetailEnt.getCompanyModel().getImageUrl(),ivMain);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.tv_address_details, R.id.tv_mark_favourite, R.id.tv_contact_details, R.id.tv_share_favourite, R.id.tv_website, R.id.tv_write_review, R.id.ll_all_posts})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_address_details:
               // UIHelper.showShortToastInCenter(getDockActivity(), getString(R.string.beta));
                openMapDialoge();
                break;
            case R.id.tv_mark_favourite:
                //  UIHelper.showShortToastInCenter(getDockActivity(), getString(R.string.beta));
              if(toggleFavourite.isChecked()){
                    toggleFavourite.setChecked(false);
                  serviceHelper.enqueueCall(webService.markFavorite(prefHelper.getUserID(), companyId, true), WebServiceConstants.MARKFAVORITE);
              }
              else{
                  toggleFavourite.setChecked(true);
                  serviceHelper.enqueueCall(webService.markFavorite(prefHelper.getUserID(), companyId, false), WebServiceConstants.MARKFAVORITE);
              }
             break;
            case R.id.tv_contact_details:
                UIHelper.showShortToastInCenter(getDockActivity(), getString(R.string.beta));
                break;
            case R.id.tv_share_favourite:
                UIHelper.showShortToastInCenter(getDockActivity(), getString(R.string.beta));
                break;
            case R.id.tv_website:
               // UIHelper.showShortToastInCenter(getDockActivity(), getString(R.string.beta));
                openWebsite();
                break;
            case R.id.tv_write_review:
                getDockActivity().replaceDockableFragment(RateAndWriteFragment.newInstance(companyId), "RateAndWriteFragment");
                //write review fragment
                break;
            case R.id.ll_all_posts:
                getDockActivity().replaceDockableFragment(ReviewsFragment.newInstance(companyId), "ReviewsFragment");
                //reviews listing fragment
                break;
        }
    }

    private void openMapDialoge() {
        final DialogHelper dialog = new DialogHelper(getDockActivity());
        dialog.mapDialoge(R.layout.map_dialoge,getDockActivity());
        dialog.showDialog();
    }

    private void openWebsite() {
        //Uri uriUrl = Uri.parse(websiteURL);
        Uri uriUrl = Uri.parse("http://www.google.com");
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }

    @Override
    public void setTitleBar(TitleBar titleBar) {
        super.setTitleBar(titleBar);
        titleBar.hideButtons();
        titleBar.setSubHeading(getString(R.string.travel));
        titleBar.showBackButton();
    }
}

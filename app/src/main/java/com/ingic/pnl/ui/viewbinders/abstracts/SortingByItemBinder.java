package com.ingic.pnl.ui.viewbinders.abstracts;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;

import com.ingic.pnl.R;
import com.ingic.pnl.activities.DockActivity;
import com.ingic.pnl.entities.SortingByEnt;
import com.ingic.pnl.helpers.BasePreferenceHelper;
import com.ingic.pnl.ui.views.AnyTextView;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by saeedhyder on 10/19/2017.
 */

public class SortingByItemBinder extends ViewBinder<SortingByEnt> {

    private DockActivity dockActivity;
    private BasePreferenceHelper prefHelper;
    private ImageLoader imageLoader;


    public SortingByItemBinder(DockActivity dockActivity, BasePreferenceHelper prefHelper) {
        super(R.layout.row_item_sorting_by);
        this.dockActivity = dockActivity;
        this.prefHelper = prefHelper;
    }

    @Override
    public BaseViewHolder createViewHolder(View view) {

        return new ViewHolder(view);
    }


    @Override
    public void bindView(SortingByEnt entity, int position, int grpPosition, View view, Activity activity) {

        final ViewHolder viewHolder = (ViewHolder) view.getTag();
        imageLoader = ImageLoader.getInstance();
    }

    static class ViewHolder extends BaseViewHolder {
        @BindView(R.id.iv_main)
        ImageView ivMain;
        @BindView(R.id.tv_company_name)
        AnyTextView tvCompanyName;
        @BindView(R.id.tv_company_description)
        AnyTextView tvCompanyDescription;
        @BindView(R.id.tv_location)
        AnyTextView tvLocation;
        @BindView(R.id.tv_phone)
        AnyTextView tvPhone;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

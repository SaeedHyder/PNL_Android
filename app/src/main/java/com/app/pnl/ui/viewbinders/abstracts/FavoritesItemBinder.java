package com.app.pnl.ui.viewbinders.abstracts;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;

import com.app.pnl.R;
import com.app.pnl.activities.DockActivity;
import com.app.pnl.entities.FavoritesEnt;
import com.app.pnl.helpers.BasePreferenceHelper;
import com.app.pnl.ui.views.AnyTextView;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by saeedhyder on 10/19/2017.
 */

public class FavoritesItemBinder extends ViewBinder<FavoritesEnt> {

    private DockActivity dockActivity;
    private BasePreferenceHelper prefHelper;
    private ImageLoader imageLoader;


    public FavoritesItemBinder(DockActivity dockActivity, BasePreferenceHelper prefHelper) {
        super(R.layout.items_favourite);
        this.dockActivity = dockActivity;
        this.prefHelper = prefHelper;
    }

    @Override
    public BaseViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    public void bindView(FavoritesEnt entity, int position, int grpPosition, View view, Activity activity) {


        final FavoritesItemBinder.ViewHolder viewHolder = (FavoritesItemBinder.ViewHolder) view.getTag();
        imageLoader = ImageLoader.getInstance();

    }


    static class ViewHolder extends BaseViewHolder{
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

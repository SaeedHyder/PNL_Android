package com.ingic.pnl.ui.viewbinders.abstracts;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;

import com.ingic.pnl.R;
import com.ingic.pnl.activities.DockActivity;
import com.ingic.pnl.entities.FavoritesEnt;
import com.ingic.pnl.helpers.BasePreferenceHelper;
import com.ingic.pnl.ui.views.AnyTextView;
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

        viewHolder.tvCompanyName.setText(entity.getName()+"");
        viewHolder.tvCompanyDescription.setText(entity.getDescription()+"");
        viewHolder.tvLocation.setText(entity.getAddress()+"");
        viewHolder.tvPhone.setText(entity.getPhone()+"");
        imageLoader.displayImage(entity.getImageUrl(),viewHolder.ivMain);

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

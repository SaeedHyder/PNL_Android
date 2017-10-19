package com.app.pnl.ui.viewbinders.abstracts;

import android.app.Activity;
import android.view.View;

import com.app.pnl.R;
import com.app.pnl.activities.DockActivity;
import com.app.pnl.entities.ViewAllServicesEnt;
import com.app.pnl.helpers.BasePreferenceHelper;
import com.app.pnl.ui.views.AnyTextView;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by saeedhyder on 10/19/2017.
 */

public class ViewAllServicesItemBinder extends ViewBinder<ViewAllServicesEnt> {

    private DockActivity dockActivity;
    private BasePreferenceHelper prefHelper;
    private ImageLoader imageLoader;


    public ViewAllServicesItemBinder(DockActivity dockActivity, BasePreferenceHelper prefHelper) {
        super(R.layout.row_item_viewall_services);
        this.dockActivity = dockActivity;
        this.prefHelper = prefHelper;
    }

    @Override
    public BaseViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    public void bindView(ViewAllServicesEnt entity, int position, int grpPosition, View view, Activity activity) {

        final ViewHolder viewHolder = (ViewHolder) view.getTag();
        imageLoader = ImageLoader.getInstance();

        viewHolder.companiesSorting.setText(entity.getCompaniesSorting() + "");
    }


    static class ViewHolder extends BaseViewHolder{
        @BindView(R.id.companies_sorting)
        AnyTextView companiesSorting;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

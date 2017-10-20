package com.app.pnl.ui.viewbinders;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;

import com.app.pnl.R;
import com.app.pnl.activities.DockActivity;
import com.app.pnl.entities.servicesGridViewEnt;
import com.app.pnl.helpers.BasePreferenceHelper;
import com.app.pnl.ui.viewbinders.abstracts.ViewBinder;
import com.app.pnl.ui.views.AnyTextView;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by saeedhyder on 10/20/2017.
 */

public class ServicesGridViewItemBinder extends ViewBinder<servicesGridViewEnt> {

    private DockActivity dockActivity;
    private BasePreferenceHelper prefHelper;
    private ImageLoader imageLoader;


    public ServicesGridViewItemBinder(DockActivity dockActivity, BasePreferenceHelper prefHelper) {
        super(R.layout.row_item_services);
        this.dockActivity = dockActivity;
        this.prefHelper = prefHelper;
    }

    @Override
    public BaseViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    public void bindView(servicesGridViewEnt entity, int position, int grpPosition, View view, Activity activity) {

        final ViewHolder viewHolder = (ViewHolder) view.getTag();
        imageLoader = ImageLoader.getInstance();
        imageLoader.displayImage(entity.getImage(),viewHolder.imgService);
        viewHolder.txtService.setText(entity.getText()+"");
    }

    static class ViewHolder  extends BaseViewHolder{
        @BindView(R.id.img_service)
        ImageView imgService;
        @BindView(R.id.txt_service)
        AnyTextView txtService;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

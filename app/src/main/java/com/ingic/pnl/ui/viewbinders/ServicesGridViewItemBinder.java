package com.ingic.pnl.ui.viewbinders;

import android.app.Activity;
import android.graphics.Point;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ingic.pnl.R;
import com.ingic.pnl.activities.DockActivity;
import com.ingic.pnl.activities.MainActivity;
import com.ingic.pnl.entities.ServiceEnt;
import com.ingic.pnl.helpers.BasePreferenceHelper;
import com.ingic.pnl.ui.viewbinders.abstracts.ViewBinder;
import com.ingic.pnl.ui.views.AnyTextView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by saeedhyder on 10/20/2017.
 */

public class ServicesGridViewItemBinder extends ViewBinder<ServiceEnt> {

    private DockActivity dockActivity;
    private BasePreferenceHelper prefHelper;
    private ImageLoader imageLoader;
    private Picasso picasso;


    public ServicesGridViewItemBinder(DockActivity dockActivity, BasePreferenceHelper prefHelper) {
        super(R.layout.row_item_services);
        this.dockActivity = dockActivity;
        this.prefHelper = prefHelper;
        imageLoader = ImageLoader.getInstance();
        picasso = Picasso.with(dockActivity);
    }

    @Override
    public BaseViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    public void bindView(ServiceEnt entity, int position, int grpPosition, View view, Activity activity) {
        LinearLayout gridView = (LinearLayout) view;
        gridView.setLayoutParams(new GridView.LayoutParams(GridView.LayoutParams.MATCH_PARENT, getScreenHeight(activity) / 4));
        final ViewHolder viewHolder = (ViewHolder) view.getTag();
        viewHolder.txtService.setText(entity.getName() + "");
        picasso.load(entity.getImageUrl()).into(viewHolder.imgService);
//        imageLoader.displayImage(entity.getImageUrl(), viewHolder.imgService);
    }

    private int getScreenHeight(Activity activity) {
        Point size = new Point();
        activity.getWindowManager().getDefaultDisplay().getSize(size);
        int screenHeight = size.y;
        screenHeight = screenHeight - (int) activity.getResources().getDimension(R.dimen.x100) - (int)
                (((MainActivity) activity).titleBar.getHeight());
        return screenHeight;
    }

    static class ViewHolder extends BaseViewHolder {
        @BindView(R.id.img_service)
        ImageView imgService;
        @BindView(R.id.txt_service)
        AnyTextView txtService;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

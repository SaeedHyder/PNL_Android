package com.ingic.pnl.ui.viewbinders;

import android.app.Activity;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ingic.pnl.R;
import com.ingic.pnl.activities.DockActivity;
import com.ingic.pnl.activities.MainActivity;
import com.ingic.pnl.entities.servicesGridViewEnt;
import com.ingic.pnl.helpers.BasePreferenceHelper;
import com.ingic.pnl.ui.viewbinders.abstracts.ViewBinder;
import com.ingic.pnl.ui.views.AnyTextView;
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
        LinearLayout gridView = (LinearLayout) view;
        int screenHeight = activity.getWindowManager()
                .getDefaultDisplay().getHeight();
        screenHeight = screenHeight - (int) activity.getResources().getDimension(R.dimen.x100) - (int)
                (((MainActivity) activity).titleBar.getHeight());
        // gridView.setMinimumHeight(screenHeight / 4);
        gridView.setLayoutParams(new GridView.LayoutParams(GridView.LayoutParams.MATCH_PARENT, screenHeight / 4));
        final ViewHolder viewHolder = (ViewHolder) view.getTag();
        viewHolder.imgService.setImageResource(entity.getImageint());
        /*imageLoader = ImageLoader.getInstance();
        imageLoader.displayImage(entity.getImage(), viewHolder.imgService);*/
        viewHolder.txtService.setText(entity.getText() + "");
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

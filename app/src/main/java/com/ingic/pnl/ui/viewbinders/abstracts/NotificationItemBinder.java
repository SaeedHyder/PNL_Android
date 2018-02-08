package com.ingic.pnl.ui.viewbinders.abstracts;

import android.app.Activity;
import android.view.View;
import android.widget.LinearLayout;

import com.ingic.pnl.R;
import com.ingic.pnl.activities.DockActivity;
import com.ingic.pnl.entities.NotificationEnt;
import com.ingic.pnl.entities.NotificationItemEnt;
import com.ingic.pnl.helpers.BasePreferenceHelper;
import com.ingic.pnl.ui.views.AnyTextView;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by saeedhyder on 10/19/2017.
 */

public class NotificationItemBinder extends ViewBinder<NotificationItemEnt> {

    private DockActivity dockActivity;
    private BasePreferenceHelper prefHelper;
    private ImageLoader imageLoader;


    public NotificationItemBinder(DockActivity dockActivity, BasePreferenceHelper prefHelper) {
        super(R.layout.items_notifications);
        this.dockActivity = dockActivity;
        this.prefHelper = prefHelper;
    }

    @Override
    public BaseViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    public void bindView(NotificationItemEnt entity, int position, int grpPosition, View view, Activity activity) {

        final ViewHolder viewHolder = (ViewHolder) view.getTag();
        imageLoader = ImageLoader.getInstance();
        if (!entity.getIsRead()){
            viewHolder.mainParent.setAlpha(1f);
        }
        viewHolder.tvName.setText(entity.getTitle()+" ");
        viewHolder.tvMsgNotification.setText(entity.getMessage()+" ");
        viewHolder.tvDateTime.setText(entity.getCreatedOn()+" ");

    }


    static class ViewHolder extends BaseViewHolder {
        @BindView(R.id.tv_name)
        AnyTextView tvName;
        @BindView(R.id.tv_msg_notification)
        AnyTextView tvMsgNotification;
        @BindView(R.id.tv_date_time)
        AnyTextView tvDateTime;
        @BindView(R.id.main_parent)
        LinearLayout mainParent;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

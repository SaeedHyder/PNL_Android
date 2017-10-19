package com.app.pnl.ui.viewbinders.abstracts;

import android.app.Activity;
import android.view.View;

import com.app.pnl.R;
import com.app.pnl.activities.DockActivity;
import com.app.pnl.entities.NotificationEnt;
import com.app.pnl.helpers.BasePreferenceHelper;
import com.app.pnl.ui.views.AnyTextView;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by saeedhyder on 10/19/2017.
 */

public class NotificationItemBinder extends ViewBinder<NotificationEnt> {

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
    public void bindView(NotificationEnt entity, int position, int grpPosition, View view, Activity activity) {

        final ViewHolder viewHolder = (ViewHolder) view.getTag();
        imageLoader = ImageLoader.getInstance();

        viewHolder.tvName.setText(entity.getTitle()+" ");
        viewHolder.tvMsgNotification.setText(entity.getDescription()+" ");
        viewHolder.tvDateTime.setText(entity.getTime()+" ");

    }


    static class ViewHolder extends BaseViewHolder {
        @BindView(R.id.tv_name)
        AnyTextView tvName;
        @BindView(R.id.tv_msg_notification)
        AnyTextView tvMsgNotification;
        @BindView(R.id.tv_date_time)
        AnyTextView tvDateTime;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

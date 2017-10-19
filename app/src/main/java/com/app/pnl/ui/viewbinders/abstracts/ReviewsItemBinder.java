package com.app.pnl.ui.viewbinders.abstracts;

import android.app.Activity;
import android.view.View;

import com.app.pnl.R;
import com.app.pnl.activities.DockActivity;
import com.app.pnl.entities.ReviewsEnt;
import com.app.pnl.helpers.BasePreferenceHelper;
import com.app.pnl.ui.views.AnyTextView;
import com.app.pnl.ui.views.CustomRatingBar;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by saeedhyder on 10/19/2017.
 */

public class ReviewsItemBinder extends ViewBinder<ReviewsEnt> {

    private DockActivity dockActivity;
    private BasePreferenceHelper prefHelper;
    private ImageLoader imageLoader;


    public ReviewsItemBinder(DockActivity dockActivity, BasePreferenceHelper prefHelper) {
        super(R.layout.items_reviews);
        this.dockActivity = dockActivity;
        this.prefHelper = prefHelper;
    }

    @Override
    public BaseViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    public void bindView(ReviewsEnt entity, int position, int grpPosition, View view, Activity activity) {

        final ViewHolder viewHolder = (ViewHolder) view.getTag();
        imageLoader = ImageLoader.getInstance();

        viewHolder.tvName.setText(entity.getTitle()+"");
        viewHolder.tvMsgNotification.setText(entity.getDescription()+"");
        viewHolder.rbReview.setScore(entity.getRating());

    }

    static class ViewHolder extends BaseViewHolder {
        @BindView(R.id.tv_name)
        AnyTextView tvName;
        @BindView(R.id.rb_review)
        CustomRatingBar rbReview;
        @BindView(R.id.tv_msg_notification)
        AnyTextView tvMsgNotification;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

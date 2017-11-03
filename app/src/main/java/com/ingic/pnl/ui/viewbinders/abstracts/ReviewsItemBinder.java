package com.ingic.pnl.ui.viewbinders.abstracts;

import android.app.Activity;
import android.view.View;

import com.ingic.pnl.R;
import com.ingic.pnl.activities.DockActivity;
import com.ingic.pnl.entities.ReviewsEnt;
import com.ingic.pnl.helpers.BasePreferenceHelper;
import com.ingic.pnl.ui.views.AnyTextView;
import com.ingic.pnl.ui.views.CustomRatingBar;
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

        viewHolder.tvName.setText(entity.getUserName()+"");
        viewHolder.tvMsgNotification.setText(entity.getAnalysis()+"");
        viewHolder.rbReview.setScore(4);

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

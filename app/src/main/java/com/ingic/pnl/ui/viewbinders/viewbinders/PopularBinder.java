package com.ingic.pnl.ui.viewbinders.viewbinders;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.ingic.pnl.R;
import com.ingic.pnl.entities.PopularEnt;
import com.ingic.pnl.interfaces.RecyclerViewItemListener;
import com.ingic.pnl.ui.viewbinders.abstracts.RecyclerViewBinder;
import com.ingic.pnl.ui.views.AnyTextView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created on 10/19/2017.
 */

public class PopularBinder extends RecyclerViewBinder<PopularEnt> {
    private RecyclerViewItemListener listener;
    private ImageLoader imageLoader;

    public PopularBinder(RecyclerViewItemListener viewItemListener) {
        super(R.layout.items_favourite);
        this.listener = viewItemListener;
    }

    @Override
    public BaseViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    public void bindView(final PopularEnt entity, final int position, Object viewHolder, Context context) {
        ViewHolder holder = (ViewHolder) viewHolder;
        imageLoader=ImageLoader.getInstance();
     //   Picasso.with(context).load(entity.getImageUrl()).into(holder.ivMain);
       // holder.ivMain.setImageResource(entity.getImage());
        imageLoader.displayImage(entity.getImageUrl(),holder.ivMain);
        holder.tvCompanyDescription.setText(entity.getDescription());
        holder.tvCompanyName.setText(entity.getName());
        holder.tvLocation.setText(entity.getAddress());
        holder.tvPhone.setText(entity.getPhone());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onRecyclerItemClicked(entity,position);
            }
        });
    }

    static class ViewHolder extends BaseViewHolder {
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
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}

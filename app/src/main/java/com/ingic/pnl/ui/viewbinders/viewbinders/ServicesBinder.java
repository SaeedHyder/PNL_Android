package com.ingic.pnl.ui.viewbinders.viewbinders;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.ingic.pnl.R;
import com.ingic.pnl.entities.ServiceEnt;
import com.ingic.pnl.interfaces.RecyclerViewItemListener;
import com.ingic.pnl.ui.viewbinders.abstracts.RecyclerViewBinder;
import com.ingic.pnl.ui.views.AnyTextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created on 10/19/2017.
 */

public class ServicesBinder extends RecyclerViewBinder<ServiceEnt> {
    private final RecyclerViewItemListener listener;

    public ServicesBinder(RecyclerViewItemListener listener) {
        super(R.layout.row_item_services);
        this.listener = listener;
    }

    @Override
    public BaseViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    public void bindView(final ServiceEnt entity, final int position, Object viewHolder, Context context) {
        ViewHolder holder = (ViewHolder) viewHolder;
       // holder.imgService.setImageResource(entity.getImageResID());
        holder.txtService.setText(entity.getName() + "");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onRecyclerItemClicked(entity, position);
                }
            }
        });
    }

     static class ViewHolder extends BaseViewHolder {
        @BindView(R.id.img_service)
        ImageView imgService;
        @BindView(R.id.txt_service)
        AnyTextView txtService;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}

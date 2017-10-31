package com.ingic.pnl.helpers;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import com.ingic.pnl.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.squareup.picasso.Picasso;


/**
 * Created on 5/24/2017.
 */

public class DialogHelper {
    private Dialog dialog;
    private Context context;
    private ImageLoader imageLoader;

    public DialogHelper(Context context) {
        this.context = context;
        this.dialog = new Dialog(context);
    }


    public Dialog initlogout(int layoutID, View.OnClickListener onokclicklistener, View.OnClickListener oncancelclicklistener) {
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        this.dialog.setContentView(layoutID);
        Button okbutton = (Button) dialog.findViewById(R.id.btn_yes);
        okbutton.setOnClickListener(onokclicklistener);
        Button cancelbutton = (Button) dialog.findViewById(R.id.btn_No);
        cancelbutton.setOnClickListener(oncancelclicklistener);
        return this.dialog;
    }

    public Dialog mapDialoge(int layoutID,Context contextt) {
        imageLoader=ImageLoader.getInstance();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        this.dialog.setContentView(layoutID);
        ImageView mapImage=(ImageView)dialog.findViewById(R.id.mapView);
        String mapURL="https://maps.googleapis.com/maps/api/staticmap?center=40.714728,-73.998672&zoom=14&" +
                "&scale=2&size=500x300&maptype=roadmap&markers=color:blue|40.714728,-73.998672&key=AIzaSyCDylplefNyWlLDoBL_n2VFjwlMWvq3sBg";
        imageLoader.displayImage(mapURL,mapImage);
        return this.dialog;
    }


    public void showDialog() {

        dialog.show();
    }

    public void setCancelable(boolean isCancelable) {
        dialog.setCancelable(isCancelable);
        dialog.setCanceledOnTouchOutside(isCancelable);
    }

    public void hideDialog() {
        dialog.dismiss();
    }
}

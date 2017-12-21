package com.ingic.pnl.helpers;

import android.util.Log;

import com.ingic.pnl.R;
import com.ingic.pnl.activities.DockActivity;
import com.ingic.pnl.entities.ResponseWrapper;
import com.ingic.pnl.global.WebServiceConstants;
import com.ingic.pnl.interfaces.webServiceResponseLisener;
import com.ingic.pnl.retrofit.WebService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created on 7/17/2017.
 */

public class ServiceHelper<T> {
    private webServiceResponseLisener serviceResponseLisener;
    private DockActivity context;
    private WebService webService;

    public ServiceHelper(webServiceResponseLisener serviceResponseLisener, DockActivity conttext, WebService webService) {
        this.serviceResponseLisener = serviceResponseLisener;
        this.context = conttext;
        this.webService = webService;
    }

    public void enqueueCall(Call<ResponseWrapper<T>> call, final String tag) {
        if (InternetHelper.CheckInternetConectivityandShowToast(context)) {
            context.onLoadingStarted();
            call.enqueue(new Callback<ResponseWrapper<T>>() {
                @Override
                public void onResponse(Call<ResponseWrapper<T>> call, Response<ResponseWrapper<T>> response) {
                    context.onLoadingFinished();
                    if (response.body() != null) {
                        if (response.body().getResponse().equals(WebServiceConstants.SUCCESS_RESPONSE_CODE)) {
                            serviceResponseLisener.ResponseSuccess(response.body().getResult(), tag, response.body().getMessage());
                        } else {
                            if ((response.body().getMessage() + "").contains("found"))
                                serviceResponseLisener.ResponseFailure(tag);
                            else if (response.body().getMessage().contains("Invalid")) {
                                UIHelper.showShortToastInCenter(context, context.getResources().getString(R.string.user_not_exist_error));
                            } else
                                UIHelper.showShortToastInCenter(context, response.body().getMessage() + "");
                        }
                    } else {
                        UIHelper.showShortToastInCenter(context, context.getResources().getString(R.string.server_response_error));
                    }

                }

                @Override
                public void onFailure(Call<ResponseWrapper<T>> call, Throwable t) {
                    context.onLoadingFinished();
                    t.printStackTrace();
                    Log.e(ServiceHelper.class.getSimpleName() + " by tag: " + tag, t.toString());
                }
            });
        }
    }

}

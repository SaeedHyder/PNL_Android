package com.ingic.pnl.helpers;

import android.content.Context;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessaging;
import com.ingic.pnl.entities.ResponseWrapper;
import com.ingic.pnl.global.AppConstants;
import com.ingic.pnl.global.WebServiceConstants;
import com.ingic.pnl.retrofit.WebService;
import com.ingic.pnl.retrofit.WebServiceFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created on 5/15/2017.
 */

public class TokenUpdater {
    private static final TokenUpdater tokenUpdater = new TokenUpdater();
    private WebService webservice;

    private TokenUpdater() {
    }

    public static TokenUpdater getInstance() {
        return tokenUpdater;
    }

    public void UpdateToken(Context context, final String userid, String DeviceType, String Token) {
        if (Token.isEmpty()) {
            Log.e("Token Updater", "Token is Empty");
        }
        webservice = WebServiceFactory.getWebServiceInstanceWithCustomInterceptor(context,
                WebServiceConstants.SERVICE_URL);

        Call<ResponseWrapper> call = webservice.updateToken(userid, Token, DeviceType);
        call.enqueue(new Callback<ResponseWrapper>() {
            @Override
            public void onResponse(Call<ResponseWrapper> call, Response<ResponseWrapper> response) {
                if (response.body() != null)
                    FirebaseMessaging.getInstance().subscribeToTopic(AppConstants.FIREBASE_TOPIC);
                Log.i("UPDATETOKEN", response.body().getResponse() + "" + userid);
            }

            @Override
            public void onFailure(Call<ResponseWrapper> call, Throwable t) {
                Log.e("UPDATETOKEN", t.toString());
            }
        });
    }

}

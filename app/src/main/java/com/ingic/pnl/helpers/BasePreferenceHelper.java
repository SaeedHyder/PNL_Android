package com.ingic.pnl.helpers;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;


public class BasePreferenceHelper extends PreferenceHelper {

    protected static final String KEY_LOGIN_STATUS = "islogin";
    private static final String FILENAME = "preferences";
    private static final String KEY_USERID = "USERID";
    private Context context;


    public BasePreferenceHelper(Context c) {
        this.context = c;
    }

    public SharedPreferences getSharedPreferences() {
        return context.getSharedPreferences(FILENAME, Activity.MODE_PRIVATE);
    }

    public String getUserID() {
        return getStringPreference(context, FILENAME, KEY_USERID);
    }

    public void setUserID(String userID) {
        putStringPreference(context, FILENAME, KEY_USERID, userID);
    }

    public void setLoginStatus(boolean isLogin) {
        putBooleanPreference(context, FILENAME, KEY_LOGIN_STATUS, isLogin);
    }

    public boolean isLogin() {
        return getBooleanPreference(context, FILENAME, KEY_LOGIN_STATUS);
    }


}

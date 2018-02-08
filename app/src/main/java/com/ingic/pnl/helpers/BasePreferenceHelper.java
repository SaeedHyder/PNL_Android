package com.ingic.pnl.helpers;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;


public class BasePreferenceHelper extends PreferenceHelper {

    protected static final String KEY_LOGIN_STATUS = "islogin";
    private static final String FILENAME = "preferences";
    private static final String KEY_USERID = "USERID";
    private static final String KEY_USERNAME= "KEY_USERNAME";
    private static final String KEY_USEREMAIL= "KEY_USEREMAIL";
    private static final String KEY_CITY= "KEY_CITY";
    private static final String KEY_PHONE= "KEY_PHONE";
    private static final String KEY_NOTIFICATION_STATUS= "KEY_NOTIFICATION_STATUS";
    private Context context;
    protected static final String Firebase_TOKEN = "Firebasetoken";


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

    public void setUserName(String userName) {
        putStringPreference(context, FILENAME, KEY_USERNAME, userName);
    }

    public String getUserName() {
        return getStringPreference(context, FILENAME, KEY_USERNAME);
    }

    public void setUserEmail(String userEmail) {
        putStringPreference(context, FILENAME, KEY_USEREMAIL, userEmail);
    }

    public String getUserEmail() {
        return getStringPreference(context, FILENAME, KEY_USEREMAIL);
    }

    public void setPhoneNum(String userPhone) {
        putStringPreference(context, FILENAME, KEY_PHONE, userPhone);
    }

    public String getPhoneNum() {
        return getStringPreference(context, FILENAME, KEY_PHONE);
    }

    public void setCity(String userCity) {
        putStringPreference(context, FILENAME, KEY_CITY, userCity);
    }

    public String getCity() {
        return getStringPreference(context, FILENAME, KEY_CITY);
    }

    public void setLoginStatus(boolean isLogin) {
        putBooleanPreference(context, FILENAME, KEY_LOGIN_STATUS, isLogin);
    }

    public boolean isLogin() {
        return getBooleanPreference(context, FILENAME, KEY_LOGIN_STATUS,false);
    }
    public String getFirebase_TOKEN() {
        return getStringPreference(context, FILENAME, Firebase_TOKEN);
    }

    public void setFirebase_TOKEN(String _token) {
        putStringPreference(context, FILENAME, Firebase_TOKEN, _token);
    }

    public void setUserNotificationStatus(boolean checked) {
        putBooleanPreference(context,FILENAME,KEY_NOTIFICATION_STATUS,checked);
    }
    public boolean getNotificationStauts() {
        return getBooleanPreference(context, FILENAME, KEY_NOTIFICATION_STATUS,true);
    }
    public void removeNotificationStauts() {
       removePreference(context,FILENAME,KEY_NOTIFICATION_STATUS);
    }
}

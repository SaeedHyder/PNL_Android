package com.app.pnl.helpers;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.app.pnl.activities.DockActivity;

/**
 * Created on 9/26/2017.
 */

public class Utils {
    public static String getTAG(Object o) {
        StackTraceElement[] elements = Thread.currentThread().getStackTrace();

        int position = 0;
        for (int i = 0; i < elements.length; i++) {
            if (elements[i].getFileName().contains(o.getClass().getSimpleName())
                    && !elements[i+1].getFileName().contains(o.getClass().getSimpleName())){
                position = i;
                break;
            }
        }

        StackTraceElement element = elements[position];
        String className = element.getFileName().replace(".java", "");
        return "[" + className + "](" + element.getMethodName() + ":" + element.getLineNumber() + ")";
    }

    public static void HideKeyBoard(DockActivity dockActivity) {
        View view = dockActivity.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) dockActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


}

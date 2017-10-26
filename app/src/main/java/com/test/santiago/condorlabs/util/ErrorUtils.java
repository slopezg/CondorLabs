package com.test.santiago.condorlabs.util;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.test.santiago.condorlabs.R;

/**
 * Created by Santiago on 10/26/17.
 */

public class ErrorUtils {
    public static String getErrorMessage(Context mContext, Throwable e){
        if(e instanceof NetworkError){
            return mContext.getString(R.string.verify_internet_connection);
        }else if(e instanceof AuthFailureError){
            return mContext.getString(R.string.verify_auth_connection);
        }

        return mContext.getResources().getString(R.string.error_search);
    }
}

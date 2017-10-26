package com.test.santiago.condorlabs.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Santiago on 10/26/17.
 */

public class Preferences {

    private static SharedPreferences.Editor getPreferencesEditor(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.PREFERENCES_NAME,
                Context.MODE_PRIVATE);
        return sharedPreferences.edit();
    }

    public static void cleanPreferences(Context context) {
        SharedPreferences.Editor editor = getPreferencesEditor(context);
        editor.clear();
        editor.commit();
    }

    public static void saveString(Context context, String key, String value) {
        SharedPreferences.Editor editor = getPreferencesEditor(context);
        editor.putString(key, value);
        editor.commit();
    }

    public static String getString(Context context, String key) {
        if ((context != null)) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.PREFERENCES_NAME,
                    Context.MODE_PRIVATE);
            return sharedPreferences.getString(key, "");
        }
        return null;
    }
}

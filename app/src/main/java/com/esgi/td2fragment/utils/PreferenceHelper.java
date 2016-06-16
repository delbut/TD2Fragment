package com.esgi.td2fragment.utils;

import com.pixplicity.easyprefs.library.Prefs;

/**
 * Created by maxime on 15/06/16.
 */
public final class PreferenceHelper {
    private static final String PREVIOUS_LOGIN = "previousLogin";


    private PreferenceHelper() {
        //noop
    }


    public static String getPreviousLogin() {
        return Prefs.getString(PREVIOUS_LOGIN, null);
    }

    public static void setPreviousLogin(String idUser) {
        Prefs.putString(PREVIOUS_LOGIN, idUser);
    }

}

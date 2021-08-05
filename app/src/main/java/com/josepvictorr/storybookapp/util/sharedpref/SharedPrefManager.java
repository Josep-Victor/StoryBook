package com.josepvictorr.storybookapp.util.sharedpref;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {
    public static final String SharedPref = "spStoryBookApp";

    public static final String SP_Username = "spUsername";

    public static final String SP_LoginCheck = "spLoginCheck";

    SharedPreferences sp;
    SharedPreferences.Editor spEditor;

    public SharedPrefManager(Context context){
        sp = context.getSharedPreferences(SharedPref, Context.MODE_PRIVATE);
        spEditor = sp.edit();
    }

    public void saveSPString(String keySP, String value) {
        spEditor.putString(keySP, value);
        spEditor.commit();
    }

    public void saveSPInt(String keySP, Integer value) {
        spEditor.putInt(keySP, value);
        spEditor.commit();
    }

    public void saveSPBoolean(String keySP, Boolean value) {
        spEditor.putBoolean(keySP, value);
        spEditor.commit();
    }

    public String getSP_Username(){
        return sp.getString(SP_Username, "");
    }

    public Boolean getSP_LoginCheck() {
        return sp.getBoolean(SP_LoginCheck, false);
    }
}

package com.midnightgeek.falllib.pref;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

import javax.inject.Inject;

public class PrefHandler {
    private final String PREF_NAME="FALLDETECT";

    private static SharedPreferences sharedPref = null;
    private Context application;

    @Inject
    public PrefHandler(Context context) {
        this.application = context;
        sharedPref = application.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public void setPreference(String key, Object value) {
        SharedPreferences.Editor editor = sharedPref.edit();

        if (value instanceof Integer)
            editor.putInt(key, (Integer) value);
        else if (value instanceof String)
            editor.putString(key, (String) value);
        else if (value instanceof Boolean)
            editor.putBoolean(key, (Boolean) value);
        else if (value instanceof Long)
            editor.putLong(key, (Long) value);
        else if (value instanceof Set)
            editor.putStringSet(key, (Set<String>) value);

        editor.apply();
    }

    public int getInt(String key, int defaultValue) {
        return sharedPref.getInt(key, defaultValue);
    }

    public String getString(String key, String defaultValue) {
        return sharedPref.getString(key, defaultValue);
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        return sharedPref.getBoolean(key, defaultValue);
    }

    public long getLong(String key, long defaultValue) {
        return sharedPref.getLong(key, defaultValue);
    }

    public Set<String> getStringSet(String key, Set<String> defaultValue) {
        return sharedPref.getStringSet(key, defaultValue);
    }

    public void clearTag(String keyName) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.remove(keyName);
        editor.apply();
    }

    public boolean clear() {
        return sharedPref.edit().clear().commit();
    }

    public boolean contain(String key) {
        return sharedPref.contains(key);
    }

    public void RemovingSinglePreference(String key) {
        sharedPref.edit().remove(key).apply();
    }

}

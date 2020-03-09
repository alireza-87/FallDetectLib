package com.midnightgeek.falllib.notifications.models;

import android.net.Uri;

public class ModelSoundProfile {
    private long[] pattern;
    private Uri uri;
    private int ledColor;

    public ModelSoundProfile() {
    }

    public int getLedColor() {
        return ledColor;
    }

    public void setLedColor(int ledColor) {
        this.ledColor = ledColor;
    }

    public long[] getPattern() {
        return pattern;
    }

    public void setPattern(long[] pattern) {
        this.pattern = pattern;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }
}

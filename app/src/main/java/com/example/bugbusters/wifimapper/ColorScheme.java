package com.example.bugbusters.wifimapper;

import android.graphics.Color;

public class ColorScheme {
    public static final int TRANSPARENCY = 100;
    public static final int RED = Color.argb(TRANSPARENCY, 255, 0, 0);
    public static final int ORANGE = Color.argb(TRANSPARENCY, 255, 127, 0);
    public static final int YELLOW = Color.argb(TRANSPARENCY, 255, 255, 0);
    public static final int GREEN_LIGHT = Color.argb(TRANSPARENCY, 127, 255, 0);
    public static final int GREEN = Color.argb(TRANSPARENCY, 0, 255, 0);


    public static int evaluateColor(double wifiStrength) {
        int color;
        if (wifiStrength < 30) {
            color = ColorScheme.RED;
        } else if (wifiStrength < 50) {
            color = ColorScheme.ORANGE;
        } else if (wifiStrength < 60) {
            color = ColorScheme.YELLOW;
        } else if (wifiStrength < 80) {
            color = ColorScheme.GREEN_LIGHT;
        } else {
            color = ColorScheme.GREEN;
        }
        return color;
    }
}

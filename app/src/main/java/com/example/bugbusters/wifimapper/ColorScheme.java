package com.example.bugbusters.wifimapper;

import android.graphics.Color;

public class ColorScheme {
    private static final int TRANSPARENCY = 100;
    private static final int RED = Color.argb(TRANSPARENCY, 255, 0, 0);
    private static final int ORANGE = Color.argb(TRANSPARENCY, 255, 127, 0);
    private static final int YELLOW = Color.argb(TRANSPARENCY, 255, 255, 0);
    private static final int GREEN_LIGHT = Color.argb(TRANSPARENCY, 127, 255, 0);
    private static final int GREEN = Color.argb(TRANSPARENCY, 0, 255, 0);


    public static int evaluateColor(int wifiStrength) {
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

    public static int getNonTransparentColor(int wifiStrength)
    {
        int argb=evaluateColor(wifiStrength);
        return Color.rgb(Color.red(argb),Color.green(argb),Color.blue(argb));
    }


    public static int getHue(int RGBint) {
        int blue =  RGBint & 255;
        int green = (RGBint >> 8) & 255;
        int red =   (RGBint >> 16) & 255;

        float min = Math.min(Math.min(red, green), blue);
        float max = Math.max(Math.max(red, green), blue);

        if (min == max) {
            return 0;
        }

        float hue = 0f;
        if (max == red) {
            hue = (green - blue) / (max - min);

        } else if (max == green) {
            hue = 2f + (blue - red) / (max - min);

        } else {
            hue = 4f + (red - green) / (max - min);
        }

        hue = hue * 60;
        if (hue < 0) hue = hue + 360;

        return Math.round(hue);
    }
}

package it.rjcsoft.prv.utils;

import java.awt.Color;
import java.util.Arrays;

public class ColorUtil {


    
    public static Color convertHexToColor(String colorStr) {
        Color color = null;
        if (colorStr != null && colorStr.length() == 7) {
            color = Color.decode(colorStr);
        }
        return color;
    }

    public static String getHTMLCode(int index) {
        return String.format("%06x", ColorUtil.getRGB(index)).toUpperCase();
    }

    public static int getRGB(int index) {
        int[] p = getPattern(index);
        return getElement(p[0]) << 16 | getElement(p[1]) << 8 | getElement(p[2]);
    }

    public static int getElement(int index) {
        int value = index - 1;
        int v = 0;
        for (int i = 0; i < 8; i++) {
            v = v | (value & 1);
            v <<= 1;
            value >>= 1;
        }
        v >>= 1;
        return v & 0xFF;
    }

    public static int[] getPattern(int index) {
        index += 2;
        int n = (int) Math.cbrt(index);
        index -= (n * n * n);
        int[] p = new int[3];
        Arrays.fill(p, n);
        if (index == 0) {
            return p;
        }
        index--;
        int v = index % 3;
        index = index / 3;
        if (index < n) {
            p[v] = index % n;
            return p;
        }
        index -= n;
        p[v] = index / n;
        p[++v % 3] = index % n;
        return p;
    }
}

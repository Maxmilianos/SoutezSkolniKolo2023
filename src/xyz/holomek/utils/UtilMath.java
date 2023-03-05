package xyz.holomek.utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Random;

public class UtilMath {

    public static Random random = new Random();

    public static boolean contains(int[] integers, Integer search) {
        for (Integer id : integers)
            if (id == search)
                return true;
        return false;
    }

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            if (Integer.valueOf(s) < 0) {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static boolean isDouble(String s) {
        try {
            Double.parseDouble(s);
            if (Double.valueOf(s) < 0) {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static boolean isLong(String s) {
        try {
            Long.parseLong(s);
            if (Long.valueOf(s) < 0) {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    // metoda pro decimalformat
    public static double trim(int degree, double d) {
        String format = "#.#";
        for (int i=1 ; i<degree ; i++)
            format += "#";

        return Double.valueOf(new DecimalFormat(format, new DecimalFormatSymbols(Locale.US)).format(d));
    }

    public static int random(int min, int max) {
        return min + random.nextInt(max - min);
    }

    public static int random(int max) {
        return random(0, max);
    }

    /**
     * If the point is within the rectangle, return true, else return false.
     *
     * @param x The x coordinate of the point.
     * @param y The y coordinate of the point.
     * @param x1 The x coordinate of the first point.
     * @param y1 The y coordinate of the first point.
     * @param x2 The x coordinate of the first point.
     * @param y2 The y coordinate of the bottom right corner of the rectangle.
     * @return A boolean value.
     */
    public static boolean isInArea(int x, int y, int x1, int y1, int x2, int y2) {
        return x <= Math.max(x1, x2) && x >= Math.min(x1, x2) && y >= Math.min(y1, y2) && y <= Math.max(y1, y2);
    }

}

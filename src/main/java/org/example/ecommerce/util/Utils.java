package org.example.ecommerce.util;

public class Utils {

    private Utils() {
    }

    public static String getLessDescription(String desc) {
        String[] strs = desc.split(" ");

        if (strs.length > 10) {
            StringBuilder res = new StringBuilder();
            for (int i = 0; i < 10; i++) {
                res.append(" ").append(strs[i]);
            }
            return (res + "...");
        } else {
            return (desc + "...");
        }

    }

    public static double getPriceAfterApplyingDiscount(double price, int discount) {
        double calculatedDiscount = (((double) discount / 100) * price);
        return price - calculatedDiscount;
    }

}

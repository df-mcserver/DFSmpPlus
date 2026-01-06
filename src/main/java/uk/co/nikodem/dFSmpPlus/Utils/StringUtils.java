package uk.co.nikodem.dFSmpPlus.Utils;

public class StringUtils {
    public static String NormalCapitalisation(String string) {
        return string.substring(0,1).toUpperCase() + string.substring(1).toLowerCase();
    }
}

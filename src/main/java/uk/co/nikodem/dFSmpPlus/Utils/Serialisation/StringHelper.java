package uk.co.nikodem.dFSmpPlus.Utils.Serialisation;

public class StringHelper {
    public static String SanitiseString(String string) {
        return string.replaceAll("\\p{C}", "");
    }
}

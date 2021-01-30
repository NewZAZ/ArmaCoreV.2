package fr.newzaz.armacore.utils;

public class IntegerUtils {
    public static boolean isInteger(String s)
    {
        try
        {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException ex)
        {
            return false;
        }
    }
}

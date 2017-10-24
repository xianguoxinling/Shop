package com.until.num;

import java.util.regex.Pattern;

public class UntilNum
{
    public static boolean isNumeric(String str)
    {
        if (null == str)
        {
            return false;
        }
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }

    /**
     * 判断字符串是否是整数
     */
    public static boolean isInteger(String value)
    {
        if (null == value)
        {
            return false;
        }
        try
        {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e)
        {
            return false;
        }
    }

    /**
     * 判断字符串是否是整数
     */
    public static boolean isLong(String value)
    {
        if (null == value)
        {
            return false;
        }
        try
        {
            Long.parseLong(value);
            return true;
        } catch (NumberFormatException e)
        {
            return false;
        }
    }

    /**
     * 判断字符串是否是浮点数
     */
    public static boolean isDouble(String value)
    {
        if (null == value)
        {
            return false;
        }
        try
        {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException e)
        {
            return false;
        }
    }

    /**
     * 判断字符串是否是数字
     */
    public static boolean isNumber(String value)
    {
        if (null == value)
        {
            return false;
        }
        return isInteger(value) || isDouble(value) || isLong(value);
    }

}

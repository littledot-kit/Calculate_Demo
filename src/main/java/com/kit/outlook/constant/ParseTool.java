package com.kit.outlook.constant;

import java.text.NumberFormat;
import java.text.ParseException;

public class ParseTool {

    public static boolean isNumberString(String s) {
        try {
            NumberFormat.getInstance().parse(s);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
}

package com.test.santiago.condorlabs.util;

import java.text.DecimalFormat;

/**
 * Created by Santiago on 10/26/17.
 */

public class DoubleHelper {
    public static String getFormat(double value){
        DecimalFormat decimalFormat = new DecimalFormat("#,###.##");
        return decimalFormat.format(value);
    }
}

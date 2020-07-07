package com.mbelisaire.familysecret.dao;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.annotation.StringDef;

public class MeasureTypeEnum {

    //Constants
    public static final String CUP = "CUP";
    public static final String TBLSP = "TBLSP";
    public static final String TSP = "TSP";
    public static final String K = "K";
    public static final String G = "G";
    public static final String OZ = "OZ";
    public static final String UNIT = "UNIT";
    public static final String UNKNOWN = "UNKNOWN";

    @StringDef({CUP, TBLSP, TSP, K, G, OZ, UNIT, UNKNOWN})
    @Retention(RetentionPolicy.SOURCE)
    public @interface MeasureType {}
}

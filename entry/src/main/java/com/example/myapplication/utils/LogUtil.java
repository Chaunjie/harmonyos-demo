package com.example.myapplication.utils;

import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

public class LogUtil {

    private static final HiLogLabel LABEL_LIFECYCLE_LOG = new HiLogLabel(3, 0xD001100, "lifecycle");
    private static final HiLogLabel LABEL_CLICK_LOG = new HiLogLabel(3, 0xD001101, "click");
    private static final HiLogLabel LABEL_SERVICE_LOG = new HiLogLabel(3, 0xD001102, "service");
    private static final HiLogLabel LABEL_DATA_ABILITY_LOG = new HiLogLabel(3, 0xD001103, "dataAbility");
    private static final HiLogLabel LABEL_DEBUG_LOG = new HiLogLabel(3, 0xD001104, "consoleJava");

    public static void clickInfo(String meg){
        HiLog.info(LABEL_CLICK_LOG, meg);
    }

    public static void lifeCycleInfo(String tag, String meg){
//        Logger.getLogger(Level.INFO.getName()).info(LIFECYCLE_INFO + "_" + tag + ":" + meg);
        HiLog.info(LABEL_LIFECYCLE_LOG, tag + "::" + meg);
    }

    public static void serviceInfo(String tag, String meg){
//        Logger.getLogger(Level.INFO.getName()).info(SERVICE_INFO + "_" + tag + ":" + meg);
        HiLog.info(LABEL_SERVICE_LOG, tag + "::" + meg);
    }

    public static void dataInfo(String tag, String meg){
//        Logger.getLogger(Level.INFO.getName()).info(DATA_ABILITY_INFO  + ":" + meg);
        HiLog.info(LABEL_DATA_ABILITY_LOG, tag + "::" + meg);
    }

    public static void debugInfo(String meg){
//        Logger.getLogger(Level.INFO.getName()).info(DEBUG_INFO  + ":" + meg);
        HiLog.info(LABEL_DEBUG_LOG, meg);
    }
}


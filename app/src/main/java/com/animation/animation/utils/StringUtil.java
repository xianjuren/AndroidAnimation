package com.animation.animation.utils;

import com.animation.animation.application.GlobalConfig;

/**
 * Created by Jone on 17/4/21.
 */

public class StringUtil {

    public static String StringId(int id){
        return GlobalConfig.getContext().getString(id);
    }

    public static String StringId(int id,Object ...args){
        return GlobalConfig.getContext().getString(id,args);
    }
}

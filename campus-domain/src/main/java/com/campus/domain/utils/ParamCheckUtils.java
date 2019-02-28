package com.campus.domain.utils;

import java.util.List;

/**
 * TO DO :参数校验工具类
 *
 * @author 睿动体育 | wangzhiqiang
 * @create 2018-05-14 15:31
 */
public class ParamCheckUtils {
    /**Long 判空**/
    public static boolean isBlank(Long param){
        return param == null || param <= 0;
    }
    /**Long 判空**/
    public static boolean isBlank(Integer param){
        return param == null || param <= 0;
    }
    /**判断list**/
    public  static  boolean isBlank(List list){
        return  list == null || list.isEmpty();
    }

    public  static  boolean isBlank(String param){
        return  param == null || param.isEmpty() || param.equals("");
    }

    /**Short 判空**/
    public static boolean isBlank(Short param){
        return param == null || param < 0;
    }

    public static boolean isBlank(Byte param) {
        return param == null || param == 0;
    }
}

/*--------------------------------------------------------------------------
 *  Copyright (c) 2010-2020, dennis@163.com All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the 苏若年 developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: 苏若年(qq: 1325103287), you can also mail dennisit@163.com
 *--------------------------------------------------------------------------
*/
package com.wuyu.plugin.floret.util;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Copyright (c) 2016, All Rights Reserved.
 * Author: dennisit@163.com / 苏若年
 */
public class NameUtil {

    public static final char UNDERLINE='_';

    /**
     * 托峰命名转换为下划线
     * @param param
     * @return
     */
    public static String camelToUnderline(String param){
        if (param==null||"".equals(param.trim())){
            return "";
        }
        int len=param.length();
        StringBuilder sb=new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c=param.charAt(i);
            if (Character.isUpperCase(c)){
                sb.append(UNDERLINE);
                sb.append(Character.toLowerCase(c));
            }else{
                sb.append(c);
            }
        }
        String tmp = sb.toString();
        if(tmp.startsWith("_")){
            tmp = tmp.substring(1, tmp.length());
        }
        return tmp;
    }

    public static String firstToUpper(String str) {
        if(StringUtils.isBlank(str)){
            return StringUtils.EMPTY;
        }
        return str.replaceFirst(str.substring(0, 1),str.substring(0, 1).toUpperCase()) ;
    }

    public static String firstToLower(String str) {
        if(StringUtils.isBlank(str)){
            return StringUtils.EMPTY;
        }
        return str.replaceFirst(str.substring(0, 1),str.substring(0, 1).toLowerCase()) ;
    }

    /**
     * 下划线命名转换为驼峰
     * @param param
     * @return
     */
    @Deprecated
    public static String underlineToCamel(String param){
        if (param==null||"".equals(param.trim())){
            return "";
        }
        int len=param.length();
        StringBuilder sb=new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c=param.charAt(i);
            if (c==UNDERLINE){
                if (++i<len){
                    sb.append(Character.toUpperCase(param.charAt(i)));
                }
            }else{
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 下划线命名转换成驼峰
     * @param param
     * @return
     */
    @Deprecated
    public static String underlineToCamel2(String param){
        if (param==null||"".equals(param.trim())){
            return "";
        }
        StringBuilder sb=new StringBuilder(param);
        Matcher mc= Pattern.compile("_").matcher(param);
        int i=0;
        while (mc.find()){
            int position=mc.end()-(i++);
            //String.valueOf(Character.toUpperCase(sb.charAt(position)));
            sb.replace(position-1,position+1,sb.substring(position,position+1).toUpperCase());
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        System.out.println(camelToUnderline("tmcOrder"));
    }

}

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
package com.wuyu.plugin.floret.setting;

import com.wuyu.plugin.floret.bean.dict.PathPHoldEnum;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

/**
 * Copyright (c) 2016, All Rights Reserved.
 * Author: dennisit@163.com / 苏若年
 */
public class Settings {

    /**
     * 扫描路径
     */
    public static String SCAN_PATH = "*";

    /**
     * 默认使用的编码
     */
    public static String CHARSET = "UTF-8";

    /**
     * 默认的模板加载路径
     */
    public static String LOCATION_PATTERN = "classpath*:/floret-web/**";

    /**
     * 接收的文件路径
     */
    public static List<String> ACCEPT_SUFFIX = Arrays.asList(".java", ".xml", ".properties", ".html", ".js", ".css", ".png", ".jpg", ".txt", ".eot", "ttf", ".svg", ".woff");

    /**
     * 路径名站位符
     */
    public static String PATH_PLACE_HOLDER_PREFIX = "\\$\\{";
    public static String PATH_PLACE_HOLDER_SUFFIX = "\\}";


    public static String buildPlaceHold(PathPHoldEnum pathPHold){
        return buildPlaceHold(pathPHold.getPlaceName());
    }

    public static String buildPlaceHold(String placeHoldName){
        if(StringUtils.isBlank(placeHoldName)){
            return StringUtils.EMPTY;
        }
        return PATH_PLACE_HOLDER_PREFIX + StringUtils.trim(placeHoldName) + PATH_PLACE_HOLDER_SUFFIX;
    }

}

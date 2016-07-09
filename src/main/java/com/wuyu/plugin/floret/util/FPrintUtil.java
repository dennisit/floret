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

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Copyright (c) 2016, All Rights Reserved.
 * Author: dennisit@163.com / 苏若年
 */
public class FPrintUtil {

    /**
     * 站位符替换多个参数
     * @param message
     * @param params
     */
    public static void info(String message, Object ... params){
        if(params.length == 0){
            System.out.println("[INFO]\t " + message);
            return ;
        }
        AtomicInteger atomic = new AtomicInteger(params.length);
        while(message.contains("{}") && atomic.get() >0){
            Object obj = params[params.length - atomic.getAndDecrement()];
            message = message.replaceFirst("\\{\\}", String.valueOf(obj));
        }
        System.out.println("[INFO]\t " + message);
    }

    public static void error(String message, Object ... params){
        if(params.length == 0){
            System.out.println(message);
        }
        AtomicInteger atomic = new AtomicInteger(params.length);
        while(message.contains("{}") && atomic.get() >0){
            Object obj = params[params.length - atomic.getAndDecrement()];
            message = message.replaceFirst("\\{\\}", String.valueOf(obj));
        }
        System.out.println("[ERROR]\t " + message);
    }
}

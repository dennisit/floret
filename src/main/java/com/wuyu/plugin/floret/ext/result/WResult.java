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
package com.wuyu.plugin.floret.ext.result;

import java.io.Serializable;

/**
 * Copyright (c) 2016, All Rights Reserved.
 * Author: dennisit@163.com / 苏若年
 */
public class WResult<T> implements Serializable {

    // 业务状态吗
    private int code;

    // http状态吗
    private int http;

    // 提示消息
    private String message;

    // 提示消息中文描述
    private String display;

    // 页面回传的数据信息
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getHttp() {
        return http;
    }

    public void setHttp(int http) {
        this.http = http;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

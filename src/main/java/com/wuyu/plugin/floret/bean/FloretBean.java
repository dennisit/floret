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
package com.wuyu.plugin.floret.bean;

import java.io.Serializable;
import java.util.Map;

/**
 * Copyright (c) 2016, All Rights Reserved.
 * Author: dennisit@163.com / 苏若年
 */
public class FloretBean implements Serializable{

    /**
     * 包名称
     */
    private String packageName;

    /**
     * 类名称
     */
    private String className;

    /**
     * 类名称对应的表名称
     */
    private String tableName;

    /**
     * key为映射类的属性名称, 属性的映射绑定
     */
    private Map<String, FloretBindBean> plainAttributeContext;

    /**
     * 主键映射的绑定
     */
    private Map<String, FloretBindBean> primaryAttributeContext;


    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Map<String, FloretBindBean> getPlainAttributeContext() {
        return plainAttributeContext;
    }

    public void setPlainAttributeContext(Map<String, FloretBindBean> plainAttributeContext) {
        this.plainAttributeContext = plainAttributeContext;
    }

    public Map<String, FloretBindBean> getPrimaryAttributeContext() {
        return primaryAttributeContext;
    }

    public void setPrimaryAttributeContext(Map<String, FloretBindBean> primaryAttributeContext) {
        this.primaryAttributeContext = primaryAttributeContext;
    }
}

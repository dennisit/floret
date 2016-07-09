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

/**
 * Copyright (c) 2016, All Rights Reserved.
 * Author: dennisit@163.com / 苏若年
 */
public class FloretBindBean  implements Serializable {

    /**
     * 表名称
     */
    private String tableName;

    /**
     * 属性名称
     */
    private String attributeName;

    /**
     * 属性类型
     */
    private String attributeType;

    /**
     * 字段名称
     */
    private String columnName;

    /**
     * 是否主键
     */
    private Boolean isPrimary;

    /**
     * 前端空间展示名称
     */
    private String displayName;

    /**
     * 列表展示
     */
    private boolean listShow = true;

    /**
     * 编辑列表展示
     */
    private boolean editShow = true;

    /**
     * 搜索条件展示
     */
    private boolean searchShow = false;


    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public Boolean getIsPrimary() {
        return isPrimary;
    }

    public void setIsPrimary(Boolean isPrimary) {
        this.isPrimary = isPrimary;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public String getAttributeType() {
        return attributeType;
    }

    public void setAttributeType(String attributeType) {
        this.attributeType = attributeType;
    }

    public boolean isListShow() {
        return listShow;
    }

    public void setListShow(boolean listShow) {
        this.listShow = listShow;
    }

    public boolean isEditShow() {
        return editShow;
    }

    public void setEditShow(boolean editShow) {
        this.editShow = editShow;
    }

    public boolean isSearchShow() {
        return searchShow;
    }

    public void setSearchShow(boolean searchShow) {
        this.searchShow = searchShow;
    }
}

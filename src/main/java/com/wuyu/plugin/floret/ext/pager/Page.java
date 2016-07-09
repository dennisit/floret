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
package com.wuyu.plugin.floret.ext.pager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (c) 2016, All Rights Reserved.
 * Author: dennisit@163.com / 苏若年
 */
public final class Page <T> implements Serializable {


    private static final long serialVersionUID = 1L;

    /**
     * 记录总数
     */
    private long recordTotal;

    /**
     * 页码数
     */
    private long pageNum;

    /**
     * 每页大小
     */
    private int pageSize;

    /**
     * 每页携带的数据集
     */
    private List<T> items = null;

    /**
     * 页码总数
     */
    private long pageTotal;

    /**
     * 当前页是否第一页
     */
    private boolean isFirstPage = false;

    /**
     * 当前页是否最后一页
     */
    private boolean isLastPage = false;

    /**
     * 默认的每页大小数
     */
    public static final Integer DEFAULT_PAGE_SIZE = 20;

    /**
     * 分页html
     */
    private String pageHtml = "";



    public Page() {
        this(new ArrayList<T>(), 0, 1);
    }


    public Page(List<T> list, long recordTotal, long pageNum) {
        setPage(list, recordTotal, pageNum, DEFAULT_PAGE_SIZE);
    }

    public Page(List<T> list, long recordTotal, long pageNum, int size) {
        setPage(list, recordTotal, pageNum, size);
    }

    private void setPage(List<T> list, long recordTotal, long pageNum, int pageSize) {
        long pageCount = (recordTotal - 1) / pageSize + 1;
        if (pageNum == 1) {
            setFirstPage(true);
        }
        if (pageNum == pageCount) {
            setLastPage(true);
        }
        this.items = list;
        this.pageTotal = pageCount;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.recordTotal = recordTotal;
        initPageHtml();
    }

    public void initPageHtml(){
        initPageHtml(false);
    }


    public void initPageHtml(boolean bothEnds){
        initPageHtml(bothEnds, 7);
    }

    /**
     * 构造分页的html代码
     * @param bothEnds 是否包含首页和末页
     * @param pageViewLength 展示的页码个数
     */
    public void initPageHtml(boolean bothEnds, int pageViewLength){
        // 规范页码约束
        pageNum = pageNum < 1 ? 1: (pageNum > pageTotal ? pageTotal : pageNum);
        pageTotal = pageTotal < pageNum ? pageNum : pageTotal;

        long $start = pageNum - (int)Math.floor(pageViewLength/2);
        long $finis = pageNum + (int)Math.round(pageViewLength/2);

        // 计算开始页
        $start = $start < 1 ? 1 : $start;
        //计算结束页
        $finis = $finis > pageTotal ? pageTotal : $finis;

        //当前显示的页码个数不够最大页码数，在进行左右调整
        long $curPageNum = $finis - $start + 1;

        // 左调整
        if($curPageNum < pageViewLength && $start > 1){
            $start = $start - (pageViewLength - $curPageNum);
            $start = $start < 1 ? 1 : $start;
            $curPageNum = $finis - $start + 1;
        }

        // 右调整
        if($curPageNum < pageViewLength && $finis < pageViewLength){
            $finis = $finis + (pageViewLength - $curPageNum);
            $finis = $finis > pageTotal ? pageTotal : $finis;
        }

        StringBuffer pageHtml = new StringBuffer(200).append("<ul class='pagination'>").append("\r\n");
        if(bothEnds){
            pageHtml.append(" <li><a href='javascript:void(0);' ng-click='pageFunction(1, " + getPageSize() + ")' >首页</a></li>").append("\r\n");
        }
        if(pageNum > 1){
            pageHtml.append(" <li><a href='javascript:void(0);' ng-click='pageFunction("+ (pageNum - 1) +", " + getPageSize() + ")'>上一页</a></li>").append("\r\n");
        }
        for (long $i = $start; $i <= $finis; $i++) {
            if($i == pageNum){
                pageHtml.append(" <li class='active'><a href='javascript:void(0);' ng-click='pageFunction("+ $i + ", " + getPageSize() + ")'>" + $i + "</a></li>").append("\r\n");
            }else{
                pageHtml.append(" <li><a href='javascript:void(0);' ng-click='pageFunction("+ $i + ", " + getPageSize() + ")'>" + $i + "</a></li>").append("\r\n");
            }
        }
        if(pageNum < $finis){
            pageHtml.append(" <li><a href='javascript:void(0);' ng-click='pageFunction("+ ( pageNum + 1) + ", " + getPageSize() + ")'>下一页</a></li>").append("\r\n");
        }
        if(bothEnds){
            pageHtml.append(" <li><a href='javascript:void(0);' ng-click='pageFunction("+ pageTotal + ", " + getPageSize() + ")'>末页</a></li>").append("\r\n");
        }
        pageHtml.append("</ul>");
        this.pageHtml = pageHtml.toString();
    }

    public long getTotalPage() {
        if (getPageSize() > 0) {
            return ((recordTotal - 1) / getPageSize() + 1);
        }
        return 0;
    }

    public long getPageNum() {
        return pageNum;
    }

    public void setPageNum(long pageNum) {
        this.pageNum = pageNum;
    }

    public boolean isNextPage() {
        return ((this.pageTotal > 1) && (this.pageNum < this.pageTotal));
    }

    public boolean isPrePage() {
        return ((this.pageTotal > 1) && (this.pageNum < this.pageTotal));
    }

    public boolean isLastPage() {
        return isLastPage;
    }

    public void setLastPage(boolean isLastPage) {
        this.isLastPage = isLastPage;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public long getRecordTotal() {
        return recordTotal;
    }

    public void setRecordTotal(int recordTotal) {
        this.recordTotal = recordTotal;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public boolean isFirstPage() {
        return isFirstPage;
    }

    public void setFirstPage(boolean isFirstPage) {
        this.isFirstPage = isFirstPage;
    }

    public long getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(int pageTotal) {
        this.pageTotal = pageTotal;
    }

    public String getPageHtml() {
        return pageHtml;
    }

    public void setPageHtml(String pageHtml) {
        this.pageHtml = pageHtml;
    }

}

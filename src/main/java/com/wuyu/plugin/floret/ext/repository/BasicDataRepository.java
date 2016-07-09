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
package com.wuyu.plugin.floret.ext.repository;

import com.wuyu.plugin.floret.ext.pager.Page;
import org.apache.ibatis.session.SqlSession;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright (c) 2016, All Rights Reserved.
 * Author: dennisit@163.com / 苏若年
 */
public abstract class BasicDataRepository {

    protected String buildInsert(){
        return buildStatement("insert");
    }

    protected String buildDelete(){
        return buildStatement("delete");
    }

    protected String buildDeleteMulti(){
        return buildStatement("deleteMulti");
    }

    protected String buildDeleteList(){
        return buildStatement("deleteList");
    }

    protected String buildUpdate(){
        return buildStatement("update");
    }

    protected String buildCount(){
        return buildStatement("count");
    }

    protected String buildSelect(){
        return buildStatement("select");
    }

    protected String buildSelectMulti(){
        return buildStatement("selectMulti");
    }

    protected String buildSelectList(){
        return buildStatement("selectList");
    }

    protected String buildSelectPage(){
        return buildStatement("selectPage");
    }

    public String buildStatement(String sqlId){
        String nameSpace = getNameSpace();
        if(!nameSpace.endsWith(".")){
            nameSpace = nameSpace.concat(".");
        }
        return nameSpace + sqlId;
    }

    // 插入对象
    public int insert(Object condition) {
        if(null == condition){
            return 0;
        }
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("object", condition);
        return getSqlSession().insert(buildInsert(), param);
    }

    // 更新对象
    public int update(Object condition) {
        if(null == condition){
            return 0;
        }
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("object", condition);
        return getSqlSession().update(buildUpdate(), param);
    }

    // 删除对象
    public int delete(Number primaryKey) {
        if(null == primaryKey){
            return 0;
        }
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("primaryKey", primaryKey);
        return getSqlSession().delete(buildDelete(), param);
    }

    // 删除对象
    public int deleteMulti(Collection<? extends Number> primaryKeys) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("primaryKeys", primaryKeys);
        return getSqlSession().delete(buildDeleteMulti(), primaryKeys);
    }

    // 删除对象
    public int deleteList(Object condition) {
        if(null == condition){
            return 0;
        }
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("object", condition);
        return getSqlSession().delete(buildDeleteList(), param);
    }

    // 聚合总数
    public long count(Object condition) {
        if(null == condition){
            return 0;
        }
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("object", condition);
        return getSqlSession().selectOne(buildCount(), param);
    }

    // 查询对象
    public <T> T select(Number primaryKey) {
        if(null == primaryKey){
            return null;
        }
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("primaryKey", primaryKey);
        return getSqlSession().selectOne(buildSelect(), primaryKey);
    }

    // 查询对象
    public <T> List<T> selectMulti(Collection<? extends Number>  primaryKeys) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("primaryKeys", primaryKeys);
        return getSqlSession().selectList(buildSelectMulti(), param);
    }

    // 查询对象
    public <T> List<T> selectList(Object condition) {
        if(null == condition){
            return null;
        }
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("object", condition);
        return getSqlSession().selectList(buildSelectList(), param);
    }

    // 查询分页
    public <T> List<T> selectList(Object condition, long offset, int size) {
        if(null == condition){
            return null;
        }
        size = size<=0 ? 20: size;
        offset = offset<=0 ? 0L : offset;
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("object", condition);
        param.put("offset", offset);
        param.put("size", size);
        return getSqlSession().selectList(buildSelectPage(), param);
    }

    // 分页查询对象 20, 40
    public <T> Page<T> selectPagination(Object condition, long offset, int size) {
        size = size<=0 ? 20: size;
        offset = offset<=0 ? 0 : offset;
        long totalCount = count(condition);
        List<T> list = selectList(condition, offset, size);
        return new Page<T>(list, totalCount, (offset/size+1), size);
    }

    public abstract String getNameSpace();

    public abstract SqlSession getSqlSession();

}

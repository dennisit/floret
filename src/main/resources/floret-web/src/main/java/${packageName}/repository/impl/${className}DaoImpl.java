/*--------------------------------------------------------------------------
 *  Copyright (c) 2010-2020, dennisit@163.com All rights reserved.
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
 * Neither the name of the suruonian developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 *
 *  <dependency>
 *      <groupId>com.github.dennisit</groupId>
 *      <artifactId>floret</artifactId>
 *      <version>1.0.0</version>
 *  </dependency>
 *
 *  The code you see is generated by the generator "floret".
 *  // 生成路径
 *  String destPath = "/gen/";
 *  // 代码生成
 *  new FloretStrap().scanAnnoBorn(Settings.LOCATION_PATTERN, Settings.ACCEPT_SUFFIX, "com.dennisit", destPath, "UTF-8");
 *--------------------------------------------------------------------------
 */
package ${bean.packageName}.repository.impl;

import com.wuyu.plugin.floret.ext.pager.Page;
import com.wuyu.plugin.floret.ext.repository.BasicDataRepository;
import ${bean.packageName}.repository.${bean.className}Dao;
import ${bean.packageName}.domain.${bean.className};
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.*;

@Repository(value = "${nameUtil.firstToLower(bean.className)}Dao")
public class ${bean.className}DaoImpl extends BasicDataRepository implements ${bean.className}Dao {

    @Resource(name = "sqlSession")
    private SqlSession sqlSession;

    public int insert(@Param("object") ${bean.className} obj) {
        return super.insert(obj);
    }

    public int update(@Param("object") ${bean.className} obj) {
        return super.update(obj);
    }

    public int delete(@Param("primaryKey") Long primaryKey) {
        return super.delete(primaryKey);
    }

    public int deleteMulti(@Param("primaryKey") Set<Long> primaryKeys) {
        return super.deleteMulti(primaryKeys);
    }

    public int deleteList(@Param("object") ${bean.className} obj) {
        return super.deleteList(obj);
    }

    public long count(@Param("object") ${bean.className} obj) {
        return super.count(obj);
    }

    public ${bean.className} select(@Param("primaryKey") Long primaryKey) {
        return super.select(primaryKey);
    }

    public List<${bean.className}> selectMulti(@Param("primaryKey") Set<Long> primaryKeys) {
        return super.selectMulti(primaryKeys);
    }

    public List<${bean.className}> selectList(@Param("object") ${bean.className} obj) {
        return super.selectList(obj);
    }

    public Page<${bean.className}> selectPagination(@Param("object") ${bean.className} obj, @Param("offset") long offset, @Param("size") int size) {
        return super.selectPagination(obj, offset, size);
    }

    @Override
    public String getNameSpace() {
        return "${bean.packageName}.repository.impl.${bean.className}DaoImpl";
    }

    @Override
    public SqlSession getSqlSession() {
        return this.sqlSession;
    }

}

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
package com.wuyu.plugin.floret.plugin;

import com.wuyu.plugin.floret.anno.FloretField;
import com.wuyu.plugin.floret.anno.FloretTable;
import com.wuyu.plugin.floret.api.AbstractBeanScanApi;
import com.wuyu.plugin.floret.bean.FloretBean;
import com.wuyu.plugin.floret.bean.FloretBindBean;
import com.wuyu.plugin.floret.setting.Settings;
import com.wuyu.plugin.floret.util.NameUtil;
import com.wuyu.plugin.floret.util.FPrintUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Copyright (c) 2016, All Rights Reserved.
 * Author: dennisit@163.com / 苏若年
 */
public class FloretBeanAnnScanPlugin implements AbstractBeanScanApi {


    /**
     * scan 强烈建议指定,否则扫描性能会很差,全工程包扫描注解
     * @param scanPath
     * @return
     */
    public Set<FloretBean> scanFrameBean(String scanPath) {
        if(StringUtils.isBlank(scanPath)){
            scanPath = Settings.SCAN_PATH;
        }
        Set<String> classNames = scanFrameBeanName(scanPath);
        if(CollectionUtils.isEmpty(classNames)){
            return null;
        }
        Set<FloretBean> set = new HashSet<FloretBean>();
        FloretBean bean = null;
        for(String className: classNames){
            try {
                bean = new FloretBean();
                Class clazz = Class.forName(className);
                String packageName = clazz.getPackage().getName();
                if(StringUtils.isNoneBlank(packageName)){
                    packageName = packageName.substring(0,packageName.lastIndexOf("."));
                }
                bean.setPackageName(packageName);
                bean.setClassName(clazz.getSimpleName());
                if(clazz.isAnnotationPresent(FloretTable.class)){
                    FloretTable floretTable = (FloretTable) clazz.getAnnotation(FloretTable.class);
                    if(floretTable.abolished()){
                        continue;
                    }
                    FPrintUtil.info("[注解实体]\t 实体类名: " + className);
                    String tableName = StringUtils.isNotBlank(floretTable.tableName())? floretTable.tableName(): NameUtil.camelToUnderline(clazz.getSimpleName());
                    bean.setTableName(tableName);

                    Field[] fields = clazz.getDeclaredFields();
                    if(null != fields){
                        Map<String, FloretBindBean> primaryAttributeContext = new HashMap<String, FloretBindBean>();
                        Map<String, FloretBindBean> plainAttributeContext = new HashMap<String, FloretBindBean>();
                        for(Field f: fields){
                            if("serialVersionUID".equalsIgnoreCase(f.getName())){
                                continue;
                            }
                            FloretBindBean bindBean = new FloretBindBean();
                            if(f.isAnnotationPresent(FloretField.class)){
                                // 对于有映射注解的处理
                                FloretField floretField = f.getAnnotation(FloretField.class);
                                if(floretField.isExclude()){
                                    continue;
                                }
                                bindBean.setTableName(tableName);
                                bindBean.setListShow(floretField.isListShow());
                                bindBean.setEditShow(floretField.isEditShow());
                                bindBean.setSearchShow(floretField.isSearchShow());
                                bindBean.setAttributeName(f.getName());
                                bindBean.setAttributeType(f.getType().getName());
                                bindBean.setIsPrimary(floretField.isPrimary());
                                bindBean.setColumnName(StringUtils.isNotBlank(floretField.columnName()) ? floretField.columnName(): NameUtil.camelToUnderline(f.getName()));
                                bindBean.setDisplayName(StringUtils.isNotBlank(floretField.displayName())? floretField.displayName(): f.getName());
                                if(floretField.isPrimary()){
                                    primaryAttributeContext.put(bindBean.getAttributeName(), bindBean);
                                }else{
                                    plainAttributeContext.put(bindBean.getAttributeName(), bindBean);
                                }
                            }else{
                                // 对于没有映射注解的处理
                                bindBean.setTableName(tableName);
                                bindBean.setListShow(true);
                                bindBean.setEditShow(true);
                                bindBean.setSearchShow(false);
                                bindBean.setAttributeName(f.getName());
                                bindBean.setAttributeType(f.getType().getName());
                                bindBean.setColumnName(NameUtil.camelToUnderline(f.getName()));
                                bindBean.setDisplayName(f.getName());
                                bindBean.setIsPrimary(false);
                                plainAttributeContext.put(bindBean.getAttributeName(), bindBean);
                            }
                        }
                        bean.setPrimaryAttributeContext(primaryAttributeContext);
                        bean.setPlainAttributeContext(plainAttributeContext);
                    }
                }
                if(null != bean){
                    set.add(bean);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return set;
    }

    /**
     * 根据路径扫描要用来映射的类
     * @param scanPath
     * @return
     */
    public Set<String> scanFrameBeanName(String scanPath){
        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
        provider.addIncludeFilter(new AnnotationTypeFilter(FloretTable.class));
        Set<BeanDefinition> set = provider.findCandidateComponents(scanPath);
        if(null !=set && set.size() > 0){
            Set<String> classNames = new HashSet<String>();
            for(BeanDefinition bean: set){
                classNames.add(bean.getBeanClassName());
            }
            return classNames;
        }
        return null;
    }
}

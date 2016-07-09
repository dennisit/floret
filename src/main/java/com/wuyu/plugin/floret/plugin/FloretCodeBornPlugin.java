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


import com.alibaba.fastjson.JSON;
import com.wuyu.plugin.floret.api.AbstractCodeBornApi;
import com.wuyu.plugin.floret.bean.dict.PathPHoldEnum;
import com.wuyu.plugin.floret.bean.FloretBean;
import com.wuyu.plugin.floret.setting.Settings;
import com.wuyu.plugin.floret.util.NameUtil;
import com.wuyu.plugin.floret.util.FPrintUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.StringTemplateResourceLoader;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Copyright (c) 2016, All Rights Reserved.
 * Author: dennisit@163.com / 苏若年
 */
public class FloretCodeBornPlugin implements AbstractCodeBornApi {

    public Object codeBorn(Set<FloretBean> fBeans, List<File> templateFiles, String bornPath, String charset){
        if(CollectionUtils.isEmpty(fBeans)){
            return null;
        }
        if(CollectionUtils.isEmpty(templateFiles)){
            return null;
        }
        if(StringUtils.isBlank(charset)){
            charset = Settings.CHARSET;
        }
        bornPath = StringUtils.isBlank(bornPath)? "." : (bornPath.endsWith("/")? bornPath.substring(0, bornPath.length()-1) : bornPath);
        FPrintUtil.info("[配置信息]\t 生成路径: " + bornPath);
        FPrintUtil.info("[配置信息]\t 字符编码: " + charset);
        // 针对没一个映射模板进行模板文件注入
        for(FloretBean bean: fBeans){
            FPrintUtil.info("[构造对象]\t bean: " + JSON.toJSONString(bean));
            try{
                for(File f: templateFiles){
                    String fPath = f.getCanonicalPath();
                    FPrintUtil.info("[模板文件]\t 模板名称: " + fPath);
                    if(fPath.contains("classes")){
                        fPath = fPath.substring(fPath.lastIndexOf("classes")+"classes".length(), fPath.length());
                    }
                    fPath = fPath.replaceAll(Settings.buildPlaceHold(PathPHoldEnum.packageName), bean.getPackageName().replaceAll("\\.","/"))
                            .replaceAll(Settings.buildPlaceHold(PathPHoldEnum.className), bean.getClassName())
                            .replaceAll(Settings.buildPlaceHold(PathPHoldEnum.tableName), StringUtils.isNotBlank(bean.getTableName())? bean.getTableName(): NameUtil.camelToUnderline(bean.getClassName()));
                    String targetFs = bornPath + fPath;
                    FPrintUtil.info("[生成文件]\t 文件名称: " + targetFs);
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("bean", bean);
                    map.put("beans", fBeans);

                    if(fPath.contains("assets") || fPath.contains("Assets")) {
                        FileUtils.copyFile(f, new File(bornPath + fPath));
                    }else{
                        templateRender(FileUtils.readFileToString(f), map, new File(targetFs), charset);
                    }
                }
            }catch (Exception e){
                FPrintUtil.error(e.getLocalizedMessage(), e);
            }
        }
        return "Have Fun ! Bug connection with -> pudongping@meitua.com";
    }

    /**
     * 渲染模板
     * @param template
     * @param data
     * @param destFile
     * @param charset
     */
    public void templateRender(String template, Map<String, Object> data, File destFile, String charset){
        try {
            StringTemplateResourceLoader resourceLoader = new StringTemplateResourceLoader();
            Configuration cfg = Configuration.defaultConfiguration();
            Map<String, String> fnPkgMap = cfg.getFnPkgMap();
            // 扩展自定义函数
            fnPkgMap.put("nameUtil", "com.wuyu.plugin.floret.util.NameUtil");
            cfg.setFnPkgMap(fnPkgMap);
            GroupTemplate gt = new GroupTemplate(resourceLoader, cfg);
            gt.setSharedVars(data);
            Template t = gt.getTemplate(template);
            String str = t.render();
            FileUtils.write(destFile, str, charset);

        }catch (Exception e){
            System.err.println("---->" + destFile.getPath());
            e.printStackTrace();
        }
    }

}

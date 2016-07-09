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

import com.wuyu.plugin.floret.api.AbstractTemplateScanApi;
import com.wuyu.plugin.floret.setting.Settings;
import com.wuyu.plugin.floret.util.FPrintUtil;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Copyright (c) 2016, All Rights Reserved.
 * Author: dennisit@163.com / 苏若年
 */
public class FloretTemplateScanPlugin implements AbstractTemplateScanApi {


    /**
     * 扫描模板路径,根据后缀过来模板文件
     * @param locationPattern
     * @param acceptSuffix
     * @return
     */
    public List<File> scanTemplate(String locationPattern, List<String> acceptSuffix) {
        if(CollectionUtils.isEmpty(acceptSuffix)){
            acceptSuffix = Settings.ACCEPT_SUFFIX;
        }
        List<File> result = new ArrayList<File>();
        FPrintUtil.info("[模板扫描]\t 扫描规则: " + locationPattern);
        FPrintUtil.info("[模板扫描]\t 过滤规则: " + acceptSuffix);
        List<Resource> list = scanResources(locationPattern);
        if(null != list){
            for(Resource r : list){
                try {
                    if(r.getFile().isDirectory()){
                        continue;
                    }
                    if(acceptSuffix.contains(".".concat(FilenameUtils.getExtension(r.getFilename())))){
                        result.add(r.getFile());
                    }
                } catch (Exception e) {
                    FPrintUtil.error(e.getLocalizedMessage(), e);
                }
            }
            return result;
        }
        return null;
    }

    /**
     * 路径
     * @param locationPattern
     * @return
     */
    public List<Resource> scanResources(String locationPattern){
        try {
            if(StringUtils.isBlank(locationPattern)){
                locationPattern = Settings.LOCATION_PATTERN;
            }
            GenericApplicationContext context = new GenericApplicationContext();
            Resource rs[] = context.getResources(locationPattern);
            if(null!=rs && rs.length > 0){
                return Arrays.asList(rs);
            }
        }catch (Exception e){
            FPrintUtil.error(e.getMessage(), e);
        }
        return null;
    }


}

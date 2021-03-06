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
package ${bean.packageName}.controller;

import com.alibaba.fastjson.JSON;
import ${bean.packageName}.domain.${bean.className};
import ${bean.packageName}.service.${bean.className}Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.Set;

/**
 *
 */
@Controller
@RequestMapping(value = "/${nameUtil.firstToLower(bean.className)}")
public class ${bean.className}Controller {

    private static final Logger LOG = LoggerFactory.getLogger(${bean.className}Controller.class);

    @Resource
    private ${bean.className}Service ${nameUtil.firstToLower(bean.className)}Service;

    @ResponseBody
    @RequestMapping(value = "/insert", method = {RequestMethod.GET, RequestMethod.POST})
    public Object insert(@RequestBody ${bean.className} ${nameUtil.firstToLower(bean.className)}){
        LOG.info("insert\t tmcOrder: {}", JSON.toJSONString(${nameUtil.firstToLower(bean.className)}));
        return ${nameUtil.firstToLower(bean.className)}Service.insert(${nameUtil.firstToLower(bean.className)});
    }

    @ResponseBody
    @RequestMapping(value = "/update", method = {RequestMethod.GET, RequestMethod.POST})
    public Object update(@RequestBody ${bean.className} ${nameUtil.firstToLower(bean.className)}){
        LOG.info("update\t tmcOrder: {}", JSON.toJSONString(${nameUtil.firstToLower(bean.className)}));
        return ${nameUtil.firstToLower(bean.className)}Service.update(${nameUtil.firstToLower(bean.className)});
    }

    @ResponseBody
    @RequestMapping(value = "/delete", method = {RequestMethod.GET, RequestMethod.POST})
    public Object delete(@RequestParam(name = "id", required = false, defaultValue = "") long id){
        LOG.info("delete\t id: {}", id);
        return ${nameUtil.firstToLower(bean.className)}Service.delete(id);
    }

    @ResponseBody
    @RequestMapping(value = "/delete/multi", method = {RequestMethod.GET, RequestMethod.POST})
    public Object delete(@RequestParam(name = "ids", required = false, defaultValue = "") Set<Long> ids){
        LOG.info("delete\t ids: {}", ids);
        return ${nameUtil.firstToLower(bean.className)}Service.deleteMulti(ids);
    }

    @ResponseBody
    @RequestMapping(value = "/select", method = {RequestMethod.GET, RequestMethod.POST})
    public Object select(@RequestParam(name = "id", required = false, defaultValue = "")long id){
        LOG.info("select\t id: {}", id);
        return ${nameUtil.firstToLower(bean.className)}Service.select(id);
    }

    @ResponseBody
    @RequestMapping(value = "/select/multi", method = {RequestMethod.GET, RequestMethod.POST})
    public Object select(@RequestParam(name = "ids", required = false, defaultValue = "")Set<Long> ids){
        LOG.info("select\t ids:{}", ids);
        return ${nameUtil.firstToLower(bean.className)}Service.selectMulti(ids);
    }

    @ResponseBody
    @RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
    public Object list(@ModelAttribute ${bean.className} query,
                       @RequestParam(name = "page", required = false, defaultValue = "1") long pageNum,
                       @RequestParam(name = "size", required = false, defaultValue = "20") int pageSize){
        LOG.info("list\t ${nameUtil.firstToLower(bean.className)}{}, pageNum:{}, pageSize:{}", JSON.toJSONString(query), pageNum, pageSize);
        return ${nameUtil.firstToLower(bean.className)}Service.selectPage(query, pageNum, pageSize);
    }

}

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

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户登录授权
 */
@Controller
public class AuthorizSignController {

    @ResponseBody
    @RequestMapping(value = "/settings")
    public Object settings(){
        Map<String, Object> context = new HashMap<String, Object>();
        context.put("title", "管理后台");
        return context;
    }

    /**
     * 登入系统
     * @return
     */
    @RequestMapping(value = "/signIn")
    public String signIn(){
        return "theme/index.html";
    }

    /**
     * 登出系统
     * @return
     */
    @RequestMapping(value = "/signOut")
    public String signOut(){
        return "login.html";
    }

}
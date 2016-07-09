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
package com.wuyu.plugin.floret.main;

import com.wuyu.plugin.floret.plugin.FloretBeanAnnScanPlugin;
import com.wuyu.plugin.floret.plugin.FloretCodeBornPlugin;
import com.wuyu.plugin.floret.plugin.FloretTemplateScanPlugin;
import com.wuyu.plugin.floret.bean.FloretBean;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.util.List;
import java.util.Set;

/**
 * Copyright (c) 2016, All Rights Reserved.
 * Author: dennisit@163.com / 苏若年
 */
public class FloretStrap {


    public Object scanAnnoBorn(String locationPattern, List<String> acceptSuffix, String annoScan, String bornPath, String charset) {
        Set<FloretBean> fBeans = new FloretBeanAnnScanPlugin().scanFrameBean(annoScan);
        if(CollectionUtils.isEmpty(fBeans)){
            return null;
        }
        List<File> templateFiles = new FloretTemplateScanPlugin().scanTemplate(locationPattern, acceptSuffix);
        if(CollectionUtils.isEmpty(templateFiles)){
            return null;
        }
        return new FloretCodeBornPlugin().codeBorn(fBeans, templateFiles, bornPath, charset);
    }

}

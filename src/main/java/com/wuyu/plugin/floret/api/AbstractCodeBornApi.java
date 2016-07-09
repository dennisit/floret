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
package com.wuyu.plugin.floret.api;


import com.wuyu.plugin.floret.bean.FloretBean;

import java.io.File;
import java.util.List;
import java.util.Set;

/**
 * Copyright (c) 2016, All Rights Reserved.
 * Author: dennisit@163.com / 苏若年
 */
public interface AbstractCodeBornApi {


    /**
     * 代码生成规范
     * @param fBeans
     * @param templateFiles
     * @param bornPath
     * @param charset
     * @return
     */
    public Object codeBorn(Set<FloretBean> fBeans, List<File> templateFiles, String bornPath, String charset);

}

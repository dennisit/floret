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
package com.wuyu.plugin.floret.anno;

import org.springframework.stereotype.Component;
import java.lang.annotation.*;

/**
 * Copyright (c) 2016, All Rights Reserved.
 * Author: dennisit@163.com / 苏若年
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface FloretField {

    /**
     * 是否是主键
     * @return
     */
    boolean isPrimary() default false;

    /**
     * 表字段列名称
     * @return
     */
    String columnName() default "";

    /**
     * 映射时是否去掉
     * @return
     */
    boolean isExclude() default false;

    /**
     * 改属性用户前端模板, 可以和表字段说明(comment)保持一致
     * @return
     */
    String displayName() default "";

    /**
     * 是否列表展示
     * @return
     */
    boolean isListShow() default true;

    /**
     * 是否编辑列表展示
     * @return
     */
    boolean isEditShow() default true;

    /**
     * 是否搜索展示
     * @return
     */
    boolean isSearchShow() default false;
}

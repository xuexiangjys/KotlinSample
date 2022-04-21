/*
 * Copyright (C) 2022 xuexiangjys(xuexiangjys@163.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.xuexiang.kotlinsample.fragment.coroutines.concurrent.entity

/**
 * 产品促销信息
 *
 * @author xuexiang
 * @since 3/22/22 1:12 AM
 */
class PromotionInfo(var id: String) {
    /**
     * 促销类型
     */
    var type = 0
    /**
     * 促销内容
     */
    var content: String? = null
    /**
     * 生效日期
     */
    var effectiveDate: String? = null
    /**
     * 失效日期
     */
    var expirationDate: String? = null

    override fun toString(): String {
        return ("促销编号:" + id
                + ", 促销类型:" + type
                + ", 促销内容:" + content
                + ", 生效日期:" + effectiveDate
                + ", 失效日期:" + expirationDate)
    }
}
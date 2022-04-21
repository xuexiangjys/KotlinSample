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

import com.xuexiang.kotlinsample.fragment.coroutines.concurrent.entity.PriceInfo

/**
 * 价格信息
 *
 * @author xuexiang
 * @since 3/22/22 1:10 AM
 */
class PriceInfo(var id: String) {
    /**
     * 出厂价
     */
    var factoryPrice = 0f
    /**
     * 批发价
     */
    var wholesalePrice = 0f
    /**
     * 零售价
     */
    var retailPrice = 0f

    override fun toString(): String {
        return ("价格编号:" + id
                + ", 出厂价:" + factoryPrice
                + ", 批发价:" + wholesalePrice
                + ", 零售价:" + retailPrice)
    }
}
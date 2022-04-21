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
 * 工厂生产信息
 *
 * @author xuexiang
 * @since 3/22/22 1:11 AM
 */
class FactoryInfo(var id: String) {
    /**
     * 生产地址
     */
    var address: String? = null

    /**
     * 生产日期
     */
    var productDate: String? = null

    /**
     * 过期日期
     */
    var expirationDate: String? = null

    override fun toString(): String {
        return ("生产编号:" + id
                + ", 生产地址:" + address
                + ", 生产日期:" + productDate
                + ", 过期日期:" + expirationDate)
    }
}
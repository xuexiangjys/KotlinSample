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
package com.xuexiang.kotlinsample.fragment.coroutines.business.entity

/**
 * 产品
 *
 * @author xuexiang
 * @since 2/25/22 1:17 AM
 */
class Product(var info: ProductInfo, var address: String) {

    var price: String? = null
    var publicTime: String? = null

    override fun toString(): String {
        return "产品信息:$info, 产地:$address, 价格:$price, 上市日前:$publicTime"
    }
}
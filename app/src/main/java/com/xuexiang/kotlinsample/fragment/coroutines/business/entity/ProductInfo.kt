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
 * 产品信息
 *
 * @author xuexiang
 * @since 2/25/22 12:40 AM
 */
class ProductInfo {
    /**
     * 编号
     */
    var id: String

    /**
     * 品牌
     */
    var brand: String? = null

    /**
     * 质量
     */
    var quality: String? = null

    constructor(id: String) {
        this.id = id
    }

    constructor(id: String, brand: String?, quality: String?) {
        this.id = id
        this.brand = brand
        this.quality = quality
    }

    override fun toString(): String {
        return "id:$id, 品牌:$brand, 品质:$quality"
    }
}
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
 * 产品
 *
 * @author xuexiang
 * @since 3/22/22 1:07 AM
 */
class Product(briefInfo: BriefInfo) : BriefInfo(briefInfo) {
    /**
     * 生产信息
     */
    var factory: FactoryInfo? = null
    /**
     * 价格信息
     */
    var price: PriceInfo? = null
    /**
     * 促销信息
     */
    var promotion: PromotionInfo? = null
    /**
     * 富文本信息
     */
    var rich: RichInfo? = null


    override fun toString(): String {
        return """
            产品信息: $name
            产地信息: $factory
            价格信息: $price
            促销信息: $promotion
            富文本信息: $rich
            """.trimIndent()
    }
}
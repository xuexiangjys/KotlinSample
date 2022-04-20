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
package com.xuexiang.kotlinsample.fragment.coroutines.business.processor

import com.xuexiang.kotlinsample.fragment.coroutines.business.entity.ProductFactory
import com.xuexiang.kotlinsample.fragment.coroutines.business.entity.ProductInfo
import com.xuexiang.xui.widget.textview.LoggerTextView

/**
 * 2.查找相关的工厂
 *
 * @author xuexiang
 * @since 2/25/22 1:13 AM
 */
class SearchFactoryProcessor(logger: LoggerTextView?, private val info: ProductInfo) :
    AbstractProcessor<ProductFactory?>(logger) {
    override fun process(): ProductFactory {
        log("[查找相关工厂]开始执行...")
        val factory = searchFactoryByBrand(info.brand)
        log("[查找相关工厂]执行完毕!")
        onProcessSuccess(factory)
        return factory
    }

    private fun searchFactoryByBrand(brand: String?): ProductFactory {
        // 模拟耗费的时间
        mockProcess(300)
        return ProductFactory(brand!!, "南京市江宁区秣陵街道xxx街区")
    }
}
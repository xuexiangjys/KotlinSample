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

import com.xuexiang.kotlinsample.fragment.coroutines.business.entity.Product
import com.xuexiang.xui.widget.textview.LoggerTextView

/**
 * 4.产品发布
 *
 * @author xuexiang
 * @since 2/25/22 1:50 AM
 */
class PublicProductProcessor : AbstractProcessor<Product?> {
    private var product: Product? = null

    constructor(logger: LoggerTextView?, product: Product?) : super(logger) {
        this.product = product
    }

    constructor(logger: LoggerTextView?) : super(logger)

    override fun process(): Product {
        log("[产品发布]开始执行...")
        val time = getPublicPlan(product)
        product!!.publicTime = time
        log("[产品发布]执行完毕!")
        onProcessSuccess(product)
        return product!!
    }

    private fun getPublicPlan(product: Product?): String {
        // 模拟耗费的时间
        mockProcess(400)
        return "2022年2月22日22时22分22秒"
    }
}
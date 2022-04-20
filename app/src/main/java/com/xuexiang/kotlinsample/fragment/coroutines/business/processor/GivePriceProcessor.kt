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
 * 3.评估产品，给出价格
 * @author xuexiang
 * @since 2/25/22 1:36 AM
 */
class GivePriceProcessor(logger: LoggerTextView?, private val product: Product) :
    AbstractProcessor<Product?>(logger) {
    override fun process(): Product {
        log("[评估产品价格]开始执行...")
        val price = givePrice(product)
        product.price = price
        log("[评估产品价格]执行完毕!")
        onProcessSuccess(product)
        return product
    }

    private fun givePrice(product: Product): String {
        // 模拟耗费的时间
        mockProcess(100)
        return "45¥"
    }
}
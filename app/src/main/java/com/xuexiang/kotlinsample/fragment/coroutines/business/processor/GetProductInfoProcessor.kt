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

import com.xuexiang.kotlinsample.fragment.coroutines.business.entity.ProductInfo
import com.xuexiang.xui.widget.textview.LoggerTextView

/**
 * 1.获取产品信息
 *
 * @author xuexiang
 * @since 2/25/22 12:34 AM
 */
class GetProductInfoProcessor(logger: LoggerTextView?, private val id: String) :
    AbstractProcessor<ProductInfo?>(logger) {

    override fun process(): ProductInfo {
        log("[获取产品信息]开始执行...")
        val info = getProductInfoById(id)
        log("[获取产品信息]执行完毕!")
        onProcessSuccess(info)
        return info
    }

    private fun getProductInfoById(id: String): ProductInfo {
        // 模拟耗费的时间
        mockProcess(500)
        return ProductInfo(id, "品牌A", "高品质")
    }
}
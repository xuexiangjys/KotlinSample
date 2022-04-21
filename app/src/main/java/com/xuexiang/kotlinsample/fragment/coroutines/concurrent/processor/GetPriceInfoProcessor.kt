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
package com.xuexiang.kotlinsample.fragment.coroutines.concurrent.processor

import com.xuexiang.kotlinsample.fragment.coroutines.business.processor.AbstractProcessor
import com.xuexiang.kotlinsample.fragment.coroutines.concurrent.entity.PriceInfo
import com.xuexiang.xui.widget.textview.LoggerTextView

/**
 * 2.2 获取商品的价格信息
 *
 * @author xuexiang
 * @since 3/22/22 1:20 AM
 */
class GetPriceInfoProcessor(logger: LoggerTextView?, private val priceId: String) :
    AbstractProcessor<PriceInfo?>(logger) {
    override fun process(): PriceInfo {
        log("[价格信息查询]开始执行...")
        val info: PriceInfo = getPriceInfoById(priceId)
        log("[价格信息查询]执行完毕!")
        onProcessSuccess(info)
        return info
    }

    private fun getPriceInfoById(id: String): PriceInfo {
        // 模拟耗费的时间
        mockProcess(300)
        return PriceInfo(id).apply {
            factoryPrice = 1.5f
            wholesalePrice = 2.5f
            retailPrice = 4.5f
        }
    }
}
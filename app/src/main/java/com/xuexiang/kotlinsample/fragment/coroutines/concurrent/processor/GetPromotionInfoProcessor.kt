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
import com.xuexiang.kotlinsample.fragment.coroutines.concurrent.entity.PromotionInfo
import com.xuexiang.xui.widget.textview.LoggerTextView

/**
 * 2.3 获取商品的促销信息
 *
 * @author xuexiang
 * @since 3/22/22 1:23 AM
 */
class GetPromotionInfoProcessor(logger: LoggerTextView?, private val promotionId: String) :
    AbstractProcessor<PromotionInfo?>(logger) {
    override fun process(): PromotionInfo {
        log("[促销信息查询]开始执行...")
        val info: PromotionInfo = getPromotionInfoById(promotionId)
        log("[促销信息查询]执行完毕!")
        onProcessSuccess(info)
        return info
    }

    private fun getPromotionInfoById(id: String): PromotionInfo {
        // 模拟耗费的时间
        mockProcess(150)
        return PromotionInfo(id).apply {
            type = 5
            content = "买一送一"
            effectiveDate = "2022年3月15日"
            expirationDate = "2022年4月15日"
        }
    }
}
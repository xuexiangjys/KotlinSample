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
import com.xuexiang.kotlinsample.fragment.coroutines.concurrent.entity.BriefInfo
import com.xuexiang.xui.widget.textview.LoggerTextView

/**
 * 1 获取商品简要信息
 *
 * @author xuexiang
 * @since 3/22/22 1:18 AM
 */
class GetBriefInfoProcessor(logger: LoggerTextView?, private val productId: String) :
    AbstractProcessor<BriefInfo?>(logger) {
    override fun process(): BriefInfo {
        log("[简要信息查询]开始执行...")
        val info = getBriefInfoById(productId)
        log("[简要信息查询]执行完毕!")
        onProcessSuccess(info)
        return info
    }

    private fun getBriefInfoById(id: String): BriefInfo {
        // 模拟耗费的时间
        mockProcess(500)
        return BriefInfo(id).apply {
            name = "统一老坛酸菜牛肉面"
            factoryId = "fa234632-1234-4567"
            priceId = "pr432359-3745-9426"
            promotionId = "pt235123-9654-2942"
            richId = "ri735294-2346-1048"
        }
    }
}
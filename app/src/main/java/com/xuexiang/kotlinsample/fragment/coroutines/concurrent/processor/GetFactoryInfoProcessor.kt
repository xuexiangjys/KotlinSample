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
import com.xuexiang.kotlinsample.fragment.coroutines.concurrent.entity.FactoryInfo
import com.xuexiang.xui.widget.textview.LoggerTextView

/**
 * 2.1 获取商品的生产信息
 *
 * @author xuexiang
 * @since 3/22/22 1:22 AM
 */
class GetFactoryInfoProcessor(logger: LoggerTextView?, private val factoryId: String) :
    AbstractProcessor<FactoryInfo?>(logger) {
    override fun process(): FactoryInfo {
        log("[生产信息查询]开始执行...")
        val info = getFactoryInfoById(
            factoryId
        )
        log("[生产信息查询]执行完毕!")
        onProcessSuccess(info)
        return info
    }

    private fun getFactoryInfoById(id: String): FactoryInfo {
        // 模拟耗费的时间
        mockProcess(200)
        return FactoryInfo(id).apply {
            address = "长沙市开福区金霞经济开发区中青路1301号长沙统一企业有限公司"
            productDate = "2022年3月15日"
            expirationDate = "2030年3月15日"
        }
    }
}
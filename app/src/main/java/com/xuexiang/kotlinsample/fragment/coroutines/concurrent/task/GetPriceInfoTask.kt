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
package com.xuexiang.kotlinsample.fragment.coroutines.concurrent.task

import com.xuexiang.kotlinsample.fragment.coroutines.business.processor.AbstractProcessor
import com.xuexiang.kotlinsample.fragment.coroutines.business.task.AbstractTask
import com.xuexiang.kotlinsample.fragment.coroutines.business.task.ProductTaskConstants
import com.xuexiang.kotlinsample.fragment.coroutines.concurrent.entity.PriceInfo
import com.xuexiang.kotlinsample.fragment.coroutines.concurrent.entity.Product
import com.xuexiang.kotlinsample.fragment.coroutines.concurrent.processor.GetPriceInfoProcessor
import com.xuexiang.xtask.core.param.ITaskResult
import com.xuexiang.xui.widget.textview.LoggerTextView
import com.xuexiang.xutil.common.StringUtils

/**
 * 2.2 获取商品的价格信息
 *
 * @author xuexiang
 * @since 3/23/22 12:18 AM
 */
class GetPriceInfoTask(logger: LoggerTextView?) : AbstractTask(logger) {
    @Throws(Exception::class)
    override fun doTask() {
        val product: Product? =
            taskParam.getObject(ProductTaskConstants.KEY_PRODUCT, Product::class.java)
        if (product == null || StringUtils.isEmpty(product.priceId)) {
            notifyTaskFailed(ITaskResult.ERROR, "product is null or priceId is empty!")
            return
        }
        GetPriceInfoProcessor(mLogger, product.priceId!!)
            .setProcessorCallback(object : AbstractProcessor.IProcessorCallback<PriceInfo?> {
                override fun onSuccess(priceInfo: PriceInfo?) {
                    taskParam.put(
                        ProductTaskConstants.KEY_PRODUCT,
                        product.apply { price = priceInfo })
                    notifyTaskSucceed()
                }

                override fun onFailed(error: String?) {
                    notifyTaskFailed(ITaskResult.ERROR, error)
                }
            })
            .process()
    }

    override fun getName(): String {
        return "2.2 获取商品的价格信息"
    }
}
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
import com.xuexiang.kotlinsample.fragment.coroutines.concurrent.entity.BriefInfo
import com.xuexiang.kotlinsample.fragment.coroutines.concurrent.entity.Product
import com.xuexiang.kotlinsample.fragment.coroutines.concurrent.processor.GetBriefInfoProcessor
import com.xuexiang.xtask.core.param.ITaskResult
import com.xuexiang.xui.widget.textview.LoggerTextView

/**
 * 获取商品简要信息
 *
 * @author xuexiang
 * @since 3/22/22 11:52 PM
 */
class GetBriefInfoTask(logger: LoggerTextView?) : AbstractTask(logger) {

    @Throws(Exception::class)
    override fun doTask() {
        val productId = taskParam.getString(ProductTaskConstants.KEY_PRODUCT_ID)
        GetBriefInfoProcessor(mLogger, productId)
            .setProcessorCallback(object : AbstractProcessor.IProcessorCallback<BriefInfo?> {
                override fun onSuccess(briefInfo: BriefInfo?) {
                    val product = Product(briefInfo!!)
                    taskParam.put(ProductTaskConstants.KEY_PRODUCT, product)
                    notifyTaskSucceed()
                }

                override fun onFailed(error: String?) {
                    notifyTaskFailed(ITaskResult.ERROR, error)
                }
            })
            .process()
    }

    override fun getName(): String {
        return "1.获取商品简要信息"
    }
}
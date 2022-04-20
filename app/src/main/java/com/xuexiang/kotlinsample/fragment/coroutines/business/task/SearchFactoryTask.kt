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
package com.xuexiang.kotlinsample.fragment.coroutines.business.task

import com.xuexiang.kotlinsample.fragment.coroutines.business.entity.Product
import com.xuexiang.kotlinsample.fragment.coroutines.business.entity.ProductFactory
import com.xuexiang.kotlinsample.fragment.coroutines.business.entity.ProductInfo
import com.xuexiang.kotlinsample.fragment.coroutines.business.processor.AbstractProcessor
import com.xuexiang.kotlinsample.fragment.coroutines.business.processor.SearchFactoryProcessor
import com.xuexiang.xtask.core.param.ITaskResult
import com.xuexiang.xui.widget.textview.LoggerTextView

/**
 * 2.查找相关的工厂并生产
 *
 * @author xuexiang
 * @since 2/25/22 2:17 AM
 */
class SearchFactoryTask(logger: LoggerTextView?) : AbstractTask(logger) {

    @Throws(Exception::class)
    override fun doTask() {
        val productInfo: ProductInfo = taskParam.getObject(
            ProductTaskConstants.KEY_PRODUCT_INFO,
            ProductInfo::class.java
        )
        SearchFactoryProcessor(mLogger, productInfo)
            .setProcessorCallback(object : AbstractProcessor.IProcessorCallback<ProductFactory?> {
                override fun onSuccess(factory: ProductFactory?) {
                    log("开始生产产品...")
                    val product: Product? = factory?.produce(productInfo)
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
        return "2.查找相关的工厂并生产"
    }
}
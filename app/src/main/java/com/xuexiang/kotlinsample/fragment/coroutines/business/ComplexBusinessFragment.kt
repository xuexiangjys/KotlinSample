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
package com.xuexiang.kotlinsample.fragment.coroutines.business

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.xuexiang.kotlinsample.R
import com.xuexiang.kotlinsample.core.BaseFragment
import com.xuexiang.kotlinsample.databinding.FragmentUsecaseCompareBinding
import com.xuexiang.kotlinsample.fragment.coroutines.business.entity.Product
import com.xuexiang.kotlinsample.fragment.coroutines.business.entity.ProductFactory
import com.xuexiang.kotlinsample.fragment.coroutines.business.entity.ProductInfo
import com.xuexiang.kotlinsample.fragment.coroutines.business.processor.AbstractProcessor.ProcessorCallbackAdapter
import com.xuexiang.kotlinsample.fragment.coroutines.business.processor.GetProductInfoProcessor
import com.xuexiang.kotlinsample.fragment.coroutines.business.processor.GivePriceProcessor
import com.xuexiang.kotlinsample.fragment.coroutines.business.processor.PublicProductProcessor
import com.xuexiang.kotlinsample.fragment.coroutines.business.processor.SearchFactoryProcessor
import com.xuexiang.kotlinsample.fragment.coroutines.business.task.*
import com.xuexiang.xaop.annotation.SingleClick
import com.xuexiang.xpage.annotation.Page
import com.xuexiang.xtask.XTask
import com.xuexiang.xtask.core.ITaskChainEngine
import com.xuexiang.xtask.core.param.ITaskResult
import com.xuexiang.xtask.core.param.impl.TaskParam
import com.xuexiang.xtask.core.step.impl.TaskChainCallbackAdapter
import com.xuexiang.xutil.system.AppExecutors
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

/**
 * 复杂业务流程处理
 *
 * 案例[高仿网红产品]的流程如下：
 * 1.获取产品信息 -> 2.查询可生产的工厂 -> 3.联系工厂生产产品 -> 4.送去市场部门评估售价 -> 5.产品上市
 *
 * @author xuexiang
 * @since 3/18/22 11:28 PM
 */
@Page(name = "复杂业务流程处理")
class ComplexBusinessFragment : BaseFragment<FragmentUsecaseCompareBinding?>() {

    override fun viewBindingInflate(
        inflater: LayoutInflater,
        container: ViewGroup
    ): FragmentUsecaseCompareBinding {
        return FragmentUsecaseCompareBinding.inflate(inflater, container, false)
    }

    private val productId = "123456"

    private val mainScope = MainScope()

    override fun initViews() {

    }

    override fun initListeners() {
        binding?.btnNormal?.setOnClickListener { view -> onViewClicked(view) }
        binding?.btnXtask?.setOnClickListener { view -> onViewClicked(view) }
        binding?.btnCoroutines?.setOnClickListener { view -> onViewClicked(view) }
        binding?.btnCoroutinesFlow?.setOnClickListener { view -> onViewClicked(view) }
    }

    @SingleClick
    fun onViewClicked(view: View) {
        clearLog()
        log("开始仿冒生产网红产品...")
        val startTime = System.currentTimeMillis()
        when (view.id) {
            R.id.btn_normal -> doBusinessNormal(startTime)
            R.id.btn_xtask -> doBusinessXTask(startTime)
            R.id.btn_coroutines -> doBusinessCoroutines(startTime)
            R.id.btn_coroutines_flow -> doBusinessCoroutinesFlow(startTime)
        }
    }

    /**
     * 普通的接口回调写法, 这里仅是演示模拟，实际的可能更复杂
     * 流程如下：
     * 1.获取产品信息 -> 2.查询可生产的工厂 -> 3.联系工厂生产产品 -> 4.送去市场部门评估售价 -> 5.产品上市
     */
    private fun doBusinessNormal(startTime: Long) {
        AppExecutors.get().singleIO().execute {
            // 1.获取产品信息
            GetProductInfoProcessor(binding?.logger, productId).setProcessorCallback(object :
                ProcessorCallbackAdapter<ProductInfo?>() {
                override fun onSuccess(productInfo: ProductInfo?) {
                    // 2.查询可生产的工厂
                    SearchFactoryProcessor(binding?.logger, productInfo!!).setProcessorCallback(
                        object : ProcessorCallbackAdapter<ProductFactory?>() {
                            override fun onSuccess(factory: ProductFactory?) {
                                // 3.联系工厂生产产品
                                log("开始生产产品...")
                                val product = factory?.produce(productInfo)
                                // 4.送去市场部门评估售价
                                GivePriceProcessor(binding?.logger, product!!).setProcessorCallback(
                                    object : ProcessorCallbackAdapter<Product?>() {
                                        override fun onSuccess(product: Product?) {
                                            // 5.产品上市
                                            PublicProductProcessor(
                                                binding?.logger,
                                                product
                                            ).setProcessorCallback(object :
                                                ProcessorCallbackAdapter<Product?>() {
                                                override fun onSuccess(product: Product?) {
                                                    log("总共耗时:" + (System.currentTimeMillis() - startTime) + "ms")
                                                    log("仿冒生产网红产品完成, $product")
                                                }
                                            }).process()
                                        }
                                    }).process()
                            }
                        }).process()
                }
            }).process()
        }
    }

    /**
     * XTask写法, 这里仅是演示模拟，实际的可能更复杂
     * 流程如下：
     * 1.获取产品信息 -> 2.查询可生产的工厂 -> 3.联系工厂生产产品 -> 4.送去市场部门评估售价 -> 5.产品上市
     */
    private fun doBusinessXTask(startTime: Long) {
        XTask.getTaskChain()
            .setTaskParam(TaskParam.get(ProductTaskConstants.KEY_PRODUCT_ID, productId)) // 1.获取产品信息
            .addTask(GetProductInfoTask(binding?.logger)) // 2.查询可生产的工厂, 3.联系工厂生产产品
            .addTask(SearchFactoryTask(binding?.logger)) // 4.送去市场部门评估售价
            .addTask(GivePriceTask(binding?.logger)) // 5.产品上市
            .addTask(PublicProductTask(binding?.logger))
            .setTaskChainCallback(object : TaskChainCallbackAdapter() {
                override fun onTaskChainCompleted(engine: ITaskChainEngine, result: ITaskResult) {
                    log("总共耗时:" + (System.currentTimeMillis() - startTime) + "ms")
                    val product = result.dataStore.getObject(
                        ProductTaskConstants.KEY_PRODUCT,
                        Product::class.java
                    )
                    log("仿冒生产网红产品完成, $product")
                }
            }).start()
    }

    /**
     * Coroutines写法, 这里仅是演示模拟，实际的可能更复杂
     * 流程如下：
     * 1.获取产品信息 -> 2.查询可生产的工厂 -> 3.联系工厂生产产品 -> 4.送去市场部门评估售价 -> 5.产品上市
     */
    private fun doBusinessCoroutines(startTime: Long) {
        mainScope.launch {
            val productInfo = withContext(Dispatchers.IO) {
                // 1.获取产品信息
                GetProductInfoProcessor(binding?.logger, productId).process()
            }
            val factory = withContext(Dispatchers.IO) {
                // 2.查询可生产的工厂
                SearchFactoryProcessor(binding?.logger, productInfo).process()
            }
            // 3.联系工厂生产产品
            log("开始生产产品...")
            var product = factory.produce(productInfo)
            product = withContext(Dispatchers.IO) {
                // 4.送去市场部门评估售价
                GivePriceProcessor(binding?.logger, product).process()
                // 5.产品上市
                PublicProductProcessor(binding?.logger, product).process()
            }
            log("总共耗时:" + (System.currentTimeMillis() - startTime) + "ms")
            log("仿冒生产网红产品完成, $product")
        }
    }

    /**
     * Coroutines flow写法, 这里仅是演示模拟，实际的可能更复杂
     * 流程如下：
     * 1.获取产品信息 -> 2.查询可生产的工厂 -> 3.联系工厂生产产品 -> 4.送去市场部门评估售价 -> 5.产品上市
     */
    private fun doBusinessCoroutinesFlow(startTime: Long) {
        mainScope.launch {
            flowOf(productId)
                .map { id ->
                    // 1.获取产品信息
                    GetProductInfoProcessor(binding?.logger, id).process()
                }
                .map { productInfo ->
                    // 2.查询可生产的工厂
                    SearchFactoryProcessor(binding?.logger, productInfo).process() to productInfo
                }
                .map { pair ->
                    // 3.联系工厂生产产品
                    log("开始生产产品...")
                    val product = pair.first.produce(pair.second)
                    // 4.送去市场部门评估售价
                    GivePriceProcessor(binding?.logger, product).process()
                }.map { product ->
                    // 5.产品上市
                    PublicProductProcessor(binding?.logger, product).process()
                }.flowOn(Dispatchers.IO)
                .collect { product ->
                    log("总共耗时:" + (System.currentTimeMillis() - startTime) + "ms")
                    log("仿冒生产网红产品完成, $product")
                }
        }
    }


    fun log(logContent: String?) {
        binding?.logger?.logSuccess(logContent)
    }

    private fun clearLog() {
        binding?.logger?.clearLog()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        XTask.cancelAllTaskChain()
        mainScope.cancel()
    }

}
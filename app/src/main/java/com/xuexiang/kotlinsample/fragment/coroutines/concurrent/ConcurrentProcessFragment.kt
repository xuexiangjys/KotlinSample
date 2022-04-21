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
package com.xuexiang.kotlinsample.fragment.coroutines.concurrent

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.xuexiang.kotlinsample.R
import com.xuexiang.kotlinsample.core.BaseFragment
import com.xuexiang.kotlinsample.databinding.FragmentUsecaseCompareBinding
import com.xuexiang.kotlinsample.fragment.coroutines.business.processor.AbstractProcessor
import com.xuexiang.kotlinsample.fragment.coroutines.business.task.ProductTaskConstants
import com.xuexiang.kotlinsample.fragment.coroutines.concurrent.entity.*
import com.xuexiang.kotlinsample.fragment.coroutines.concurrent.processor.*
import com.xuexiang.kotlinsample.fragment.coroutines.concurrent.task.*
import com.xuexiang.xaop.annotation.SingleClick
import com.xuexiang.xpage.annotation.Page
import com.xuexiang.xtask.XTask
import com.xuexiang.xtask.core.ITaskChainEngine
import com.xuexiang.xtask.core.ThreadType
import com.xuexiang.xtask.core.param.ITaskResult
import com.xuexiang.xtask.core.param.impl.TaskParam
import com.xuexiang.xtask.core.step.impl.TaskChainCallbackAdapter
import com.xuexiang.xutil.system.AppExecutors
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.util.concurrent.CountDownLatch

/**
 * 复杂并发任务处理
 *
 *
 * 案例[展示商品详细信息]的流程如下：
 *
 *
 * 1.根据商品的唯一号ID获取商品简要信息
 * 2.获取商品的详细信息：
 * 2.1 获取商品的生产信息
 * 2.2 获取商品的价格信息
 * 2.3 获取商品的促销信息
 * 2.4 获取商品的富文本信息
 * 3.进行商品信息的展示
 *
 * @author xuexiang
 * @since 3/21/22 10:57 PM
 */
@Page(name = "复杂并发任务处理")
class ConcurrentProcessFragment : BaseFragment<FragmentUsecaseCompareBinding?>() {

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
        log("开始查询商品信息...")
        val startTime = System.currentTimeMillis()
        when (view.id) {
            R.id.btn_normal -> queryInfoNormal(startTime, productId)
            R.id.btn_xtask -> queryInfoXTask(startTime, productId)
            R.id.btn_coroutines -> queryInfoCoroutines(startTime, productId)
            R.id.btn_coroutines_flow -> doBusinessCoroutinesFlow(startTime, productId)
        }
    }

    /**
     * 普通的接口回调写法, 这里仅是演示模拟，实际的可能更复杂
     */
    private fun queryInfoNormal(startTime: Long, productId: String) {
        AppExecutors.get().singleIO().execute {
            GetBriefInfoProcessor(binding?.logger, productId).setProcessorCallback(object :
                AbstractProcessor.ProcessorCallbackAdapter<BriefInfo?>() {
                override fun onSuccess(briefInfo: BriefInfo?) {
                    val product = Product(briefInfo!!)
                    val latch = CountDownLatch(4)

                    // 2.1 获取商品的生产信息
                    AppExecutors.get().networkIO().execute {
                        GetFactoryInfoProcessor(
                            binding?.logger,
                            product.factoryId!!
                        ).setProcessorCallback(object :
                            AbstractProcessor.ProcessorCallbackAdapter<FactoryInfo?>() {
                            override fun onSuccess(result: FactoryInfo?) {
                                product.factory = result
                                latch.countDown()
                            }
                        }).process()
                    }
                    // 2.2 获取商品的价格信息
                    AppExecutors.get().networkIO().execute {
                        GetPriceInfoProcessor(
                            binding?.logger,
                            product.priceId!!
                        ).setProcessorCallback(
                            object : AbstractProcessor.ProcessorCallbackAdapter<PriceInfo?>() {
                                override fun onSuccess(result: PriceInfo?) {
                                    product.price = result
                                    latch.countDown()
                                }
                            }).process()
                    }
                    // 2.3 获取商品的促销信息
                    AppExecutors.get().networkIO().execute {
                        GetPromotionInfoProcessor(
                            binding?.logger,
                            product.promotionId!!
                        ).setProcessorCallback(object :
                            AbstractProcessor.ProcessorCallbackAdapter<PromotionInfo?>() {
                            override fun onSuccess(result: PromotionInfo?) {
                                product.promotion = result
                                latch.countDown()
                            }
                        }).process()
                    }
                    // 2.4 获取商品的富文本信息
                    AppExecutors.get().networkIO().execute {
                        GetRichInfoProcessor(
                            binding?.logger,
                            product.richId!!
                        ).setProcessorCallback(
                            object : AbstractProcessor.ProcessorCallbackAdapter<RichInfo?>() {
                                override fun onSuccess(result: RichInfo?) {
                                    product.rich = result
                                    latch.countDown()
                                }
                            }).process()
                    }
                    try {
                        latch.await()
                        log("总共耗时:" + (System.currentTimeMillis() - startTime) + "ms")
                        log("查询商品信息完成, $product")
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                }
            }).process()
        }
    }


    /**
     * XTask写法, 这里仅是演示模拟，实际的可能更复杂
     */
    private fun queryInfoXTask(startTime: Long, productId: String) {
        XTask.getTaskChain()
            .setTaskParam(
                TaskParam.get(
                    ProductTaskConstants.KEY_PRODUCT_ID,
                    productId
                )
            ) // 1.获取商品简要信息
            .addTask(GetBriefInfoTask(binding?.logger))
            .addTask(
                XTask.getConcurrentGroupTask(ThreadType.SYNC) // 2.1 获取商品的生产信息
                    .addTask(GetFactoryInfoTask(binding?.logger)) // 2.2 获取商品的价格信息
                    .addTask(GetPriceInfoTask(binding?.logger)) // 2.3 获取商品的促销信息
                    .addTask(GetPromotionInfoTask(binding?.logger)) // 2.4 获取商品的富文本信息
                    .addTask(GetRichInfoTask(binding?.logger))
            )
            .setTaskChainCallback(object : TaskChainCallbackAdapter() {
                override fun onTaskChainCompleted(engine: ITaskChainEngine, result: ITaskResult) {
                    log("总共耗时:" + (System.currentTimeMillis() - startTime) + "ms")
                    val product: Product = result.dataStore.getObject(
                        ProductTaskConstants.KEY_PRODUCT,
                        Product::class.java
                    )
                    log("查询商品信息完成, $product")
                }
            }).start()
    }


    /**
     * Coroutines写法, 这里仅是演示模拟，实际的可能更复杂
     */
    private fun queryInfoCoroutines(startTime: Long, productId: String) {
        mainScope.launch {
            // 1.获取商品简要信息
            val briefInfo = withContext(Dispatchers.IO) {
                GetBriefInfoProcessor(binding?.logger, productId).process()
            }

            val product = Product(briefInfo)
            // 2.1 获取商品的生产信息
            val factory = async(Dispatchers.IO) {
                GetFactoryInfoProcessor(binding?.logger, product.factoryId!!).process()
            }
            // 2.2 获取商品的价格信息
            val price = async(Dispatchers.IO) {
                GetPriceInfoProcessor(binding?.logger, product.factoryId!!).process()
            }
            // 2.3 获取商品的促销信息
            val promotion = async(Dispatchers.IO) {
                GetPromotionInfoProcessor(binding?.logger, product.factoryId!!).process()
            }
            // 2.4 获取商品的富文本信息
            val rich = async(Dispatchers.IO) {
                GetRichInfoProcessor(binding?.logger, product.factoryId!!).process()
            }
            product.factory = factory.await()
            product.price = price.await()
            product.promotion = promotion.await()
            product.rich = rich.await()

            log("总共耗时:" + (System.currentTimeMillis() - startTime) + "ms")
            log("查询商品信息完成, $product")
        }
    }

    private fun doBusinessCoroutinesFlow(startTime: Long, productId: String) {
        mainScope.launch {
            flowOf(productId)
                .map { id ->
                    // 1.获取商品简要信息
                    GetBriefInfoProcessor(binding?.logger, id).process()
                }
                .map { briefInfo -> Product(briefInfo) }
                .flatMapMerge { product ->
                    // 2.1 获取商品的生产信息
                    flowFactory(product)
                        // 2.2 获取商品的价格信息
                        .zip(flowPrice(product)) { factoryInfo, priceInfo ->
                            product.apply {
                                factory = factoryInfo
                                price = priceInfo
                            }
                            // 2.3 获取商品的促销信息
                        }.zip(flowPromotion(product)) { _, promotionInfo ->
                            product.apply {
                                promotion = promotionInfo
                            }
                            // 2.4 获取商品的富文本信息
                        }.zip(flowRich(product)) { _, richInfo ->
                            product.apply {
                                rich = richInfo
                            }
                        }
                }.flowOn(Dispatchers.IO)
                .collect { product ->
                    log("总共耗时:" + (System.currentTimeMillis() - startTime) + "ms")
                    log("查询商品信息完成, $product")
                }
        }
    }

    @OptIn(FlowPreview::class)
    private fun flowFactory(product: Product) =
        {
            GetFactoryInfoProcessor(
                binding?.logger,
                product.factoryId!!
            ).process()
        }.asFlow().flowOn(Dispatchers.IO)

    @OptIn(FlowPreview::class)
    private fun flowPrice(product: Product) =
        {
            GetPriceInfoProcessor(
                binding?.logger,
                product.priceId!!
            ).process()
        }.asFlow().flowOn(Dispatchers.IO)

    @OptIn(FlowPreview::class)
    private fun flowRich(product: Product) =
        {
            GetRichInfoProcessor(
                binding?.logger,
                product.richId!!
            ).process()
        }.asFlow().flowOn(Dispatchers.IO)


    @OptIn(FlowPreview::class)
    private fun flowPromotion(product: Product) =
        {
            GetPromotionInfoProcessor(
                binding?.logger,
                product.promotionId!!
            ).process()
        }.asFlow().flowOn(Dispatchers.IO)

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
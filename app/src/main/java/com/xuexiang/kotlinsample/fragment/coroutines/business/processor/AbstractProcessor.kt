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

import com.xuexiang.xui.widget.textview.LoggerTextView

/**
 * 抽象处理器
 *
 * @author xuexiang
 * @since 2/25/22 12:28 AM
 */
abstract class AbstractProcessor<T>(private val mLogger: LoggerTextView?) {

    private var mCallback: IProcessorCallback<T>? = null

    fun setProcessorCallback(callback: IProcessorCallback<T>): AbstractProcessor<T> {
        mCallback = callback
        return this
    }

    /**
     * 处理任务
     *
     * @return 返回的结果
     */
    abstract fun process(): T

    fun log(logContent: String?) {
        mLogger?.logNormal(logContent)
    }

    fun onProcessSuccess(t: T) {
        mCallback?.onSuccess(t)
    }

    fun onProcessFailed(error: String?) {
        mCallback?.onFailed(error)
    }

    /**
     * 模拟执行
     *
     * @param time 模拟执行所需要的时间
     */
    fun mockProcess(time: Long) {
        try {
            Thread.sleep(time)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

    abstract class ProcessorCallbackAdapter<T> : IProcessorCallback<T> {
        override fun onFailed(error: String?) {}
    }

    interface IProcessorCallback<T> {
        /**
         * 处理成功
         *
         * @param result 结果
         */
        fun onSuccess(result: T)

        /**
         * 处理失败
         *
         * @param error 错误信息
         */
        fun onFailed(error: String?)
    }
}
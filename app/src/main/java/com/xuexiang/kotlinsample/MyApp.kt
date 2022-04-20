/*
 * Copyright (C) 2019 xuexiangjys(xuexiangjys@163.com)
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
package com.xuexiang.kotlinsample

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.xuexiang.kotlinsample.utils.sdkinit.UMengInit
import com.xuexiang.kotlinsample.utils.sdkinit.XBasicLibInit
import com.xuexiang.xtask.XTask

/**
 * @author xuexiang
 * @since 2018/11/7 下午1:12
 */
class MyApp : Application() {
    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        //解决4.x运行崩溃的问题
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        initLibs()

        // 设置XTask的调试模式
        XTask.debug(isDebug)
    }

    /**
     * 初始化基础库
     */
    private fun initLibs() {
        XBasicLibInit.init(this)
        UMengInit.init(this)
    }

    companion object {
        /**
         * @return 当前app是否是调试开发模式
         */
        val isDebug: Boolean
            get() = BuildConfig.DEBUG
    }
}
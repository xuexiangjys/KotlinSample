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
package com.xuexiang.kotlinsample.fragment

import android.util.Log
import com.xuexiang.kotlinsample.core.BaseSimpleListFragment
import com.xuexiang.xpage.annotation.Page
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * 协程使用
 *
 * @author xuexiang
 * @since 2022/3/25 1:42 上午
 */
@Page(name = "协程使用")
class CoroutinesFragment : BaseSimpleListFragment() {

    private val TAG = "CoroutinesFragment"

    override fun initSimpleData(lists: MutableList<String>): MutableList<String> {
        lists.add("简单使用")
        return lists
    }

    override fun onItemClick(position: Int) {
        when (position) {
            0 -> {
                GlobalScope.launch {
                    val result = doCoroutines()
                    Log.e(TAG, "${result},当前线程：${Thread.currentThread().name}")
                }
            }
            else -> {

            }
        }
    }

    private suspend fun doCoroutines(): String {
        //暂停两秒，模拟耗时操作
        delay(2000L)
        Log.e(TAG, "doCoroutines完成,当前线程：${Thread.currentThread().name}")
        return "成功执行doCoroutines"
    }

}
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

package com.xuexiang.kotlinsample.fragment.coroutines

import android.util.Log
import com.xuexiang.kotlinsample.core.BaseSimpleListFragment
import com.xuexiang.xpage.annotation.Page
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

/**
 * Flow必须在协程的环境下方可使用
 */
@Page(name = "协程Flow的使用")
class CoroutinesFlowFragment : BaseSimpleListFragment() {

    private val TAG = "Coroutines-Flow"

    private val mainScope = MainScope()

    override fun initSimpleData(lists: MutableList<String>): MutableList<String> {
        lists.add("Flow简单使用")
        return lists
    }


    override fun onItemClick(position: Int) {
        when (position) {
            0 -> {
                mainScope.launch {
                    flowOf(1, 2, 3)
                        .map { x ->
                            x * 2
                        }
                        .filter { x -> x > 3 }
                        .onEach { item ->
                            Log.e(TAG, "on each: $item, 当前线程：${Thread.currentThread().name}")
                            item + 1
                        }
                        .flowOn(Dispatchers.IO)
                        .onCompletion {
                            Log.e(TAG, "onCompletion, 当前线程：${Thread.currentThread().name}")
                        }.collect { value ->
                            Log.e(TAG, "collect: $value, 当前线程：${Thread.currentThread().name}")
                        }
                }

            }
            1 -> {

            }
        }
    }

    override fun onDestroyView() {
        mainScope.cancel()
        super.onDestroyView()
    }

}
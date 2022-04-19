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

/***
 * 1.使用withContext进行线程串行切换
 * 2.使用async进行线程并行切换
 */
@Page(name = "协程线程切换")
class SchedulerChangeFragment : BaseSimpleListFragment() {

    private val TAG = "Coroutines-Scheduler"

    private val mainScope = MainScope()

    override fun initSimpleData(lists: MutableList<String>): MutableList<String> {
        lists.add("1.使用withContext进行线程串行切换")
        lists.add("2.使用async进行线程并行切换")
        return lists
    }

    override fun onItemClick(position: Int) {
        when (position) {
            0 -> {
                mainScope.launch {
                    Log.e(TAG, "task start: ${Thread.currentThread().name}")
                    val result = withContext(Dispatchers.IO) {
                        delay(500)
                        Log.e(TAG, "delay on: ${Thread.currentThread().name}")
                        "task pass!"
                    }
                    Log.e(TAG, "task finished: ${Thread.currentThread().name}, result: $result")
                }
            }
            1 -> {
                mainScope.launch {
                    Log.e(TAG, "task start: ${Thread.currentThread().name}")
                    val task1 = async {
                        delay(100)
                        Log.e(TAG, "subtask1 on: ${Thread.currentThread().name}")
                        "subtask1 pass!"
                    }
                    val task2 = async(Dispatchers.IO) {
                        delay(200)
                        Log.e(TAG, "subtask2 on: ${Thread.currentThread().name}")
                        "subtask2 pass!"
                    }
                    val task3 = async(Dispatchers.Default) {
                        delay(300)
                        Log.e(TAG, "subtask3 on: ${Thread.currentThread().name}")
                        "subtask3 pass!"
                    }
                    Log.e(
                        TAG,
                        "task finished: ${Thread.currentThread().name}, result: ${task1.await()}-${task2.await()}-${task3.await()}"
                    )
                }
            }
        }
    }

    override fun onDestroyView() {
        mainScope.cancel()
        super.onDestroyView()
    }

}
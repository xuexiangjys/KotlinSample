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

/**
 * CoroutineContext - 协程上下文
 * Job: 控制协程的生命周期；
 * CoroutineDispatcher: 向合适的线程分发任务；
 * CoroutineName: 协程的名称，调试的时候很有用；
 * CoroutineExceptionHandler: 处理未被捕捉的异常。
 *
 * SupervisorJob是一个特殊的 Job，里面的子Job不相互影响，一个子Job失败了，不影响其他子Job的执行。
 *
 */
@Page(name = "协程基础使用")
class CoroutinesBasicFragment : BaseSimpleListFragment() {

    private val TAG = "Coroutines-Basic"

    var job: Job? = null

    var lazyJob: Job? = null

    private val mainScope = MainScope()

    override fun initSimpleData(lists: MutableList<String>): MutableList<String> {
        lists.add("1.使用 runBlocking 顶层函数（常用于单元测试）")
        lists.add("2.使用 GlobalScope 单例对象（不推荐使用）")
        lists.add("3.通过 CoroutineContext 创建一个 CoroutineScope 对象（推荐使用）")
        lists.add("启动模式【CoroutineStart.LAZY】")
        return lists
    }

    init {
        lazyJob = mainScope.launch(start = CoroutineStart.LAZY) {
            val result = doCoroutines()
            Log.e(TAG, "${result},当前线程：${Thread.currentThread().name}")
        }
    }

    override fun onItemClick(position: Int) {
        when (position) {
            0 -> {
                // 通常适用于单元测试的场景，而业务开发中不会用到这种方法，因为它是线程阻塞的
                runBlocking {
                    launch {
                        val result = doCoroutines()
                        Log.e(TAG, "${result},当前线程：${Thread.currentThread().name}")
                    }
                }
            }
            1 -> {
                job = GlobalScope.launch {
                    val result = doCoroutineScope()
                    Log.e(TAG, "${result},当前线程：${Thread.currentThread().name}")
                }
            }
            2 -> {
//                val jobScope = CoroutineScope(Job())
//                val ioScope = CoroutineScope(Dispatchers.IO)
//                val nameScope = CoroutineScope(CoroutineName("TestName"))
//                val coroutineScope = CoroutineScope(Job() + Dispatchers.IO + CoroutineName("TestName"))
                mainScope.launch {
                    val result = doCoroutines()
                    Log.e(TAG, "${result},当前线程：${Thread.currentThread().name}")
                }
            }
            3 -> {
                lazyJob?.start()
            }
        }
    }

    private suspend fun doCoroutines(): String {
        //暂停两秒，模拟耗时操作
        delay(1000L)
        Log.e(TAG, "doCoroutines完成, 当前线程：${Thread.currentThread().name}")
        return "成功执行doCoroutines"
    }

    private suspend fun doCoroutineScope() = coroutineScope {
        //暂停两秒，模拟耗时操作
        delay(1000L)
        withContext(Dispatchers.Main) {
            Log.e(TAG, "doCoroutineScope完成, 当前线程：${Thread.currentThread().name}")
        }
        "成功执行doCoroutineScope"
    }

    override fun onDestroyView() {
        job?.cancel()
        lazyJob?.cancel()
        mainScope.cancel()
        super.onDestroyView()
    }

}
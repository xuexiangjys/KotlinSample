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

package com.xuexiang.kotlinsample.fragment.basic

import android.util.Log
import com.xuexiang.kotlinsample.core.BaseSimpleListFragment
import com.xuexiang.xpage.annotation.Page

/**
 *
 * return。默认从最直接包围它的函数或者匿名函数返回。
 * break。终止最直接包围它的循环。
 * continue。继续下一次最直接包围它的循环。
 *
 */
@Page(name = "循环中的return break continue")
class LoopControlFragment : BaseSimpleListFragment() {
    private val TAG = "Kotlin-LoopControl"

    override fun initSimpleData(lists: MutableList<String>): MutableList<String> {
        lists.add("return: 默认从最直接包围它的函数或者匿名函数返回")
        lists.add("break: 终止最直接包围它的循环")
        lists.add("continue: 继续下一次最直接包围它的循环")
        lists.add("在foreach中实现break效果")
        lists.add("在foreach中实现continue效果")
        return lists
    }

    override fun onItemClick(position: Int) {
        when (position) {
            0 -> returnTest()
            1 -> breakTest()
            2 -> continueTest()
            3 -> foreachBreakTest()
            4 -> foreachContinueTest()
        }
    }

    private fun returnTest() {
        for (i in 1..3) {
            for (j in 1..3) {
                Log.e(TAG, "i = $i and j = $j")
                if (i == 2) return // 当执行到i == 2时，退出整个循环，直接返回.共执行3+1=4次
                Log.e(TAG, "this is below if")
            }
        }
    }

    private fun breakTest() {
        for (i in 1..3) {
            for (j in 1..3) {
                Log.e(TAG, "i = $i and j = $j")
                if (i == 2) break // 当执行到i == 2时，退出内层循环，直接从i == 3。开始共执行2*3+1=7次
                Log.e(TAG, "this is below if")
            }
        }
    }

    private fun continueTest() {
        for (i in 1..3) {
            for (j in 1..3) {
                Log.e(TAG, "i = $i and j = $j")
                if (i == 2) continue // i == 2时，下方的日志不执行。共执行9次
                Log.e(TAG, "this is below if")
            }
        }
    }

    private fun foreachBreakTest() {
        run loop@{
            listOf(1, 2, 3, 4, 5).forEach {
                if (it == 3) return@loop // 局部返回到该 lambda 表达式的调用者，即 loop 标记位，跳出循环，等同于loop中的break效果
                Log.e(TAG, "it = $it")
            }
        }
    }

    private fun foreachContinueTest() {
        listOf(1, 2, 3, 4, 5).forEach {
            if (it == 3) return@forEach // 局部返回到该 lambda 表达式的调用者，即 forEach 循环，等同于loop中的continue效果
            Log.e(TAG, "it = $it")
        }

        listOf(1, 2, 3, 4, 5).forEachIndexed { index, item ->
            if (item == 3) return@forEachIndexed // 局部返回到该 lambda 表达式的调用者，即 forEachIndexed 循环，等同于loop中的continue效果
            Log.e(TAG, "index = $index, item = $item")
        }
    }

}
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

package com.xuexiang.kotlinsample.fragment.basic.simplify

import android.util.Log
import com.xuexiang.kotlinsample.core.BaseSimpleListFragment
import com.xuexiang.xpage.annotation.Page

/**
 * 高阶函数是将 函数 用作[参数]或[返回值]的函数
 *
 * 1.所有函数类型都有一个圆括号括起来的参数类型列表以及一个返回类型：(A, B) -> C 表示接受类型分别为 A 与 B 两个参数并返回一个 C 类型值的函数类型。
 * 参数类型列表可以为空，如 () -> A。Unit 返回类型不可省略。
 *
 * 2.函数类型可以有一个额外的接收者类型，它在表示法中的点之前指定： 类型 A.(B) -> C 表示可以在 A 的接收者对象上以一个 B 类型参数来调用并返回一个 C 类型值的函数。
 *
 * 3.挂起函数属于特殊种类的函数类型，它的表示法中有一个 suspend 修饰符 ，例如 suspend () -> Unit 或者 suspend A.(B) -> C。
 *
 * 4.可以通过使用类型别名给函数类型起一个别称. 如：typealias ClickHandler = (Button, ClickEvent) -> Unit
 *
 * @author xuexiang
 * @since 2022/7/4 1:59 上午
 */
@Page(name = "高阶函数")
class HighFunctionFragment : BaseSimpleListFragment() {

    private val TAG = "Kotlin-HighFunction"

    override fun initSimpleData(lists: MutableList<String>): MutableList<String> {
        lists.add("reduce: 集合元素累积操作")
        lists.add("foldRight:同reduce，只是可以设置初始值")
        lists.add("高阶函数1：函数作为入参")
        lists.add("高阶函数2：函数作为返回值")
        lists.add("typealias：类型别名，给函数、类、枚举等进行重命名")
        return lists
    }

    override fun onItemClick(position: Int) {
        when (position) {
            0 -> reduceTest()
            1 -> foldRightTest()
            2 -> {
                val result = testOperation(1) { value -> value * 3 }
                Log.e(TAG, "result:$result")
                // 输出：result:3
            }
            3 -> {
                val operation = getOperation(1)
                Log.e(TAG, "operation:${operation(4)}")
                // 输出：operation:Operation2:4
            }
            4 -> {
                val result = getAction().invoke("method", 100)
                Log.e(TAG, "result:$result")
                // 输出：result:name:method, id:100
            }
        }
    }

    private fun reduceTest() {
        val intList = listOf(1, 2, 3, 4)

        val reduce = intList.reduce { acc, i ->
            acc + i
        }
        Log.e(TAG, "reduce:$reduce")
        // 输出：10
    }

    private fun foldRightTest() {
        val intList = listOf(1, 2, 3, 4)

        val fold = intList.foldRight(100) { acc, i ->
            acc + i
        }
        Log.e(TAG, "foldRight:$fold")
        // 输出：110
    }

    /**
     * 高阶函数：函数作为入参
     */
    private fun testOperation(value: Int, method: (value: Int) -> Int) = method(value)

    /**
     * 高阶函数：函数作为返回值
     */
    private fun getOperation(type: Int): (value: Int) -> String =
        when (type) {
            0 -> { value ->
                "Operation1:$value"
            }
            1 -> { value ->
                "Operation2:$value"
            }
            else -> { value ->
                "Operation3:$value"
            }
        }

    private fun getAction() : Action = { name: String, id: Int ->  "name:$name, id:$id"}

}

// 类型别名 给 函数、类、枚举等进行重命名
typealias Action = (String, Int) -> String

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
 * 条件判断
 *
 * @author xuexiang
 * @since 2022/6/14 12:51 上午
 */
@Page(name = "条件判断")
class ConditionalFragment : BaseSimpleListFragment() {

    private val TAG = "Kotlin-Conditional"

    override fun initSimpleData(lists: MutableList<String>): MutableList<String> {
        lists.add("kotlin中 if/else 代替三元运算符")
        lists.add("for循环使用in")
        lists.add("?. 和 ?:")
        return lists
    }

    override fun onItemClick(position: Int) {
        when(position) {
            0 -> ifElseTest()
            1-> forTest()
            2-> nullSafeTest(null)
        }
    }

    private fun ifElseTest() {
        val a = 10
        val b = 20
        // 等价于 max = a > b ? a : b
        val max = if (a > b) a else b
    }

    private fun forTest() {
        val array = intArrayOf(1, 2, 3, 4)
        for (item in array) {
            Log.e(TAG, "for $item")
        }
    }

    private fun nullSafeTest(str: String?) {
        // 如果左侧表达式 str?.length 结果为空，则返回右侧的值 -1。
        val length = str?.length ?: -1
    }

}
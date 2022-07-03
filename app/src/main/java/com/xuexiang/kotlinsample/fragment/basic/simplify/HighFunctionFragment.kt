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
 *
 *
 * @author xuexiang
 * @since 2022/7/4 1:59 上午
 */
@Page(name = "高阶函数")
class HighFunctionFragment : BaseSimpleListFragment() {

    private val TAG = "Kotlin-HighFunction"

    override fun initSimpleData(lists: MutableList<String>): MutableList<String> {
        lists.add("reduce: 将所提供的操作应用于集合元素并返回积累的结果")
        lists.add("foldRight:同reduce，只是可以设置初始值")
        return lists
    }


    override fun onItemClick(position: Int) {
        when (position) {
            0 -> reduceTest()
            1 -> foldRightTest()
        }
    }

    private fun reduceTest() {
        val intList = listOf(1, 2, 3, 4)

        val reduce = intList.reduce { acc, i ->
            acc + i
        }
        Log.e(TAG, "reduce:$reduce")
    }

    private fun foldRightTest() {
        val intList = listOf(1, 2, 3, 4)

        val fold = intList.foldRight(100) { acc, i ->
            acc + i
        }
        Log.e(TAG, "foldRight:$fold")
    }
}

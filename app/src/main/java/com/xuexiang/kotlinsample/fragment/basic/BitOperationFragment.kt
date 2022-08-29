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
 * kotlin中的位运算符
 *
 * @author xuexiang
 * @since 2022/5/24 1:35 上午
 */
@Page(name = "位运算符：and、or、shl、shr")
class BitOperationFragment : BaseSimpleListFragment() {

    private val TAG = "Kotlin-BitOperation"

    override fun initSimpleData(lists: MutableList<String>): MutableList<String> {
        lists.add("Kotlin中的 位运算符 只对Int和Long两种 数据类型 起作用")
        lists.add("与: and(bits) == &")
        lists.add("或: or(bits) == |")
        lists.add("左移: shl(bits) == <<")
        lists.add("右移: shr(bits) == >>")
        return lists
    }

    override fun onItemClick(position: Int) {
        when (position) {
            1 -> andTest()
            2 -> orTest()
            3 -> shlTest()
            4 -> shrTest()
        }
    }

    private fun andTest() {
        // 0010
        val a = 2
        // 1010
        val b = 10
        // 0010 -> 2
        Log.e(TAG, "2&10 = ${a.and(b)}")
    }

    private fun orTest() {
        // 0010
        val a = 2
        // 1001
        val b = 9
        // 1011 -> 11
        Log.e(TAG, "2|9 = ${a.or(b)}")
    }

    private fun shlTest() {
        // 000010
        val a = 2
        val b = 4
        // 100000 -> 32
        Log.e(TAG, "2<<4 = ${a.shl(b)}")
    }

    private fun shrTest() {
        // 1000000
        val a = 64
        val b = 3
        // 1000 -> 8
        Log.e(TAG, "64>>3 = ${a.shr(b)}")
    }

}
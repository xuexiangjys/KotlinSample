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
 * 拓展函数的使用
 *
 * @author xuexiang
 * @since 2022/7/4 1:50 上午
 */
@Page(name = "拓展函数")
class ExtensionFragment : BaseSimpleListFragment() {

    private val TAG = "Kotlin-Extension"

    override fun initSimpleData(lists: MutableList<String>): MutableList<String> {
        lists.add("拓展String的方法，获取字符串的真实长度")
        return lists
    }

    override fun onItemClick(position: Int) {
        when (position) {
            0 -> testString()
        }
    }

    private fun testString() {
        val a = "我是来自1978年的中国人"
        val b = "abcdefghijklmn"
        Log.e(TAG, "长度:${a.length}， 真实长度:${a.getRealLength()}")
        Log.e(TAG, "长度:${b.length}， 真实长度:${b.getRealLength()}")
    }
}
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
import com.xuexiang.kotlinsample.fragment.coroutines.business.entity.ProductInfo
import com.xuexiang.xpage.annotation.Page

/**
 * let、with、run、apply、also等作用域函数
 *
 * @author xuexiang
 * @since 2022/5/24 1:35 上午
 */
@Page(name = "let、with、run、apply、also")
class ScopeFunctionFragment : BaseSimpleListFragment() {

    private val TAG = "Kotlin-ScopeFunction"

    private val productInfo = ProductInfo("9587", "格力空调", "优质")

    override fun initSimpleData(lists: MutableList<String>): MutableList<String> {
        lists.add("【let】函数一般统一做空判断处理")
        lists.add("【with】函数在同一个对象的多个方法时，可以省去类名重复")
        lists.add("【run】函数适用于`let`函数和`with`函数的任何场景。`run`=`let`+`with`")
        lists.add("【apply】函数整体上和`run`函数相似，唯一不同就是它的返回值是对象本身")
        lists.add("【also】适用于`let`函数的任何场景，唯一不同就是它的返回值是对象本身")
        return lists
    }

    override fun onItemClick(position: Int) {
        when (position) {
            0 -> letForTest()
            1 -> withForTest()
            2 -> runForTest()
            3 -> applyForTest()
            4 -> alsoForTest()
        }
    }


    /**
     * `let`函数一般统一做空判断处理。
     */
    private fun letForTest() {
        val result = productInfo.let {
            Log.e(TAG, "let == product:${it.brand}, quality:${it.quality}")
            1111
        }
        Log.e(TAG, "let == result：$result")
    }

    /**
     * `with`函数在同一个对象的多个方法时，可以省去类名重复。
     */
    private fun withForTest() {
        val result = with(productInfo) {
            Log.e(TAG, "with == product:$brand, quality:$quality")
            2222
        }
        Log.e(TAG, "with == result：$result")
    }

    /**
     * `run`函数适用于`let`函数和`with`函数的任何场景。`run`=`let`+`with`。
     */
    private fun runForTest() {
        val result = productInfo.run {
            Log.e(TAG, "run == product:$brand, quality:$quality")
            3333
        }
        Log.e(TAG, "run == result：$result")
    }

    /**
     * `apply`函数整体上和`run`函数相似，唯一不同就是它的返回值是对象本身。
     */
    private fun applyForTest() {
        val result = productInfo.apply {
            Log.e(TAG, "apply == product:$brand, quality:$quality")
        }
        Log.e(TAG, "apply == result：$result")
    }

    /**
     * `also`适用于`let`函数的任何场景，唯一不同就是它的返回值是对象本身
     */
    private fun alsoForTest() {
        val result = productInfo.also {
            Log.e(TAG, "also == product:${it.brand}, quality:${it.quality}")
        }
        Log.e(TAG, "also == result：$result")
    }

}
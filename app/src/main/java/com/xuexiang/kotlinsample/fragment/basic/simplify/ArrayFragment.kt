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
 * 数组和集合
 *
 * @author xuexiang
 * @since 2022/6/5 2:34 下午
 */
@Page(name = "数组和集合")
class ArrayFragment : BaseSimpleListFragment() {

    private val TAG = "Kotlin-Array"

    override fun initSimpleData(lists: MutableList<String>): MutableList<String> {
        lists.add("创建")
        lists.add("forEach：遍历")
        lists.add("filter：过滤")
        lists.add("map：变换、修改")
        lists.add("flatMap：变换、创建、合并")
        lists.add("Range：区间范围")
        lists.add("Sequence：惰性集合操作")
        return lists
    }

    override fun onItemClick(position: Int) {
        when (position) {
            0 -> listCreate()
            1 -> listForEach()
            2 -> listFilter()
            3 -> listMap()
            4 -> listFlatMap()
            5 -> rangeTest()
            6 -> sequenceTest()
        }
    }

    private fun listCreate() {
        // 数组
        val intArray = intArrayOf(1, 2, 3)
        // 集合
        val intList = listOf(1, 2, 3)
        // 可变集合
        val mutableList = mutableListOf(1, 2, 3)
        mutableList.add(4)
        val mutableMap = mutableMapOf(
            1 to "1",
            2 to "2",
            3 to "3"
        )
        Log.e(TAG, "intArray $intArray")
        Log.e(TAG, "intList $intList")
        Log.e(TAG, "mutableList $mutableList")
        Log.e(TAG, "mutableMap $mutableMap")
    }

    private fun listForEach() {
        intArrayOf(1, 2, 3).forEach { i ->
            Log.e(TAG, "遍历：$i")
        }
    }

    /**
     * 遍历每个元素并执行给定表达式，最终形成新的集合
     *
     * 结果：[4, 8]
     */
    private fun listFilter() {
        val result = intArrayOf(1, 2, 3, 4, 8).filter { i ->
            i > 3
        }
        Log.e(TAG, "结果：$result")
    }

    /**
     * 遍历每个元素并执行给定表达式，最终形成新的集合
     *
     * 结果：[3, 6, 9]
     */
    private fun listMap() {
        val result = intArrayOf(1, 2, 3).map { i ->
            i * 3
        }
        Log.e(TAG, "结果：$result")
    }

    /**
     * 遍历每个元素，并为每个元素创建新的集合，最后合并到一个集合中
     *
     * 结果：[3, a, 6, a, 9, a]
     */
    private fun listFlatMap() {
        val result = intArrayOf(1, 2, 3).flatMap { i ->
            listOf("${i * 3}", "a") // 👈 生成新集合
        }
        Log.e(TAG, "结果：$result")
    }

    /**
     * 这里的 0 until 1000 表示从 0 到 1000，但不包括 1000，这就是半开区间 [0, 1000) 。
     */
    private fun rangeTest() {
        val time = System.currentTimeMillis() - 3 * MINUTE_S * 1000
        Log.e(TAG, "结果：${getFuzzyTimeDescriptionByNow(time)}")
    }

    /**
     * 根据时间戳获取模糊型的时间描述。
     *
     * @param timestamp 时间戳 单位为毫秒
     * @return 模糊型的与当前时间的差
     *
     *  * 如果在 1 分钟内或者时间是未来的时间，显示刚刚
     *  * 如果在 1 小时内，显示 XXX分钟前
     *  * 如果在 1 天内，显示XXX小时前
     *  * 如果在 1 月内，显示XXX天前
     *  * 如果在 1 年内，显示XXX月前
     *  * 如果在 1 年外，显示XXX年前
     *
     */
    private fun getFuzzyTimeDescriptionByNow(timestamp: Long): String {
        val currentTime = System.currentTimeMillis()
        // 与现在时间相差秒数
        val timeStr: String = when (val timeGap = (currentTime - timestamp) / 1000) {
            in 0 until MINUTE_S -> "刚刚"
            in MINUTE_S until HOUR_S -> "${timeGap.toFloat() / MINUTE_S}分钟前"
            in HOUR_S until DAY_S -> "${timeGap.toFloat() / HOUR_S}小时前"
            in DAY_S until MONTH_S -> "${timeGap.toFloat() / DAY_S}天前"
            in MONTH_S until YEAR_S -> "${timeGap.toFloat() / MONTH_S}个月前"
            else -> "${timeGap.toFloat() / YEAR_S}年前"
        }
        return timeStr
    }

    /**
     * 惰性指当出现满足条件的第一个元素的时候，Sequence 就不会执行后面的元素遍历.
     */
    private fun sequenceTest() {
        val sequence = sequenceOf(1, 2, 3, 4)
        val result = sequence
            .map { i ->
                Log.e(TAG, "Map $i")
                i * 2
            }
            .filter { i ->
                Log.e(TAG, "Filter $i")
                i % 3 == 0
            }
        Log.e(TAG, "result: ${result.first()}") // 👈 只取集合的第一个元素
    }

    companion object {

        /**
         * 年，单位【s】
         */
        private const val YEAR_S = 365 * 24 * 60 * 60

        /**
         * 月，单位【s】
         */
        private const val MONTH_S = 30 * 24 * 60 * 60

        /**
         * 天，单位【s】
         */
        private const val DAY_S = 24 * 60 * 60

        /**
         * 小时，单位【s】
         */
        private const val HOUR_S = 60 * 60

        /**
         * 分钟，单位【s】
         */
        private const val MINUTE_S = 60

    }

}
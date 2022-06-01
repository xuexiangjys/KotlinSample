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
 * 变量基础
 *
 * var：可变变量     var <表识符> : <类型>? = null/指定值
 * val：不可变变量   val <表识符> : <类型>? = null/指定值
 * `?`：表示该变量可为空
 * !!：不做空检查，判定一定非空，空的话会报错
 * lateinit： 修饰var变量，默认你会初始化，不做空检查
 * by lazy {}：修饰val变量，程序第一次使用到这个变量(或者对象)时再初始化
 * const：修饰val变量，代表常量
 *
 * @author xuexiang
 * @since 2022/5/27 11:22 下午
 */
@Page(name = "变量基础")
class VariableFragment : BaseSimpleListFragment() {

    companion object {
        const val TAG = "Kotlin-Variable"
    }

    var a: String? = null

    val b = "abc"

    lateinit var c: String

    val d: String by lazy {
        Log.e(TAG, "-----lazy init start-----")
        "this is a lazy variable"
    }

    var e: String? = null
        get() = "this is $field"
        set(value) {
            if (field != value) {
                Log.e(TAG, "modify field:$value")
                field = value
            } else {
                Log.e(TAG, "value is equal:$value")
            }

        }

    init {
        a = "haha!"
    }


    override fun initArgs() {
        super.initArgs()

        c = "this is a lateinit variable!"
    }

    override fun initSimpleData(lists: MutableList<String>): MutableList<String> {
        lists.add("var：可变变量")
        lists.add("val：不可变变量")
        lists.add("`?`：表示该变量可为空")
        lists.add("!!：不做空检查，判定一定非空，空的话会报错")
        lists.add("lateinit： 修饰var变量，默认你会初始化，不做空检查")
        lists.add("by lazy {}：修饰val变量，程序第一次使用到这个变量(或者对象)时再初始化")
        lists.add("const：修饰val变量，代表常量")
        lists.add("field的getter/setter函数")
        lists.add("类型的判断和强转")
        return lists
    }

    override fun onItemClick(position: Int) {
        when (position) {
            5 -> {
                Log.e(TAG, "d = $d")
            }
            7 -> {
                e = ((Math.random() * 3).toInt() + 1).toString()
                Log.e(TAG, "e = $e")
            }
            8 -> {
                Log.e(TAG, getTypeName("hahaha"))
                Log.e(TAG, getTypeName(88))
                Log.e(TAG, getTypeName(listOf(1, 2, 3, 4)))
                Log.e(TAG, getSomething("hahaha").toString())
            }
        }
    }


    private fun getTypeName(any: Any) =
        when (any) {
            is String -> "String $any"
            is Int -> "Int ${any.plus(3)}"
            is List<*> -> "List ${any.size}"
            else -> "Other"
        }

    private fun getSomething(any: Any) =
        (any as? ProductInfo)?.brand

}
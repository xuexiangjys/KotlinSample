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
 * 构造方法
 *
 * @author xuexiang
 * @since 2022/5/28 1:54 上午
 */
@Page(name = "字符串")
class StringFragment : BaseSimpleListFragment() {

    private val TAG = "Kotlin-String"

    override fun initSimpleData(lists: MutableList<String>): MutableList<String> {
        lists.add("字符串模板")
        lists.add("原生字符串")
        lists.add("trimMargin方法")
        return lists
    }

    override fun onItemClick(position: Int) {
        when(position) {
            0 -> stringFormatTest()
            1-> rawStringTest()
            2-> trimMarginTest()
        }
    }

    private fun stringFormatTest() {
        val name = "xuexiangjys"
        Log.e(TAG, "the length of $name is ${name.length}")
    }

    private fun rawStringTest() {
        val string = """    xuexiang  \n  
            |   ~~ 35 \?'
        """
        Log.e(TAG, string)
    }


    private fun trimMarginTest() {
        val string1 = """    xuexiang  \n  
                |   ~~ 35 \?'
            ?11112234  
        """.trimMargin()
        Log.e(TAG, string1)

        val string2 = """    xuexiang  \n  
                |   ~~ 35 \?'
            ?11112234  
        """.trimMargin("?")
        Log.e(TAG, string2)
    }

}
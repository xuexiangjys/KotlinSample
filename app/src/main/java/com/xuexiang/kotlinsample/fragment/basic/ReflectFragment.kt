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
import com.xuexiang.kotlinsample.fragment.basic.reflect.GoodStudent
import com.xuexiang.kotlinsample.fragment.basic.reflect.Student
import com.xuexiang.xpage.annotation.Page

/**
 * 1.反射设置属性
 * 2.
 *
 * @author xuexiang
 * @since 2022/11/6 16:05
 */
@Page(name = "Kotlin中的反射")
class ReflectFragment : BaseSimpleListFragment() {

    private val TAG = "Kotlin-Reflect"

    val student = Student(123, "xxxxxx", 25, 0, 100)

    override fun initSimpleData(lists: MutableList<String>): MutableList<String> {
        lists.add("反射设置/获取某个对象的成员属性")
        lists.add("反射设置/获取某个对象的静态属性")
        lists.add("反射执行某对象的成员方法")
        lists.add("反射执行某个类的静态方法")
        lists.add("反射构建实例")
        return lists
    }

    override fun onItemClick(position: Int) {
        when (position) {
            0 -> fieldTest()
            1 -> staticFieldTest()
            2 -> invokeMethodTest()
            3 -> invokeStaticMethod()
            4 -> newInstanceTest()
        }
    }

    private fun fieldTest() {
        Log.e(TAG, "Student before $student")
        student.javaClass.getDeclaredField("Name").run {
            isAccessible = true
            set(student, "xuexiang")
            Log.e(TAG, "Student after $student")

            Log.e(TAG, "Student Name: ${get(student)}")
        }
    }

    private fun staticFieldTest() {
        Student::class.java.getDeclaredField("TotalNumber").run {
            isAccessible = true
            set(null, 1111)
            Log.e(TAG, "TotalNumber = ${get(null)}")
        }
    }

    private fun invokeMethodTest() {
        student.javaClass.getDeclaredMethod("getName", Integer::class.java).run {
            isAccessible = true
            Log.e(TAG, "invokeMethod: ${invoke(student, 23)}")
        }
    }


    private fun invokeStaticMethod() {
        Student::class.java.getDeclaredMethod("incrementTotalNumber", Int::class.java).run {
            isAccessible = true
            Log.e(TAG, "invokeStaticMethod: ${invoke(null, 23)}")
        }
    }

    private fun newInstanceTest() {
        Student::class.java.getDeclaredConstructor(Int::class.java, String::class.java).run {
            isAccessible = true
            val std = newInstance(666, "xiaohuang")
            Log.e(TAG, "newInstance: $std")
            GoodStudent::class.java.getDeclaredConstructor(Student::class.java).run {
                isAccessible = true
                val goodStd = newInstance(std)
                Log.e(TAG, "GoodStudent: $goodStd")
            }
        }
    }

}
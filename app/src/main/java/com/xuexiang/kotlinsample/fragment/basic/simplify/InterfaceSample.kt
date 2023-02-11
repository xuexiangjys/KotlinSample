/*
 * Copyright (C) 2023 xuexiangjys(xuexiangjys@163.com)
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

/**
 * interface实例：
 *
 * object : xx {
 *   override fun xxx(...) {
 *
 *   }
 * }
 *
 * @author xuexiang
 * @since 2023/2/11 18:01
 */

interface IFilter {

    fun onFilter(quality: Int): Boolean
}

class Food(var quality: Int) {

    var name: String = ""

    var filter: IFilter? = null
}

fun setFilter(food: Food) {
    food.filter = object : IFilter {
        override fun onFilter(quality: Int): Boolean {
            return quality > 90
        }
    }
}
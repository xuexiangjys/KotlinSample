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

/**
 * 函数简化
 *
 * @author xuexiang
 * @since 2022/5/29 1:06 上午
 */

// 1.标准写法
fun getArea(width: Int, height: Int): Int {
    return width * height
}

// 2.简化写法
fun getArea1(width: Int, height: Int) = width * height


// 3.默认值
fun getArea2(width: Int = 0, height: Int = 0) = width * height


fun login(user: String, password: String, errorMessage: String) {

    // 本地函数（嵌套函数）
    fun validate(value: String, errorMessage: String) {
        if (value.isEmpty()) {
            throw IllegalArgumentException(errorMessage)
        }
    }

    validate(user, errorMessage)
    validate(password, errorMessage)
}
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
 * 构造方法
 *
 * @author xuexiang
 * @since 2022/5/28 2:01 上午
 */
class User(var name: String, var age: Int)

class UserA {
    var name: String = "xiaoming"
    var age: Int = 20

    init {
        age = 40
    }
}

class UserB(var name: String = "xiaoming", var age: Int = 20)


class User2 constructor(var name: String) {
    var age: Int = 20
}

class User3 {
    var name: String = ""
    private var age: Int = 0
        get() = field * 3
        set(value) {
            field = value
            name = "${field}岁的人"
        }

    constructor(name: String, age: Int) {
        this.name = name
        this.age = age
    }


}

/**
 * Kotlin 的 data class 默认提供了一种对象拷贝的方式 , 即 data class 类会生成 copy() 方法, 类似java.lang.Object 的 clone() 方法，这是一种浅拷贝
 */
data class Person(var name: String, var age: Int, var phone: String) {
    override fun toString(): String {
        return "Person(name='$name', age=$age, phone='$phone')"
    }
}
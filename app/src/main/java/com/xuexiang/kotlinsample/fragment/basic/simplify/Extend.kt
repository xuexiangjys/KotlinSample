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
 * 类的继承和接口
 *
 * --类修饰关键词
 * open：表示类可继承，类默认是final的；
 * final：表示类不可继承，默认属性；
 * abstract：表示抽象类；
 * enum：表示枚举类；
 * annotation：表示注解类；
 *
 * --可见性修饰词
 * public：表示公有，范围最大，所有调用的地方可见，具有最大的访问权限，可以被其他所有类访问，如果没有声明修饰符则系统会默认使用这个修饰符；
 * private：表示私有，范围最小，仅在同一文件可见，只能被自己访问和修改；
 * protected：表示私有+子类，同一个文件中或者子类可见，自己和子类及同一包中的类可以访问，不能用于顶层声明；
 * internal：表示模块，同一个模块中可见。一个模块可以是一个Maven项目，一个Gradle源集。
 *
 * @author xuexiang
 * @since 2022/5/29 1:00 上午
 */
open class Fruit(var price: Float)

open class Place(var address: String)

interface IProduce {
    fun produce()
}


internal class Apple(var quality: Int, price: Float) : Fruit(price), IProduce {

    override fun produce() {
        // do produce
    }
}
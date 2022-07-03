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

import com.xuexiang.kotlinsample.core.BaseContainerFragment
import com.xuexiang.kotlinsample.fragment.basic.simplify.*
import com.xuexiang.xpage.annotation.Page

/**
 * Kotlin中更加方便的写法：https://juejin.cn/post/6844903923061358605
 *
 *
 * @author xuexiang
 * @since 2022/5/28 1:51 上午
 */
@Page(name = "Kotlin中常见的便捷使用")
class SimplifyUseFragment : BaseContainerFragment() {

    override fun getPagesClasses(): Array<Class<*>> {
        return arrayOf(
            StringFragment::class.java,
            ArrayFragment::class.java,
            ConditionalFragment::class.java,
            ExtensionFragment::class.java,
            HighFunctionFragment::class.java,
        )
    }
}
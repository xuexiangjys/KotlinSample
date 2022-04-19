/*
 * Copyright (C) 2019 xuexiangjys(xuexiangjys@163.com)
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
package com.xuexiang.kotlinsample.fragment

import com.xuexiang.kotlinsample.core.BaseContainerFragment
import com.xuexiang.kotlinsample.fragment.coroutines.CoroutinesBasicFragment
import com.xuexiang.kotlinsample.fragment.coroutines.CoroutinesFlowFragment
import com.xuexiang.kotlinsample.fragment.coroutines.SchedulerChangeFragment
import com.xuexiang.xpage.annotation.Page

/**
 * 协程使用
 *
 * @author xuexiang
 * @since 2022/3/25 1:42 上午
 */
@Page(name = "协程使用")
class CoroutinesFragment : BaseContainerFragment() {

    override fun getPagesClasses(): Array<Class<*>> {
        return arrayOf(
            CoroutinesBasicFragment::class.java,
            SchedulerChangeFragment::class.java,
            CoroutinesFlowFragment::class.java
        )
    }
}
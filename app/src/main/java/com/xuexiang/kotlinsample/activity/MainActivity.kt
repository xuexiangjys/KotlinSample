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
package com.xuexiang.kotlinsample.activity

import android.os.Bundle
import androidx.viewbinding.ViewBinding
import com.xuexiang.kotlinsample.core.BaseActivity
import com.xuexiang.kotlinsample.fragment.MainFragment

/**
 * 程序入口，空壳容器
 *
 * @author xuexiang
 * @since 2019-07-07 23:53
 */
class MainActivity : BaseActivity<ViewBinding?>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        openPage(MainFragment::class.java)
    }
}
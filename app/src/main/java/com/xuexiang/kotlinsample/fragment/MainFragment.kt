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

import android.view.KeyEvent
import com.xuexiang.kotlinsample.core.BaseContainerFragment
import com.xuexiang.kotlinsample.utils.XToastUtils
import com.xuexiang.xpage.annotation.Page
import com.xuexiang.xpage.enums.CoreAnim
import com.xuexiang.xui.widget.actionbar.TitleBar
import com.xuexiang.xutil.XUtil
import com.xuexiang.xutil.common.ClickUtils
import com.xuexiang.xutil.common.ClickUtils.OnClick2ExitListener

@Page(name = "Kotlin使用演示", anim = CoreAnim.none)
class MainFragment : BaseContainerFragment(), OnClick2ExitListener {
    override fun getPagesClasses(): Array<Class<*>> {
        return arrayOf( //此处填写fragment
            CoroutinesFragment::class.java,
            KotlinBasicFragment::class.java
        )
    }

    override fun initTitle(): TitleBar? {
        return super.initTitle()?.setLeftClickListener { ClickUtils.exitBy2Click(2000, this) }
    }

    /**
     * 菜单、返回键响应
     */
    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            ClickUtils.exitBy2Click(2000, this)
        }
        return true
    }

    override fun onRetry() {
        XToastUtils.toast("再按一次退出程序")
    }

    override fun onExit() {
        XUtil.exitApp()
    }
}
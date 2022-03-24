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
package com.xuexiang.kotlinsample.utils

import androidx.annotation.MainThread
import com.xuexiang.xui.widget.toast.XToast
import com.xuexiang.xui.XUI
import androidx.annotation.StringRes
import java.lang.UnsupportedOperationException

/**
 * xtoast 工具类
 *
 * @author xuexiang
 * @since 2019-06-30 19:04
 */
class XToastUtils private constructor() {

    companion object {
        //======普通土司=======//
        @MainThread
        fun toast(message: CharSequence) {
            XToast.normal(XUI.getContext(), message).show()
        }

        @MainThread
        fun toast(@StringRes message: Int) {
            XToast.normal(XUI.getContext(), message).show()
        }

        @MainThread
        fun toast(message: CharSequence, duration: Int) {
            XToast.normal(XUI.getContext(), message, duration).show()
        }

        @MainThread
        fun toast(@StringRes message: Int, duration: Int) {
            XToast.normal(XUI.getContext(), message, duration).show()
        }

        //======错误【红色】=======//
        @MainThread
        fun error(message: CharSequence) {
            XToast.error(XUI.getContext(), message).show()
        }

        @MainThread
        fun error(@StringRes message: Int) {
            XToast.error(XUI.getContext(), message).show()
        }

        @MainThread
        fun error(message: CharSequence, duration: Int) {
            XToast.error(XUI.getContext(), message, duration).show()
        }

        @MainThread
        fun error(@StringRes message: Int, duration: Int) {
            XToast.error(XUI.getContext(), message, duration).show()
        }

        //======成功【绿色】=======//
        @MainThread
        fun success(message: CharSequence) {
            XToast.success(XUI.getContext(), message).show()
        }

        @MainThread
        fun success(@StringRes message: Int) {
            XToast.success(XUI.getContext(), message).show()
        }

        @MainThread
        fun success(message: CharSequence, duration: Int) {
            XToast.success(XUI.getContext(), message, duration).show()
        }

        @MainThread
        fun success(@StringRes message: Int, duration: Int) {
            XToast.success(XUI.getContext(), message, duration).show()
        }

        //======信息【蓝色】=======//
        @MainThread
        fun info(message: CharSequence) {
            XToast.info(XUI.getContext(), message).show()
        }

        @MainThread
        fun info(@StringRes message: Int) {
            XToast.info(XUI.getContext(), message).show()
        }

        @MainThread
        fun info(message: CharSequence, duration: Int) {
            XToast.info(XUI.getContext(), message, duration).show()
        }

        @MainThread
        fun info(@StringRes message: Int, duration: Int) {
            XToast.info(XUI.getContext(), message, duration).show()
        }

        //=======警告【黄色】======//
        @MainThread
        fun warning(message: CharSequence) {
            XToast.warning(XUI.getContext(), message).show()
        }

        @MainThread
        fun warning(@StringRes message: Int) {
            XToast.warning(XUI.getContext(), message).show()
        }

        @MainThread
        fun warning(message: CharSequence, duration: Int) {
            XToast.warning(XUI.getContext(), message, duration).show()
        }

        @MainThread
        fun warning(@StringRes message: Int, duration: Int) {
            XToast.warning(XUI.getContext(), message, duration).show()
        }

        init {
            XToast.Config.get()
                    .setAlpha(200)
                    .allowQueue(false)
        }
    }

    init {
        throw UnsupportedOperationException("u can't instantiate me...")
    }
}
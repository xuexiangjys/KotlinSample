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
package com.xuexiang.kotlinsample.core

import android.content.res.Configuration
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.umeng.analytics.MobclickAgent
import com.xuexiang.xpage.base.XPageActivity
import com.xuexiang.xpage.base.XPageFragment
import com.xuexiang.xpage.core.PageOption
import com.xuexiang.xpage.enums.CoreAnim
import com.xuexiang.xpage.utils.Utils
import com.xuexiang.xrouter.facade.service.SerializationService
import com.xuexiang.xrouter.launcher.XRouter
import com.xuexiang.xui.utils.WidgetUtils
import com.xuexiang.xui.widget.actionbar.TitleBar
import com.xuexiang.xui.widget.actionbar.TitleUtils
import com.xuexiang.xui.widget.progress.loading.IMessageLoader
import java.io.Serializable
import java.lang.reflect.Type

/**
 * 基础fragment
 *
 * @author xuexiang
 * @since 2018/5/25 下午3:44
 */
abstract class BaseFragment<Binding : ViewBinding?> : XPageFragment() {
    private var mIMessageLoader: IMessageLoader? = null
    /**
     * ViewBinding
     */
    var binding: Binding? = null
        protected set

    override fun inflateView(inflater: LayoutInflater, container: ViewGroup): View {
        binding = viewBindingInflate(inflater, container)
        return binding!!.root
    }

    /**
     * 构建ViewBinding
     *
     * @param inflater  inflater
     * @param container 容器
     * @return ViewBinding
     */
    protected abstract fun viewBindingInflate(
        inflater: LayoutInflater,
        container: ViewGroup
    ): Binding

    override fun initPage() {
        initTitle()
        initViews()
        initListeners()
    }

    open fun initTitle(): TitleBar? {
        return TitleUtils.addTitleBarDynamic(rootView as ViewGroup, pageTitle) { popToBack() }
    }

    override fun initListeners() {}
    val messageLoader: IMessageLoader?
        get() {
            if (mIMessageLoader == null) {
                mIMessageLoader = WidgetUtils.getMiniLoadingDialog(requireContext())
            }
            return mIMessageLoader
        }

    fun getMessageLoader(message: String?): IMessageLoader? {
        if (mIMessageLoader == null) {
            mIMessageLoader = WidgetUtils.getMiniLoadingDialog(requireContext(), message!!)
        } else {
            mIMessageLoader!!.updateMessage(message)
        }
        return mIMessageLoader
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        //屏幕旋转时刷新一下title
        super.onConfigurationChanged(newConfig)
        val root = rootView as ViewGroup
        if (root.getChildAt(0) is TitleBar) {
            root.removeViewAt(0)
            initTitle()
        }
    }

    override fun onDestroyView() {
        if (mIMessageLoader != null) {
            mIMessageLoader!!.dismiss()
        }
        super.onDestroyView()
        binding = null
    }

    override fun onResume() {
        super.onResume()
        MobclickAgent.onPageStart(pageName)
    }

    override fun onPause() {
        super.onPause()
        MobclickAgent.onPageEnd(pageName)
    }
    //==============================页面跳转api===================================//
    /**
     * 打开一个新的页面【建议只在主tab页使用】
     *
     * @param clazz 页面的类
     * @param <T>
     * @return
    </T> */
    fun <T : XPageFragment?> openNewPage(clazz: Class<T>?): Fragment? {
        return PageOption(clazz)
            .setNewActivity(true)
            .open(this)
    }

    /**
     * 打开一个新的页面【建议只在主tab页使用】
     *
     * @param pageName 页面名
     * @param <T>
     * @return
    </T> */
    fun <T : XPageFragment?> openNewPage(pageName: String?): Fragment? {
        return PageOption(pageName)
            .setAnim(CoreAnim.slide)
            .setNewActivity(true)
            .open(this)
    }

    /**
     * 打开一个新的页面【建议只在主tab页使用】
     *
     * @param clazz                页面的类
     * @param containActivityClazz 页面容器
     * @param <T>
     * @return
    </T> */
    fun <T : XPageFragment?> openNewPage(
        clazz: Class<T>?,
        containActivityClazz: Class<out XPageActivity?>
    ): Fragment? {
        return PageOption(clazz)
            .setNewActivity(true)
            .setContainActivityClazz(containActivityClazz)
            .open(this)
    }

    /**
     * 打开一个新的页面【建议只在主tab页使用】
     *
     * @param clazz 页面的类
     * @param key   入参的键
     * @param value 入参的值
     * @param <T>
     * @return
    </T> */
    fun <T : XPageFragment?> openNewPage(clazz: Class<T>?, key: String?, value: Any?): Fragment? {
        val option = PageOption(clazz).setNewActivity(true)
        return openPage(option, key, value)
    }

    private fun openPage(option: PageOption, key: String?, value: Any?): Fragment? {
        when (value) {
            is Int -> option.putInt(key, value)
            is Float -> option.putFloat(key, value)
            is String -> option.putString(key, value)
            is Boolean -> option.putBoolean(key, value)
            is Long -> option.putLong(key, value)
            is Double -> option.putDouble(key, value)
            is Parcelable -> option.putParcelable(key, value)
            is Serializable -> option.putSerializable(key, value)
            else -> option.putString(key, serializeObject(value))
        }
        return option.open(this)
    }

    /**
     * 打开页面
     *
     * @param clazz          页面的类
     * @param addToBackStack 是否加入回退栈
     * @param key            入参的键
     * @param value          入参的值
     * @param <T>
     * @return
    </T> */
    fun <T : XPageFragment?> openPage(
        clazz: Class<T>?,
        addToBackStack: Boolean,
        key: String?,
        value: String?
    ): Fragment? {
        return PageOption(clazz)
            .setAddToBackStack(addToBackStack)
            .putString(key, value)
            .open(this)
    }

    /**
     * 打开页面
     *
     * @param clazz 页面的类
     * @param key   入参的键
     * @param value 入参的值
     * @param <T>
     * @return
    </T> */
    fun <T : XPageFragment?> openPage(clazz: Class<T>?, key: String?, value: Any?): Fragment? {
        return openPage(clazz, true, key, value)
    }

    /**
     * 打开页面
     *
     * @param clazz          页面的类
     * @param addToBackStack 是否加入回退栈
     * @param key            入参的键
     * @param value          入参的值
     * @param <T>
     * @return
    </T> */
    fun <T : XPageFragment?> openPage(
        clazz: Class<T>?,
        addToBackStack: Boolean,
        key: String?,
        value: Any?
    ): Fragment? {
        val option = PageOption(clazz).setAddToBackStack(addToBackStack)
        return openPage(option, key, value)
    }

    /**
     * 打开页面
     *
     * @param clazz 页面的类
     * @param key   入参的键
     * @param value 入参的值
     * @param <T>
     * @return
    </T> */
    fun <T : XPageFragment?> openPage(clazz: Class<T>?, key: String?, value: String?): Fragment? {
        return PageOption(clazz)
            .putString(key, value)
            .open(this)
    }

    /**
     * 打开页面,需要结果返回
     *
     * @param clazz       页面的类
     * @param key         入参的键
     * @param value       入参的值
     * @param requestCode 请求码
     * @param <T>
     * @return
    </T> */
    fun <T : XPageFragment?> openPageForResult(
        clazz: Class<T>?,
        key: String?,
        value: Any?,
        requestCode: Int
    ): Fragment? {
        val option = PageOption(clazz).setRequestCode(requestCode)
        return openPage(option, key, value)
    }

    /**
     * 打开页面,需要结果返回
     *
     * @param clazz       页面的类
     * @param key         入参的键
     * @param value       入参的值
     * @param requestCode 请求码
     * @param <T>
     * @return
    </T> */
    fun <T : XPageFragment?> openPageForResult(
        clazz: Class<T>?,
        key: String?,
        value: String?,
        requestCode: Int
    ): Fragment? {
        return PageOption(clazz)
            .setRequestCode(requestCode)
            .putString(key, value)
            .open(this)
    }

    /**
     * 打开页面,需要结果返回
     *
     * @param clazz       页面的类
     * @param requestCode 请求码
     * @param <T>
     * @return
    </T> */
    fun <T : XPageFragment?> openPageForResult(clazz: Class<T>?, requestCode: Int): Fragment? {
        return PageOption(clazz)
            .setRequestCode(requestCode)
            .open(this)
    }

    /**
     * 序列化对象
     *
     * @param object 需要序列化的对象
     * @return 序列化结果
     */
    fun serializeObject(`object`: Any?): String {
        return XRouter.getInstance().navigation(SerializationService::class.java)
            .object2Json(`object`)
    }

    /**
     * 反序列化对象
     *
     * @param input 反序列化的内容
     * @param clazz 类型
     * @return 反序列化结果
     */
    fun <T> deserializeObject(input: String?, clazz: Type?): T {
        return XRouter.getInstance().navigation(SerializationService::class.java)
            .parseObject(input, clazz)
    }

    override fun hideCurrentPageSoftInput() {
        if (activity == null) {
            return
        }
        // 记住，要在xml的父布局加上android:focusable="true" 和 android:focusableInTouchMode="true"
        Utils.hideSoftInputClearFocus(requireActivity().currentFocus)
    }
}
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
package com.xuexiang.kotlinsample.fragment.coroutines.concurrent.entity

/**
 * 富文本信息
 *
 * @author xuexiang
 * @since 3/22/22 1:13 AM
 */
class RichInfo(var id: String) {
    /**
     * 描述信息
     */
    var description: String? = null
    /**
     * 图片链接
     */
    var imgUrl: String? = null
    /**
     * 视频链接
     */
    var videoUrl: String? = null

    override fun toString(): String {
        return ("富文本编号:" + id
                + ", 描述信息:" + description
                + ", 图片链接:" + imgUrl
                + ", 视频链接:" + videoUrl)
    }
}
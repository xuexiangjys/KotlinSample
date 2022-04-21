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
 * 产品简要信息
 *
 * @author xuexiang
 * @since 3/22/22 1:08 AM
 */
open class BriefInfo {
    var id: String
    var name: String? = null
    var factoryId: String? = null
    var priceId: String? = null
    var promotionId: String? = null
    var richId: String? = null

    constructor(id: String) {
        this.id = id
    }

    constructor(briefInfo: BriefInfo) : this(
        briefInfo.id,
        briefInfo.name,
        briefInfo.factoryId,
        briefInfo.priceId,
        briefInfo.promotionId,
        briefInfo.richId
    )

    constructor(
        id: String,
        name: String?,
        factoryId: String?,
        priceId: String?,
        promotionId: String?,
        richId: String?
    ) {
        this.id = id
        this.name = name
        this.factoryId = factoryId
        this.priceId = priceId
        this.promotionId = promotionId
        this.richId = richId
    }
}
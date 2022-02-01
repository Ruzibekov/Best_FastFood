package com.ruzibekov.needfood_r.domain.interfaces

import com.ruzibekov.needfood_r.domain.models.ProductCategory

interface CategoryList {
    fun getList(categories: List<ProductCategory>)
}
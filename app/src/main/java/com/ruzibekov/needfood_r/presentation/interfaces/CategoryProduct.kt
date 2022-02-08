package com.ruzibekov.needfood_r.presentation.interfaces

import com.ruzibekov.needfood_r.domain.models.ProductCategory

interface CategoryProduct {

    fun get(category: ProductCategory)
}
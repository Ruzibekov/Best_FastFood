package com.ruzibekov.needfood_r.interfaces

import com.ruzibekov.needfood_r.domain.models.Product


interface ProductItemClick {
    fun onClick(product: Product)
}
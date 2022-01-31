package com.ruzibekov.needfood_r.presentation.interfaces

import com.ruzibekov.needfood_r.data.room.Product


interface ProductItem {
    fun product(product: Product)
}
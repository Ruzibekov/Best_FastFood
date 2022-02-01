package com.ruzibekov.needfood_r.domain.interfaces

import com.ruzibekov.needfood_r.data.room.Product

interface ProductObject {

    fun getProduct(list: List<Product>)
}
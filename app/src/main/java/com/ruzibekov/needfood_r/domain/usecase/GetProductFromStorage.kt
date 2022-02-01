package com.ruzibekov.needfood_r.domain.usecase

import com.ruzibekov.needfood_r.data.room.Product
import com.ruzibekov.needfood_r.domain.interfaces.ProductObject
import com.ruzibekov.needfood_r.presentation.App

class GetProductFromStorage {

    fun execute(productObject: ProductObject){
        Thread  {
            val productDao = App.instance.database.productDao()
            productObject.getProduct(productDao.getAllPhotos())
        }.start()
    }
}
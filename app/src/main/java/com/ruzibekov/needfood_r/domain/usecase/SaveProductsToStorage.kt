package com.ruzibekov.needfood_r.domain.usecase

import com.ruzibekov.needfood_r.data.room.Product
import com.ruzibekov.needfood_r.presentation.App

class SaveProductsToStorage {

    fun execute(product: Product) {
        Thread {
            val productDao = App.instance.database.productDao()
            productDao.addPhotos(product)
        }.start()
    }
}
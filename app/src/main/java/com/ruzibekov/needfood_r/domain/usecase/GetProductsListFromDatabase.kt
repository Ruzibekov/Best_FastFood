package com.ruzibekov.needfood_r.domain.usecase

import android.util.Log
import com.ruzibekov.needfood_r.data.GetListFromFirebase
import com.ruzibekov.needfood_r.data.room.Product
import com.ruzibekov.needfood_r.presentation.interfaces.ProductItem

class GetProductsListFromDatabase {

    fun execute(getProduct: ProductItem){

        GetListFromFirebase().execute(object : ProductItem{
            override fun product(product: Product) {
                getProduct.product(product)
            }
        })
    }

}
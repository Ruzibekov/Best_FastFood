package com.ruzibekov.needfood_r.domain.usecase

import com.ruzibekov.needfood_r.domain.models.ProductCategory
import com.ruzibekov.needfood_r.presentation.App


class GetProductsCategories {

    fun execute(): List<ProductCategory> {
        val productDao = App.instance.database.productDao()
        val allCategories = productDao.getAllCategories()
        return filterList(allCategories as ArrayList<ProductCategory>)
    }

    private fun filterList(allCategories: ArrayList<ProductCategory>): List<ProductCategory> {
        val set: Set<ProductCategory> = HashSet<ProductCategory>(allCategories)
        allCategories.clear()
        allCategories.addAll(set)
        return allCategories
    }


}
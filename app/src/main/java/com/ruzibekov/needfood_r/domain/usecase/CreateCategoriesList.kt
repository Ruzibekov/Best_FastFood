package com.ruzibekov.needfood_r.domain.usecase

import com.ruzibekov.needfood_r.databinding.FragmentMainBinding
import com.ruzibekov.needfood_r.domain.models.ProductCategory
import com.ruzibekov.needfood_r.presentation.App
import com.ruzibekov.needfood_r.presentation.adapters.CategoriesListAdapter

class CreateCategoriesList(private val binding: FragmentMainBinding) {

    fun execute(categoriesList: List<ProductCategory>) {
        for (category in categoriesList)
            category.uri = getUriByCategory(category.category)
        binding.mainScreen.categoriesList.adapter = CategoriesListAdapter(categoriesList)
    }

    private fun getUriByCategory(category: String): String {
        val dao = App.instance.database.productDao()
        return dao.getCategoryUri(category)
    }

}
package com.ruzibekov.needfood_r.domain.usecase

import com.ruzibekov.needfood_r.databinding.FragmentHomeBinding
import com.ruzibekov.needfood_r.domain.models.ProductCategory
import com.ruzibekov.needfood_r.presentation.App
import com.ruzibekov.needfood_r.presentation.adapters.CategoriesListAdapter
import com.ruzibekov.needfood_r.presentation.interfaces.CategoryProduct

class CreateCategoriesList(private val binding: FragmentHomeBinding) {

    fun execute(categoriesList: List<ProductCategory>, productCategory: CategoryProduct) {
        for (category in categoriesList)
            category.uri = getUriByCategory(category.category.toString())
        binding.categoriesList.adapter = CategoriesListAdapter(categoriesList, object : CategoryProduct{
            override fun get(category: ProductCategory) {
                productCategory.get(category)
            }
        })
    }

    private fun getUriByCategory(category: String): String {
        val dao = App.instance.database.productDao()
        return dao.getCategoryUri(category)
    }

}
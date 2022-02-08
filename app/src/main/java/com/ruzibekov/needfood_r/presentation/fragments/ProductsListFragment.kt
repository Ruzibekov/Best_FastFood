package com.ruzibekov.needfood_r.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ruzibekov.needfood_r.R
import com.ruzibekov.needfood_r.data.room.Product
import com.ruzibekov.needfood_r.databinding.FragmentProductsListBinding
import com.ruzibekov.needfood_r.presentation.App
import com.ruzibekov.needfood_r.presentation.adapters.PopularNowListAdapter
import com.ruzibekov.needfood_r.presentation.interfaces.ProductItem

class ProductsListFragment : Fragment(R.layout.fragment_products_list) {
    companion object {
        const val CATEGORY_KEY = "category_key"
    }

    private lateinit var binding: FragmentProductsListBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProductsListBinding.bind(view)

        Thread {
            binding.productList.adapter = PopularNowListAdapter(getDatas(), object : ProductItem {
                override fun product(product: Product) {
                    findNavController().navigate(R.id.action_productsListFragment_to_descriptionFragment)
                }
            })
        }.start()

    }

    private fun getDatas(): ArrayList<Product> {
        val selectedCategory = arguments?.getString(CATEGORY_KEY)
        val filteredList = arrayListOf<Product>()
        val dao = App.instance.database.productDao()
        val products = dao.getAllProducts()
        for (product in products) {
            if (product.category == selectedCategory)
                filteredList.add(product)
        }
        return filteredList
    }

}
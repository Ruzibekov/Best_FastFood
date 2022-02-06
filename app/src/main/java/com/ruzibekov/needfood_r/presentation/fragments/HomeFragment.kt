package com.ruzibekov.needfood_r.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ruzibekov.needfood_r.R
import com.ruzibekov.needfood_r.data.room.Product
import com.ruzibekov.needfood_r.databinding.FragmentHomeBinding
import com.ruzibekov.needfood_r.domain.usecase.*
import com.ruzibekov.needfood_r.presentation.adapters.PopularNowListAdapter
import com.ruzibekov.needfood_r.presentation.interfaces.ProductItem

class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var binding: FragmentHomeBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        binding.toolbarMainFragment.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.menu_search_button -> findNavController().navigate(R.id.action_mainFragment_to_searchFragment)
            }
            true
        }

        Thread {
            GetProductsListFromDatabase().execute(object : ProductItem {
                override fun product(product: Product) {
                    SaveProductsToStorage().execute(product)
                }
            })
            createPopularList(GetProductFromStorage().execute())
            CreateCategoriesList(binding).execute(GetProductsCategories().execute())
        }.start()
    }



    private fun createPopularList(list: List<Product>) {
        binding.popularNowList.adapter =
            PopularNowListAdapter(list as ArrayList<Product>, object : ProductItem {
                override fun product(product: Product) {
                    openProductDetails(product)
                }
            })
    }

    private fun openProductDetails(product: Product) {
        val productArray = arrayListOf<String>()
        productArray.add(product.name)
        productArray.add(product.uri)
        productArray.add(product.category)
        productArray.add(product.location)
        productArray.add(product.price)
        productArray.add(product.description)
        val bundle = Bundle()
        bundle.putStringArrayList(DescriptionFragment.PRODUCT_KEY, productArray)
        findNavController().navigate(R.id.action_mainFragment_to_descriptionFragment, bundle)
    }
}
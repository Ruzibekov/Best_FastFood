package com.ruzibekov.needfood_r.presentation.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ruzibekov.needfood_r.R
import com.ruzibekov.needfood_r.data.room.Product
import com.ruzibekov.needfood_r.databinding.FragmentMainBinding
import com.ruzibekov.needfood_r.domain.usecase.*
import com.ruzibekov.needfood_r.presentation.adapters.PopularNowListAdapter
import com.ruzibekov.needfood_r.presentation.interfaces.ProductItem


class MainFragment : Fragment(R.layout.fragment_main) {
    private lateinit var binding: FragmentMainBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)
        binding.toolbarMainFragment.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.menu_search_button -> findNavController().navigate(R.id.action_mainFragment_to_searchFragment)
            }
            true
        }
        createCategoriesList()
        createRecommendedList()
    }

    override fun onStart() { // Get Products
        super.onStart()

        Thread {
            GetProductsListFromDatabase().execute(object : ProductItem {
                override fun product(product: Product) {
                    SaveProductsToStorage().execute(product)
                }
            })
            createPopularList(GetProductFromStorage().execute())
            CreateCategoriesList(binding).execute(GetProductsCategories().execute())
        }.start()


//        val list = arrayListOf<Product>()
//        list.add(Product(name = "Burger"))
//        list.add(Product(name = "Pizza"))
//        Log.i("mylog", GetProductsByName().execute(list, "Pizza").toString())
    }

    private fun createPopularList(list: List<Product>) {
        binding.mainScreen.popularNowList.adapter =
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


    private fun createCategoriesList() {
        val categoriesList = arrayListOf<Product>()
//        categoriesList.add(Product(R.drawable.image_beef_burger,
//            "Burger",
//            "MLunch",
//            500,
//            "Daxshat"))
//        categoriesList.add(Product(R.drawable.image_beef_burger,
//            "Burger",
//            "MLunch",
//            500,
//            "Daxshat"))
//        categoriesList.add(Product(R.drawable.image_beef_burger,
//            "Burger",
//            "MLunch",
//            500,
//            "Daxshat"))
//        categoriesList.add(Product(R.drawable.image_beef_burger,
//            "Burger",
//            "MLunch",
//            500,
//            "Daxshat"))

    }


    @SuppressLint("UseRequireInsteadOfGet")
    private fun createRecommendedList() {
        val recommendedProductsList = arrayListOf<Product>()
//        recommendedProductsList.add(Product(R.drawable.image_big_burger,
//            "Pizza",
//            "MLunch",
//            500,
//            "Daxshat"))
//        recommendedProductsList.add(Product(R.drawable.image_big_burger,
//            "Burger",
//            "MLunch",
//            500,
//            "Daxshat"))
//        recommendedProductsList.add(Product(R.drawable.image_big_burger,
//            "Burger",
//            "MLunch",
//            500,
//            "Daxshat"))
//        recommendedProductsList.add(Product(R.drawable.image_big_burger,
//            "Burger",
//            "MLunch",
//            500,
//            "Daxshat"))
//        recommendedProductsList.add(Product(R.drawable.image_big_burger,
//            "Burger",
//            "MLunch",
//            500,
//            "Daxshat"))
//        binding.mainScreen.recommendedList.adapter =
//            RecommendedListAdapter(recommendedProductsList, this)
    }

    fun showToast() {
        Toast.makeText(context, "ishladi", Toast.LENGTH_SHORT).show()
    }

}
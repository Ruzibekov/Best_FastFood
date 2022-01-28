package com.ruzibekov.needfood_r.view.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.ruzibekov.needfood_r.R
import com.ruzibekov.needfood_r.view.adapters.CategoriesListAdapter
import com.ruzibekov.needfood_r.databinding.FragmentMainBinding
import com.ruzibekov.needfood_r.domain.usecase.GetProductsFromFirebase
import com.ruzibekov.needfood_r.domain.models.Product


class MainFragment : Fragment(R.layout.fragment_main){
    private lateinit var binding: FragmentMainBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)
        createCategoriesList()
        createRecommendedList()
    }

    override fun onStart() {
        super.onStart()
        GetProductsFromFirebase().execute(binding)
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
        binding.mainScreen.categoriesList.adapter = CategoriesListAdapter(categoriesList)
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





}
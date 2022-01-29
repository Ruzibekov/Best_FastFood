package com.ruzibekov.needfood_r.presentation.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ruzibekov.needfood_r.R
import com.ruzibekov.needfood_r.databinding.FragmentMainBinding
import com.ruzibekov.needfood_r.domain.models.Product
import com.ruzibekov.needfood_r.domain.usecase.GetProducts
import com.ruzibekov.needfood_r.domain.usecase.GetProductsByName
import com.ruzibekov.needfood_r.presentation.adapters.CategoriesListAdapter


class MainFragment : Fragment(R.layout.fragment_main) {
    private lateinit var binding: FragmentMainBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)
        binding.toolbarMainFragment.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.menu_search_button -> findNavController().navigate(R.id.action_mainFragment_to_searchFragment)
            }
            true
        }
        createCategoriesList()
        createRecommendedList()
    }

    override fun onStart() {
        super.onStart()
        GetProducts().execute(binding, parentFragment)
        val list = arrayListOf<Product>()
        list.add(Product(name = "Burger"))
        list.add(Product(name = "Pizza"))
        Log.i("mylog", GetProductsByName().execute(list, "Pizza").toString())

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

    fun showToast(){
        Toast.makeText(context, "ishladi", Toast.LENGTH_SHORT).show()
    }

}
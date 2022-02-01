package com.ruzibekov.needfood_r.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.ruzibekov.needfood_r.R
import com.ruzibekov.needfood_r.data.room.Product
import com.ruzibekov.needfood_r.databinding.FragmentDescriptionBinding
import java.util.ArrayList

class DescriptionFragment : Fragment(R.layout.fragment_description) {
    companion object {
        val PRODUCT_KEY = "product_key"
    }

    private lateinit var binding: FragmentDescriptionBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDescriptionBinding.bind(view)
        getDatas()
        onBackStack()
        productSelected()
    }

    private fun getDatas() {
        val productStringArray: ArrayList<String>? = arguments?.getStringArrayList(PRODUCT_KEY)

        val product = Product(
            productStringArray?.get(0) ?: "null",
            productStringArray?.get(1) ?: "null",
            productStringArray?.get(2) ?: "null",
            productStringArray?.get(3) ?: "null",
            productStringArray?.get(4) ?: "null",
            productStringArray?.get(5) ?: "null",

        )
        setDatas(product)
    }

    private fun setDatas(product: Product) {
        Glide.with(this).load(product.uri.toUri()).into(binding.productImage)
        //binding.productName.text = product.name
        binding.collapsingToolbar.title = product.name
        binding.productPrice.text = product.price
        binding.productLocation.text = product.location
        binding.productDescription.text = product.description
    }

    private fun onBackStack() {
        binding.descriptionToolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun productSelected() {
        var isSelected = false
        binding.descriptionToolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.menu_heart -> {
                    if (!isSelected) {
                        it.setIcon(R.drawable.ic_heart_red)
                        isSelected = true
                    } else {
                        it.setIcon(R.drawable.ic_heart)
                        isSelected = false
                    }
                }
            }
            true
        }
    }
}
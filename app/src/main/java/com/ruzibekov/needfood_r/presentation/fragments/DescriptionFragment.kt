package com.ruzibekov.needfood_r.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.ruzibekov.needfood_r.R
import com.ruzibekov.needfood_r.databinding.FragmentDescriptionBinding
import com.ruzibekov.needfood_r.domain.models.Product

class DescriptionFragment : Fragment(R.layout.fragment_description) {
    private lateinit var binding: FragmentDescriptionBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDescriptionBinding.bind(view)
        getDatas()
        onBackStack()
        productSelected()
    }

    private fun getDatas() {
        val productDatas: ArrayList<String> =
            arguments?.getStringArrayList("product") as ArrayList<String>
        val product = Product(
            productDatas[0].toInt(),
            productDatas[1],
            productDatas[2],
            productDatas[3],
            productDatas[4],
            productDatas[5],
            productDatas[6])
        setDatas(product)
    }

    private fun setDatas(product: Product) {
        Glide.with(this).load(product.uri.toUri()).into(binding.productImage)
        binding.productName.text = product.name
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
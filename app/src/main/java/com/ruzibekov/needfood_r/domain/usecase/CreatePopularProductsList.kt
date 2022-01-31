package com.ruzibekov.needfood_r.domain.usecase

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ruzibekov.needfood_r.R
import com.ruzibekov.needfood_r.databinding.FragmentMainBinding
import com.ruzibekov.needfood_r.presentation.interfaces.ProductItem
import com.ruzibekov.needfood_r.data.room.Product
import com.ruzibekov.needfood_r.presentation.adapters.PopularNowListAdapter

class CreatePopularProductsList(val parentFragment: Fragment?) : ProductItem {

    fun execute(popularNowList: ArrayList<Product>, binding: FragmentMainBinding){
        binding.mainScreen.popularNowList.adapter = PopularNowListAdapter(popularNowList, this)
    }

    override fun product(product: Product) {
        val bundle = Bundle()
        bundle.putStringArrayList("product", arrayListOf(
            product.id.toString(),
            product.uri,
            product.name,
            product.category,
            product.location,
            product.price,
            product.description))
        parentFragment?.findNavController()?.navigate(R.id.action_mainFragment_to_descriptionFragment, bundle)
    }
}
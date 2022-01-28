package com.ruzibekov.needfood_r.domain.usecase

import android.os.Bundle
import com.ruzibekov.needfood_r.databinding.FragmentMainBinding
import com.ruzibekov.needfood_r.interfaces.ProductItemClick
import com.ruzibekov.needfood_r.domain.models.Product
import com.ruzibekov.needfood_r.view.adapters.PopularNowListAdapter

class CreatePopularProductsList : ProductItemClick {
    fun execute(popularNowList: ArrayList<Product>, binding: FragmentMainBinding){
        binding.mainScreen.popularNowList.adapter = PopularNowListAdapter(popularNowList, this)
    }

    override fun onClick(product: Product) {
        val bundle = Bundle()
        bundle.putStringArrayList("product", arrayListOf(
            product.uri,
            product.name,
            product.category,
            product.location,
            product.price,
            product.description))
//        findNavController().navigate(R.id.action_mainFragment_to_descriptionFragment, bundle)
    }
}
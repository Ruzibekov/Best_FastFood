package com.ruzibekov.needfood_r.data

import androidx.fragment.app.Fragment
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.ruzibekov.needfood_r.databinding.FragmentMainBinding
import com.ruzibekov.needfood_r.domain.models.Product
import com.ruzibekov.needfood_r.domain.usecase.CreatePopularProductsList

class ProductRepository(val parentFragment: Fragment?) {

    fun getDatas(binding: FragmentMainBinding) {

        val database = Firebase.database.getReference("Products")
        val products = arrayListOf<Product>()
        database.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                for (dataSnapshot in snapshot.children) {
                    val product: Product = dataSnapshot.getValue(Product::class.java) ?: Product()
                    products.add(product)
                    CreatePopularProductsList(parentFragment).execute(products, binding)
                }
            }

            override fun onCancelled(error: DatabaseError) {}
        })
    }
}
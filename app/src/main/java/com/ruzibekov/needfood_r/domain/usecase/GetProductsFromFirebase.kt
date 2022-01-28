package com.ruzibekov.needfood_r.domain.usecase

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.ruzibekov.needfood_r.databinding.FragmentMainBinding
import com.ruzibekov.needfood_r.domain.models.Product

class GetProductsFromFirebase {

    fun execute(binding: FragmentMainBinding) {
        val database = Firebase.database.getReference("Products")
        getDatas(database, binding)
    }

    private fun getDatas(database: DatabaseReference, binding: FragmentMainBinding) {
        database.addValueEventListener(object : ValueEventListener {
            val products = arrayListOf<Product>()
            override fun onDataChange(snapshot: DataSnapshot) {
                for (dataSnapshot in snapshot.children) {
                    val product = Product()
                    for (data in dataSnapshot.children)
                        when (data.key.toString()) {
                            "id" -> product.id = data.value.toString().toInt()
                            "uri" -> product.uri = data.value.toString()
                            "name" -> product.name = data.value.toString()
                            "category" -> product.category = data.value.toString()
                            "location" -> product.location = data.value.toString()
                            "price" -> product.price = data.value.toString()
                            "description" -> product.description = data.value.toString()
                        }
                    products.add(product)
                    Log.i("mylog", products.toString())
                    CreatePopularProductsList().execute(products, binding)
                }
            }
            override fun onCancelled(error: DatabaseError) {}
        })
    }

}
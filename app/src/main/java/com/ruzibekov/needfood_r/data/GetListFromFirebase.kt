package com.ruzibekov.needfood_r.data

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.ruzibekov.needfood_r.data.room.Product
import com.ruzibekov.needfood_r.presentation.interfaces.ProductItem

class GetListFromFirebase {

    fun getDatas(getProduct: ProductItem){

        val database = Firebase.database.getReference("Products")
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (dataSnapshot in snapshot.children) {
                    val product: Product = dataSnapshot.getValue(Product::class.java) ?: Product()
                    getProduct.product(product)
                }
            }

            override fun onCancelled(error: DatabaseError) {}
        })
    }
}
package com.ruzibekov.needfood_r.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Product(
    @PrimaryKey
    var name: String = "",
    var uri: String = "null",
    var category: String = "",
    var location: String = "",
    var price: String = "",
    var description: String = "",
)

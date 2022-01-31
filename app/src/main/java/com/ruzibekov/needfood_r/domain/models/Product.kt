package com.ruzibekov.needfood_r.domain.models

import androidx.room.Entity

@Entity(tableName = "product")
data class Product(
    var id: Int = 0,
    var uri: String = "null",
    var name: String = "",
    var category: String = "",
    var location: String = "",
    var price: String = "",
    var description: String = "",
)

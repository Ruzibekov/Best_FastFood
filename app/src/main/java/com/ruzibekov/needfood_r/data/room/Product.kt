package com.ruzibekov.needfood_r.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Product(
    @PrimaryKey(autoGenerate = true)
    var key: Int = 0,
    var id: Int = 0,
    var uri: String = "null",
    var name: String = "",
    var category: String = "",
    var location: String = "",
    var price: String = "",
    var description: String = "",
)

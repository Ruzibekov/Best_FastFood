package com.ruzibekov.needfood_r.objects

import android.net.Uri

data class Product(
    var id: Int = 0,
    var uri: String = "null",
    var name: String = "",
    var category: String = "",
    var location: String = "",
    var price: String = "",
    var description: String = "",
)

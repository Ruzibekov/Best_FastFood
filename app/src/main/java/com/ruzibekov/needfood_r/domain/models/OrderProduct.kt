package com.ruzibekov.needfood_r.domain.models

data class OrderProduct(
    var name: String = "",
    var uri: String = "null",
    var category: String = "",
    var location: String = "",
    var price: String = "",
    var description: String = "",
    var latitude: Double = 0.0,
    var longitude: Double = 0.0,
)
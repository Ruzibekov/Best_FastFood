package com.ruzibekov.needfood_r.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ProductDao {
    @Insert
    fun addPhotos(product: Product)

    @Query("SELECT * FROM product")
    fun getAllPhotos():List<Product>
}
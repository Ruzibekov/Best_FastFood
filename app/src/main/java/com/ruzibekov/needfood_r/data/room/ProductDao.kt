package com.ruzibekov.needfood_r.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ruzibekov.needfood_r.domain.models.ProductCategory

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addPhotos(product: Product)

    @Query("SELECT * FROM product")
    fun getAllPhotos(): List<Product>

    @Query("SELECT category FROM product")
    fun getAllCategories(): List<ProductCategory>

    @Query("SELECT uri FROM product WHERE category = :category")
    fun getCategoryUri(category: String): String

}
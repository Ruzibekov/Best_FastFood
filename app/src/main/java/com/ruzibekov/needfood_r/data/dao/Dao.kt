package com.ruzibekov.needfood_r.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ruzibekov.needfood_r.domain.models.Product

@Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllPhotos(list: List<Product>)

    @Query("select * from product")
    fun getAllPhotos():List<Product>
}
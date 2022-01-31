package com.ruzibekov.needfood_r.data.helper


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ruzibekov.needfood_r.data.dao.Dao
import com.ruzibekov.needfood_r.domain.models.Product


@Database(entities = [Product::class],version = 1,exportSchema = false)
abstract class DbHelper : RoomDatabase(){

    abstract fun productDao(): Dao

    companion object{
        private var instance:DbHelper? = null

        fun getinstance(context: Context):DbHelper{
            if (instance == null){
                synchronized(DbHelper::class){
                    instance = buildRoom(context)
                }
            }
            return instance!!
        }

        private fun buildRoom(context: Context): DbHelper? =
            Room.databaseBuilder(context.applicationContext,DbHelper::class.java,"products")
                .allowMainThreadQueries()
                .build()
    }
}
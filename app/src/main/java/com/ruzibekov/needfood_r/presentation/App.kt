package com.ruzibekov.needfood_r.presentation

import android.app.Application
import androidx.room.Room
import com.ruzibekov.needfood_r.data.room.ProductDatabase

class App : Application() {
    companion object {
        lateinit var instance: App
    }

    lateinit var database: ProductDatabase

    override fun onCreate() {
        super.onCreate()
        instance = this
        database = Room.databaseBuilder(applicationContext,
            ProductDatabase::class.java,
            "product_database").build()
    }
}
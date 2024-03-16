package com.example.mvvm.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mvvm.model.Product

@Database(entities = arrayOf(Product::class), version = 1 )
abstract class DataBase : RoomDatabase() {
    abstract fun getProductDao(): ProductDao
    companion object{
        @Volatile
        private var INSTANCE: DataBase? = null
        fun getInstance (ctx: Context): DataBase{
            return INSTANCE ?: synchronized(this) {
                    val instance = Room.databaseBuilder(
                    ctx.applicationContext, DataBase::class.java, "color_database").build()
                    INSTANCE = instance
                    instance
            }
        }
    }
}

package com.example.mvvm.model

import android.content.Context
import com.example.mvvm.db.DataBase
import com.example.mvvm.db.ProductDao
import kotlinx.coroutines.flow.Flow

object ProductLocalDataSourceImpl:ProductLocalDataSource  {


    lateinit var dataBase:DataBase

    lateinit var dao:ProductDao

    fun prepareDataBase(context: Context){
        dataBase = DataBase.getInstance(context)
        dao = dataBase.getProductDao()
    }

    override suspend fun insert(product: Product): Long {
        return dao.insertProduct(product)
    }

    override suspend fun delete(product: Product) {
        dao.deleteProduct(product)
    }

    override fun getAllProducts(): Flow<List<Product>> {
        return dao.getAllProducts()
    }
}
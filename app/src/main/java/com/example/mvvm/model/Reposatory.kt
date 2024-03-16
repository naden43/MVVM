package com.example.mvvm.model

import android.content.Context
import com.example.mvvm.network.ProductRemoteDataSourceImpl
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

object Reposatory{

    fun createRepo(context: Context){
        ProductLocalDataSourceImpl.prepareDataBase(context)
    }

    suspend fun insertProduct(product: Product){
        ProductLocalDataSourceImpl.insert(product)
    }

    suspend fun deleteProduct(product: Product){
        ProductLocalDataSourceImpl.delete(product)
    }

    fun getFavourites(): Flow<List<Product>> {
        return ProductLocalDataSourceImpl.getAllProducts()
    }

     fun getAllProduct():Flow<Products>{
        return ProductRemoteDataSourceImpl.getProducts()
    }

}
package com.example.mvvm.model

import kotlinx.coroutines.flow.Flow

interface ProductLocalDataSource {

    suspend fun insert(product: Product):Long

    suspend fun delete(product: Product)

     fun getAllProducts(): Flow<List<Product>>

}
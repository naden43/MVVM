package com.example.mvvm.network

import com.example.mvvm.model.Products
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface ProductRemoteDataSource {
     fun getProducts(): Flow<Products>
}
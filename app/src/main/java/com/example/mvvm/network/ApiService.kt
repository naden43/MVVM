package com.example.mvvm.network

import com.example.mvvm.model.Products
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("products")
     suspend fun getProducts(): Products

}
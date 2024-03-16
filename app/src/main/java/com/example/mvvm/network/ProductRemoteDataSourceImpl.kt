package com.example.mvvm.network

import com.example.mvvm.model.Products
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ProductRemoteDataSourceImpl : ProductRemoteDataSource {

    const val BASE_URL = "https://dummyjson.com/"
    val instance = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
        .baseUrl(
        BASE_URL
    ).build()

    lateinit var service: ApiService

    override fun getProducts(): Flow<Products> {
        service = instance.create(ApiService::class.java)
        return flow<Products>{
            emit(service.getProducts())
        }
    }
}

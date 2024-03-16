package com.example.mvvm.allProducts.viewModel

import com.example.mvvm.model.Products

sealed class ApiStatus {
    class Success(var products: Products) : ApiStatus()
    class Failure(var error:String) : ApiStatus()
    object loading : ApiStatus()
}
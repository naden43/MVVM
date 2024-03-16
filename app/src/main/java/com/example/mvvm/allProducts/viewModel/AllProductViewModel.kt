package com.example.mvvm.allProducts.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm.model.Product
import com.example.mvvm.model.Products
import com.example.mvvm.model.Reposatory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import retrofit2.Response

class AllProductViewModel(): ViewModel(){


    private var _products:MutableStateFlow<ApiStatus> = MutableStateFlow<ApiStatus>(ApiStatus.loading)
    val products :StateFlow<ApiStatus> = _products

    fun intializeViewModel(context: Context){
        Reposatory.createRepo(context)
        getRemoteProducts()
    }

    fun getProducts() : Flow<Products>{
        return Reposatory.getAllProduct()
    }

    fun getRemoteProducts(){
        viewModelScope.launch(Dispatchers.IO){
            Reposatory.getAllProduct()
                .catch { _products.value = ApiStatus.Failure(it.message.toString())}
                .collect{
                   _products.value = ApiStatus.Success(it)
                }
        }
    }

    fun addFavourate(product: Product){
        viewModelScope.launch(Dispatchers.IO){
            Reposatory.insertProduct(product)
        }
    }

}
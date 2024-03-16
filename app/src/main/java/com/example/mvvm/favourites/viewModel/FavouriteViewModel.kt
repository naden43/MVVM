package com.example.mvvm.favourites.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm.favourites.view.favourites
import com.example.mvvm.model.Product
import com.example.mvvm.model.Products
import com.example.mvvm.model.Reposatory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import retrofit2.Response
import java.util.stream.Collectors

class FavouriteViewModel() : ViewModel() {
    private var _products: MutableLiveData<List<Product>> = MutableLiveData<List<Product>>()
    val products : LiveData<List<Product>> = _products

    fun intializeViewModel(context: Context){
        Reposatory.createRepo(context)
        getLocalProducts()
    }

    fun getProducts() : Flow<Products> {
        return Reposatory.getAllProduct()
    }

    fun getLocalProducts(){
        viewModelScope.launch(Dispatchers.IO){
            Reposatory.getFavourites().collect {
                _products.postValue(it)
            }
        }
    }

    fun deleteFavourate(product: Product){
        viewModelScope.launch(Dispatchers.IO){
            Reposatory.deleteProduct(product)
            getLocalProducts()
        }
    }

}
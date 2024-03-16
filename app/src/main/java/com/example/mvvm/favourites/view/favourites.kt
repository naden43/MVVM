package com.example.mvvm.favourites.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.listadapterfirstkotlinapp.FavourireAdapter
import com.example.listadapterfirstkotlinapp.ProductAdapter
import com.example.mvvm.R
import com.example.mvvm.allProducts.viewModel.AllProductViewModel
import com.example.mvvm.databinding.ActivityFavouritesBinding
import com.example.mvvm.databinding.ActivityProductsBinding
import com.example.mvvm.favourites.viewModel.FavouriteViewModel

class favourites : AppCompatActivity() {

    lateinit var binding:ActivityFavouritesBinding
    lateinit var productAdapter:FavourireAdapter

    lateinit var viewModel:FavouriteViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favourites)

        binding = ActivityFavouritesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        productAdapter = FavourireAdapter(this){
            viewModel.deleteFavourate(it)
        }

        viewModel  = ViewModelProvider(this).get(FavouriteViewModel::class.java)
        viewModel.intializeViewModel(this)
        viewModel.products.observe(this){
            productAdapter.submitList(it)

        }



        binding.RecycleView.apply {
            adapter = productAdapter
            layoutManager = LinearLayoutManager(context).apply {
                orientation = RecyclerView.VERTICAL
            }
        }


    }
}
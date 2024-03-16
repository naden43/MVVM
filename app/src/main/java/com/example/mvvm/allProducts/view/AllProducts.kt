package com.example.mvvm.allProducts.view

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.listadapterfirstkotlinapp.ProductAdapter
import com.example.mvvm.R
import com.example.mvvm.allProducts.viewModel.AllProductViewModel
import com.example.mvvm.allProducts.viewModel.ApiStatus
import com.example.mvvm.databinding.ActivityProductsBinding
import com.example.mvvm.model.Product
import com.example.mvvm.model.Reposatory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AllProducts : AppCompatActivity() , OnAddFavourateCliCKListener {

    lateinit var binding:ActivityProductsBinding
    lateinit var productAdapter:ProductAdapter

    lateinit var viewModel:AllProductViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        productAdapter = ProductAdapter(this){
            viewModel.addFavourate(it)
        }

        viewModel  = ViewModelProvider(this).get(AllProductViewModel::class.java)
        viewModel.intializeViewModel(this)

        lifecycleScope.launch {
            viewModel.products.collect{result  ->

                when(result){

                    is ApiStatus.loading -> {
                        //binding.recycleView.visibility = android.view.View.GONE
                        binding.loader.visibility = android.view.View.VISIBLE
                    }
                    is ApiStatus.Success -> {
                        binding.recycleView.visibility = android.view.View.VISIBLE
                        binding.loader.visibility = android.view.View.GONE
                        withContext(Dispatchers.Main) {
                            productAdapter.submitList(result.products.products)
                        }
                    }
                    is ApiStatus.Failure -> {
                        Toast.makeText(this@AllProducts , "failed ${result.error}" ,Toast.LENGTH_SHORT).show()
                    }
                }

            }
        }

        binding.recycleView.apply {
            adapter = productAdapter
            layoutManager = LinearLayoutManager(context).apply {
                orientation = RecyclerView.VERTICAL
            }
        }



    }

    override fun onAddClickListener(product: Product?) {

    }
}
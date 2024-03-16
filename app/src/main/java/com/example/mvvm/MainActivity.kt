package com.example.mvvm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mvvm.allProducts.view.AllProducts
import com.example.mvvm.databinding.ActivityMainBinding
import com.example.mvvm.favourites.view.favourites

class MainActivity : AppCompatActivity() {

    lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnExit.setOnClickListener {
            finish()
        }

        binding.btnFav.setOnClickListener {
            startActivity(Intent(this@MainActivity , favourites::class.java))
        }

        binding.btnShowAll.setOnClickListener {
            startActivity(Intent(this@MainActivity , AllProducts::class.java))
        }
        /*lifecycleScope.launch {
            ProductLocalDataSourceImpl.prepareDataBase(this@MainActivity)
            Log.i("TAG", "onCreate: " + ProductRemoteDataSourceImpl.getProducts().body()?.products?.size)
            ProductLocalDataSourceImpl.insert(ProductRemoteDataSourceImpl.getProducts().body()?.products?.get(1)!!)
        }*/


    }
}
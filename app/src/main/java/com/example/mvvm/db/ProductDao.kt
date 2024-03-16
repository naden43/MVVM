package com.example.mvvm.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Delete
import com.example.mvvm.model.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertProduct(product:Product) : Long

    @Delete
    suspend fun deleteProduct(product: Product)

    @Query("SELECT * FROM product_table")
    fun getAllProducts(): Flow<List<Product>>

}
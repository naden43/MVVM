package com.example.mvvm.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_table")
data class Product(
    @PrimaryKey
    var title:String ,
    @ColumnInfo
    var description: String ,
    @ColumnInfo
    var price:Int ,
    @ColumnInfo
    var rating:Float ,
    @ColumnInfo
    var thumbnail: String)
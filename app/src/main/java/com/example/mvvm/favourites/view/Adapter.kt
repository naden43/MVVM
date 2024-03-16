package com.example.listadapterfirstkotlinapp

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.mvvm.databinding.FavItemBinding
import com.example.mvvm.model.Product


class FavourireAdapter(var context: Context, var listener :(Product)->Unit) : ListAdapter<Product, FavViewHolder>(FavProductDiffUtil()){

    lateinit var binding:FavItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavViewHolder {
        val inflater: LayoutInflater = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = FavItemBinding.inflate(inflater , parent , false)
        //val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item , parent , false)
        return FavViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavViewHolder, position: Int) {
        val currentObj = getItem(position)

        holder.binding.titleTxt.text = currentObj.title
        holder.binding.discTxt.text =  currentObj.description
        holder.binding.priceTxt.text = currentObj.price.toString()
        holder.binding.ratingBar.rating = currentObj.rating
        holder.binding.btnDeleteFav.setOnClickListener {
            listener(currentObj)
        }
        Glide.with(context).load(currentObj.thumbnail)
            .apply(RequestOptions().override(200, 200)).into(holder.binding.imageView)
    }
}

data class FavViewHolder(var binding: FavItemBinding): RecyclerView.ViewHolder(binding.root)


class FavProductDiffUtil : DiffUtil.ItemCallback<Product>(){
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem.title==newItem.title
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem == newItem
    }
}


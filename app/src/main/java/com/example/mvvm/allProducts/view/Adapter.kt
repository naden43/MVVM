package com.example.listadapterfirstkotlinapp

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.mvvm.databinding.ItemBinding
import com.example.mvvm.model.Product


class ProductAdapter(var context: Context, var listener :(Product)->Unit) : ListAdapter<Product, ViewHolder>(ProductDiffUtil()){

    lateinit var binding:ItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater: LayoutInflater = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = ItemBinding.inflate(inflater , parent , false)
        //val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item , parent , false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentObj = getItem(position)

        holder.binding.titleTxt.text = currentObj.title
        holder.binding.discTxt.text =  currentObj.description
        holder.binding.priceTxt.text = currentObj.price.toString()
        holder.binding.ratingBar.rating = currentObj.rating
        holder.binding.btnAddFav.setOnClickListener {
            listener(currentObj)
        }
        Glide.with(context).load(currentObj.thumbnail)
            .apply(RequestOptions().override(200, 200)).into(holder.binding.imageView)
    }
}

data class ViewHolder(var binding: ItemBinding): RecyclerView.ViewHolder(binding.root)/*{

    var image:ImageView = itemView.findViewById(R.id.image)
    var titleTxt:TextView = itemView.findViewById(R.id.title)
    var descTxt :TextView = itemView.findViewById(R.id.desc)
    var constrainLayout:ConstraintLayout = itemView.findViewById(R.id.constrainLayout)
}*/

class ProductDiffUtil : DiffUtil.ItemCallback<Product>(){
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem.title==newItem.title
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem == newItem
    }
}


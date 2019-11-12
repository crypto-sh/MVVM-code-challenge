package com.mobiquity.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.core.dto.ProductModel
import com.mobiquity.databinding.ProductItemLayoutBinding


class MainListAdapter(val onProductSelected : (ProductModel) -> Unit) : ListAdapter<ProductModel, MainListAdapter.Holder>(Diff()) {

    class Diff  : DiffUtil.ItemCallback<ProductModel>(){
        override fun areItemsTheSame(oldItem: ProductModel, newItem: ProductModel)   : Boolean = oldItem == newItem
        override fun areContentsTheSame(oldItem: ProductModel, newItem: ProductModel): Boolean = oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ProductItemLayoutBinding.inflate(LayoutInflater.from(parent.context))
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class Holder(private val binding : ProductItemLayoutBinding)  : RecyclerView.ViewHolder(binding.root){

        fun bind(product : ProductModel){
            binding.product = product
            binding.holder  = this
        }

        fun onItemSelected(product: ProductModel){
            onProductSelected(product)
        }
    }
}



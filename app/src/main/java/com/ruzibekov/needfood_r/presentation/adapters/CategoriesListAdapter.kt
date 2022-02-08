package com.ruzibekov.needfood_r.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ruzibekov.needfood_r.R
import com.ruzibekov.needfood_r.domain.models.ProductCategory
import com.ruzibekov.needfood_r.presentation.interfaces.CategoryProduct

class CategoriesListAdapter(private val categoriesList: List<ProductCategory>, private val category: CategoryProduct) :
    RecyclerView.Adapter<CategoriesListAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.categories_product_image)
        val name: TextView = view.findViewById(R.id.categories_product_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_categories, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.itemView).load(categoriesList[position].uri?.toUri()).into(holder.image)
        holder.name.text = categoriesList[position].category
        holder.itemView.setOnClickListener {
            category.get(categoriesList[position])
        }
    }

    override fun getItemCount(): Int {
        return categoriesList.size
    }
}
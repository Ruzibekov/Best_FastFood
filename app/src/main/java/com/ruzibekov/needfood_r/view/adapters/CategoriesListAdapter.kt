package com.ruzibekov.needfood_r.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ruzibekov.needfood_r.R
import com.ruzibekov.needfood_r.domain.models.Product

class CategoriesListAdapter(val categoriesList: ArrayList<Product>) : RecyclerView.Adapter<CategoriesListAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.categories_product_image)
        val name: TextView = view.findViewById(R.id.categories_product_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_categories, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.image.setImageResource(categoriesList[position].image)
        holder.name.text = categoriesList[position].name
    }

    override fun getItemCount(): Int {
        return categoriesList.size
    }
}
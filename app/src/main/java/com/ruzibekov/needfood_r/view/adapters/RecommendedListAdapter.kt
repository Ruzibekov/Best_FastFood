package com.ruzibekov.needfood_r.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ruzibekov.needfood_r.R
import com.ruzibekov.needfood_r.interfaces.ProductItemClick
import com.ruzibekov.needfood_r.objects.Product

class RecommendedListAdapter(
    private val recommendedList: ArrayList<Product>,
    private val itemClick: ProductItemClick
) : RecyclerView.Adapter<RecommendedListAdapter.ViewHolder>(){

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.recommended_product_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recommended, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.itemView).load(recommendedList[position].uri.toUri()).into(holder.image)
        holder.image.setOnClickListener{
            itemClick.onClick(recommendedList[position])
        }
    }

    override fun getItemCount(): Int {
        return recommendedList.size
    }

    interface ItemClick{
        fun itemClickListener(product: Product)
    }


}
package com.ruzibekov.needfood_r.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ruzibekov.needfood_r.R
import com.ruzibekov.needfood_r.interfaces.ProductItemClick
import com.ruzibekov.needfood_r.domain.models.Product

class PopularNowListAdapter(
    private val popularNowList: ArrayList<Product>,
    private val click: ProductItemClick,
) :
    RecyclerView.Adapter<PopularNowListAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val productImage: ImageView = view.findViewById(R.id.popular_product_image)
        val productName: TextView = view.findViewById(R.id.popular_product_name)
        val productPrice: TextView = view.findViewById(R.id.popular_product_price)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_popular_now, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Glide.with(holder.itemView).load(popularNowList[position].uri.toUri())
            .into(holder.productImage)

        holder.productName.text = popularNowList[position].name
        holder.productPrice.text = popularNowList[position].price
        holder.itemView.setOnClickListener {
            click.onClick(popularNowList[position])
        }
    }

    override fun getItemCount(): Int {
        return popularNowList.size
    }

    interface Click {
        fun onClik(person: Product)
    }
}
package com.ruzibekov.needfood_r.presentation.fragments

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.PackageManager
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.firebase.database.FirebaseDatabase
import com.ruzibekov.needfood_r.R
import com.ruzibekov.needfood_r.data.room.Product
import com.ruzibekov.needfood_r.databinding.FragmentDescriptionBinding
import com.ruzibekov.needfood_r.domain.models.OrderProduct
import java.util.ArrayList

class DescriptionFragment : Fragment(R.layout.fragment_description) {
    companion object {
        val PRODUCT_KEY = "product_key"
    }
    private lateinit var binding: FragmentDescriptionBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDescriptionBinding.bind(view)
        val product = getDatas()
        setDatas(product)
        onBackStack()
        productSelected()
        toOrder(product)
    }

    private fun getDatas() : Product {
        val productStringArray: ArrayList<String>? = arguments?.getStringArrayList(PRODUCT_KEY)

        val product = Product(
            productStringArray?.get(0) ?: "null",
            productStringArray?.get(1) ?: "null",
            productStringArray?.get(2) ?: "null",
            productStringArray?.get(3) ?: "null",
            productStringArray?.get(4) ?: "null",
            productStringArray?.get(5) ?: "null",
        )
        return product
    }

    private fun setDatas(product: Product) {
        Glide.with(this).load(product.uri.toUri()).into(binding.productImage)
        //binding.productName.text = product.name
        binding.collapsingToolbar.title = product.name
        binding.productPrice.text = product.price
        binding.productLocation.text = product.location
        binding.productDescription.text = product.description
    }

    private fun onBackStack() {
        binding.descriptionToolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun productSelected() {
        var isSelected = false
        binding.descriptionToolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.menu_heart -> {
                    isSelected = if (!isSelected) {
                        it.setIcon(R.drawable.ic_heart_red)
                        true
                    } else {
                        it.setIcon(R.drawable.ic_heart)
                        false
                    }
                }
            }
            true
        }
    }

    private fun toOrder(product: Product){
        binding.buttonToOrder.setOnClickListener {
            val orderProduct = OrderProduct(
                product.name,
                product.uri,
                product.category,
                product.location,
                product.price,
                product.description
            )
            getLocation(orderProduct)
        }
    }

    @SuppressLint("UseRequireInsteadOfGet")
    private fun getLocation(orderProduct: OrderProduct){
        val locationManager = context?.getSystemService(AppCompatActivity.LOCATION_SERVICE) as LocationManager
        if (ActivityCompat.checkSelfPermission(context!!,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(context!!,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
        ) {
            //Lokatsiyadan foydalanishga ruxsat berilmagan bo'lsa ishga tushadi
            ActivityCompat.requestPermissions(context as Activity,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION), 1)
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
            0,
            10f,
            locationListener(orderProduct))

        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
            0,
            10f,
            locationListener(orderProduct))
    }

    private fun locationListener(product: OrderProduct) = LocationListener {
        product.latitude = it.latitude
        product.longitude = it.longitude
        val database = FirebaseDatabase.getInstance().getReference("Orders")
        database.push().setValue(product)
        Toast.makeText(context, "Buyurtmangiz qabul qilindi", Toast.LENGTH_LONG).show()
    }
}
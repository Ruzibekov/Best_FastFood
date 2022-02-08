package com.ruzibekov.needfood_r.presentation.fragments

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.graphics.drawable.toDrawable
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
        const val PRODUCT_KEY = "product_key"
    }
    private lateinit var binding: FragmentDescriptionBinding

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDescriptionBinding.bind(view)
        val product = getDatas()
        setDatas(product)

        var favoriteNum = 0
        binding.favorite.setOnClickListener {
            if (favoriteNum == 0) {
                binding.favorite.setImageResource(R.drawable.ic_heart_red)
                favoriteNum = 1
            }else{
                favoriteNum = 0
                binding.favorite.setImageResource(R.drawable.ic_heart)
            }
        }

        val productPrice = binding.productPrice.text.toString().toInt()
        binding.plus.setOnClickListener {
            binding.productNumber.text = (binding.productNumber.text.toString().toInt() + 1).toString()
            setPrice(productPrice)
        }

        binding.minus.setOnClickListener {
            if (binding.productNumber.text.toString().toInt() != 0)
                binding.productNumber.text = (binding.productNumber.text.toString().toInt() - 1).toString()
            setPrice(productPrice)
        }

        binding.minus.setOnLongClickListener {
            binding.productNumber.text = "0"
            setPrice(productPrice)
            return@setOnLongClickListener true
        }

        Thread{
            binding.userCart.setOnClickListener {
                toOrder(product)
            }
        }.start()
    }

    private fun setPrice(productPrice: Int) {
        binding.productPrice.text = (binding.productNumber.text.toString().toInt() * productPrice).toString()
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
        binding.productName.text = product.name
        binding.productPrice.text = product.price
        binding.productLocation.text = product.location
        binding.productDescription.text = product.description
    }

    private fun toOrder(product: Product){
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

    @SuppressLint("UseRequireInsteadOfGet")
    private fun getLocation(orderProduct: OrderProduct){
        val locationManager = context?.getSystemService(AppCompatActivity.LOCATION_SERVICE) as LocationManager
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            checkEnabledLocation()
        }
        if (ActivityCompat.checkSelfPermission(context!!,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(context!!,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
        ) {
            //Lokatsiyadan foydalanishga ruxsat berilmagan bo'lsa ishga tushadi
            ActivityCompat.requestPermissions(context as Activity,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION), 1)
        }else {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                0,
                10f,
                locationListener(orderProduct))

            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                0,
                10f,
                locationListener(orderProduct))
        }
    }

    private fun checkEnabledLocation() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        builder.setMessage("Your GPS seems to be disabled, do you want to enable it?")
            .setCancelable(false)
            .setPositiveButton("Yes") { dialog, id ->
                startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
            }
            .setNegativeButton("No") { dialog, id -> dialog.cancel() }
        val alert: AlertDialog = builder.create()
        alert.show()
    }

    private fun locationListener(product: OrderProduct) = LocationListener {
        product.latitude = it.latitude
        product.longitude = it.longitude
        val database = FirebaseDatabase.getInstance().getReference("Orders")
        database.push().setValue(product)
        Toast.makeText(context, "Buyurtmangiz qabul qilindi", Toast.LENGTH_LONG).show()
    }
}
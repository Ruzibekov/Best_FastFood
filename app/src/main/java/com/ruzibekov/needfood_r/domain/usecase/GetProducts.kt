package com.ruzibekov.needfood_r.domain.usecase

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.ruzibekov.needfood_r.R
import com.ruzibekov.needfood_r.data.ProductRepository
import com.ruzibekov.needfood_r.databinding.FragmentMainBinding
import com.ruzibekov.needfood_r.domain.models.Product

class GetProducts {

    fun execute(binding: FragmentMainBinding, parentFragment: Fragment?) {
        ProductRepository(parentFragment).getDatas(binding)
    }

}
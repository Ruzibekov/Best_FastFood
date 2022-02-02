package com.ruzibekov.needfood_r.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ruzibekov.needfood_r.R
import com.ruzibekov.needfood_r.databinding.FragmentContenerBinding

class ContenerFragment : Fragment(R.layout.fragment_contener) {
    private lateinit var binding: FragmentContenerBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentContenerBinding.inflate(layoutInflater)


    }

}
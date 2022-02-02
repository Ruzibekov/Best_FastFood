package com.ruzibekov.needfood_r.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.ruzibekov.needfood_r.R
import com.ruzibekov.needfood_r.databinding.FragmentMainBinding


class MainFragment : Fragment(R.layout.fragment_main) {
    private lateinit var binding: FragmentMainBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)
        binding.bottomNav
        setFragment(HomeFragment())

        binding.bottomNav.setOnItemSelectedListener { menu ->
            when(menu.itemId){
                R.id.menu_home -> setFragment(HomeFragment())
                R.id.menu_save -> setFragment(SaveFragment())
                R.id.menu_account -> setFragment(AccountFragment())
            }
            return@setOnItemSelectedListener true
        }
    }


    private fun setFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.main_fragment_container, fragment).commit()
    }
}
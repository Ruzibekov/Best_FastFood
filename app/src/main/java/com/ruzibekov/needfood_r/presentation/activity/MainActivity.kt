package com.ruzibekov.needfood_r.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ruzibekov.needfood_r.R
import com.ruzibekov.needfood_r.databinding.ActivityMainBinding
import com.ruzibekov.needfood_r.presentation.fragments.AccountFragment
import com.ruzibekov.needfood_r.presentation.fragments.MainFragment
import com.ruzibekov.needfood_r.presentation.fragments.SaveFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}
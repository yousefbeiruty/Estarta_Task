package com.it.yousefl.estartatask.ui.main


import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.it.yousefl.estartatask.BaseActivity
import com.it.yousefl.estartatask.R
import com.it.yousefl.estartatask.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


private const val TAG = "MainActivity"

@AndroidEntryPoint
class MainActivity : BaseActivity(){

    private var flag: Boolean? = null

    private lateinit var binding: ActivityMainBinding
    lateinit var  navHostFragment:NavHostFragment
    lateinit var navController:NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
         navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
         navController = navHostFragment.navController
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }


}
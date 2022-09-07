package com.jhcreativedeveloper.BarcodeScanner.Games

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.jhcreativedeveloper.BarcodeScanner.BaseActivity
import com.jhcreativedeveloper.BarcodeScanner.R
import com.jhcreativedeveloper.BarcodeScanner.databinding.ActivityOneForAllBinding



class OneActivityForAll : BaseActivity<ActivityOneForAllBinding>(){

    companion object{
        fun openOneActivityForAll(context: Context? ,  type: String?, title: String?,){
            context?.startActivity(Intent(context, OneActivityForAll::class.java).apply {
                putExtra("Type", type)
                putExtra("Title", title)
            })
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOneForAllBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        initData()
    }

    private fun initData() {
        val type = intent.getStringExtra("Type")

        when (type) {
            "AllGames" -> {
                setTitle(intent.getStringExtra("Title"))
                addFragment(AllGamesFragmentFragment.newInstance())
            }
            "Scanner history" -> {
                setTitle(intent.getStringExtra("Title"))
                addFragment(ScannerDataFragment.newInstance())
            }
        }
    }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
    }
}
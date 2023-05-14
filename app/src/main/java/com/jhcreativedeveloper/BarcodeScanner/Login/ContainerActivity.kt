package com.jhcreativedeveloper.BarcodeScanner.Login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.jhcreativedeveloper.BarcodeScanner.BaseActivity
import com.jhcreativedeveloper.BarcodeScanner.Games.Utilites.DATA
import com.jhcreativedeveloper.BarcodeScanner.Games.Utilites.TYPE
import com.jhcreativedeveloper.BarcodeScanner.R
import com.jhcreativedeveloper.BarcodeScanner.databinding.ActivityContainerBinding

class ContainerActivity : BaseActivity<ActivityContainerBinding>() {

    companion object{
        fun openContainer(
            context: Context?,
            type:String,
            data: Any,
        ){
            context?.startActivity(Intent(context,ContainerActivity::class.java).apply {
                putExtra(TYPE, type)
                if (data is Bundle)
                    putExtras(data)
                else if (data != null)
                    putExtra(DATA, Gson().toJson(data))
            })
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContainerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        setDataFragment()
    }

    private fun setDataFragment() {
        val type = intent?.getStringExtra(TYPE)
        when(type){
            "LoginFragment"->{
                addFragment(LoginFragment.newInstance())
            }
            "RegisterFragment"->{
                addFragment(RegisterFragment.newInstance())
            }
        }
    }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.container,fragment).commit()
    }
}
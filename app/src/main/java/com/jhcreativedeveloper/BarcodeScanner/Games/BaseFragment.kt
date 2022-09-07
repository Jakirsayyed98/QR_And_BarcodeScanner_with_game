package com.jhcreativedeveloper.BarcodeScanner.Games

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding

import com.jhcreativedeveloper.BarcodeScanner.Games.APIRequest.RequestViewModel
import com.jhcreativedeveloper.BarcodeScanner.Games.Interface.LoaderListener
import com.jhcreativedeveloper.BarcodeScanner.Games.Utilites.toast



abstract class BaseFragment<t : ViewBinding> : Fragment(), LoaderListener {
    lateinit var viewModel: RequestViewModel
    var binding: t? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RequestViewModel::class.java)
    }

    fun statusBarColor(color: Int) {
        activity?.window?.statusBarColor = ContextCompat.getColor(requireContext(), color)
    }

//    fun statusBarTextWhite() {
////        activity?.theme=ContextCompat.get
//        activity?.theme?.applyStyle(R.style.Theme_Tapfo_BlackStatusBar, true)
//    }

    fun statusBarTextBlack() {
//        activity?.theme=ContextCompat.get
     //   activity?.theme?.applyStyle(R.style.TextViewBold, true)
    }

    fun statusBarTextWhite() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity?.window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
    }

    override fun showLoader() {
        kotlin.runCatching {
            LoaderFragment.showLoader(childFragmentManager)
        }
    }

    override fun dismissLoader() {
        kotlin.runCatching {
            LoaderFragment.dismissLoader(childFragmentManager)
        }
    }

    override fun showMess(mess: String?) {
       context?.toast(mess)
    }

//    fun getSharedPreference(): AppSharedPreference {
//        return AppSharedPreference.getInstance(requireContext())
//    }
//
//    fun getUserId(): String {
//        getSharedPreference().getLoginData()?.let {
//            it.id?.let { it1 ->
//                return it1
//            }
//        }
//        return ""
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        _binding = null
//    }

}
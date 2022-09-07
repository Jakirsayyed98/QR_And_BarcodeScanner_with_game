package com.jhcreativedeveloper.BarcodeScanner.Games

import android.content.ContentValues.TAG
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.jhcreativedeveloper.BarcodeScanner.R


class LoaderFragment : DialogFragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable=false
        arguments?.let {


        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_loader, container, false)
    }

    companion object {

        fun showLoader(fragmentManager: FragmentManager) {
            //Jakir
//            try {
//                newInstance().show(fragmentManager, TAG)
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
        }

        fun dismissLoader(fragmentManager: FragmentManager) {
            try {
                val frag = fragmentManager.findFragmentByTag(TAG)
                if (frag is LoaderFragment && frag.isVisible)
                    frag.dismiss()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        @JvmStatic
        fun newInstance() =
            LoaderFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

}
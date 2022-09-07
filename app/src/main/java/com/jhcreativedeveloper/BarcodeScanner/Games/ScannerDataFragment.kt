package com.jhcreativedeveloper.BarcodeScanner.Games

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.jhcreativedeveloper.BarcodeScanner.databinding.FragmentScannerDataBinding


class ScannerDataFragment : BaseFragment<FragmentScannerDataBinding>() {

    //lateinit var scannerviewmodel: ScannerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentScannerDataBinding.inflate(inflater,container,false)

//        scannerviewmodel=ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application = Application())).get(ScannerViewModel::class.java)
//        scannerviewmodel.allScannData.observe(requireActivity(), Observer {
//        })

        return binding?.root
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            ScannerDataFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}
package com.jhcreativedeveloper.BarcodeScanner.Games.Interface

interface LoaderListener2 {
    fun showLoader()
    fun dismissLoader()
    fun showMess(mess: String?)
    fun onSuccess(pos: Int, data: Any?, mess: String?, type: String?)
}
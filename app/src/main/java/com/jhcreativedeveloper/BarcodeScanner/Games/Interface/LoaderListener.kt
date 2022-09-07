package com.jhcreativedeveloper.BarcodeScanner.Games.Interface

interface LoaderListener {
    fun showLoader()
    fun dismissLoader()
    fun showMess(mess:String?)
}
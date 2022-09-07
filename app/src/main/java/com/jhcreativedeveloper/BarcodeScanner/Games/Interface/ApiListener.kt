package com.jhcreativedeveloper.BarcodeScanner.Games.Interface

import com.jhcreativedeveloper.BarcodeScanner.Games.ViewModel.BaseRes


interface ApiListener<T> {
    fun onSuccess(t: T?, mess: String?)
    fun onError(mess: String?) {}
    fun onResponse(t: T?, loaderListener: LoaderListener?) {

        loaderListener?.dismissLoader()
        if (t is BaseRes) {
            if (t.errorCode == "0") {

                 onSuccess(t, t.errorMsg)
            } else {
                if(t.type!="check_passcode") {
                    loaderListener?.showMess(t.errorMsg)
                }
                onError(t.errorMsg)
            }
        }else{
            onSuccess(t, "")
        }
    }
}
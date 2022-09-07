package com.jhcreativedeveloper.BarcodeScanner.Games.APIRequest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jhcreativedeveloper.BarcodeScanner.Games.Interface.ApiListener
import com.jhcreativedeveloper.BarcodeScanner.Games.Interface.LoaderListener
import com.jhcreativedeveloper.BarcodeScanner.Games.ModelClasses.AllGames
import com.jhcreativedeveloper.BarcodeScanner.Games.Utilites.setErrorHandler


import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RequestViewModel : ViewModel() {
    private var loadLis: LoaderListener? = null

    fun GetGames(
      //  id: String?,
        loadLis: LoaderListener?,
        listener: ApiListener<AllGames>,
    ) {
        this.loadLis = loadLis
        loadLis?.showLoader()
        viewModelScope.launch(setErrorHandler(loadLis)) {
            withContext(coroutineContext) {
                myapi().getGameZopgames().body()
                    ?.let {
                        listener.onResponse(it, loadLis)
                    }
            }
        }
    }
}
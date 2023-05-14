package com.jhcreativedeveloper.BarcodeScanner.Games.Utilites

import android.content.Context
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import com.jhcreativedeveloper.BarcodeScanner.Games.Interface.LoaderListener

import kotlinx.coroutines.CoroutineExceptionHandler
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.Random

fun Context.toast(mess: String?) {
    Toast.makeText(this, mess, Toast.LENGTH_SHORT).show()
}

fun setErrorHandler(loadLis: LoaderListener?): CoroutineExceptionHandler {
    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
        loadLis?.dismissLoader()
        if (throwable is UnknownHostException || throwable is SocketTimeoutException) {
            loadLis?.showMess("Internet error")
        } else {
            loadLis?.showMess(throwable.message)
        }
    }
    return exceptionHandler
}

fun String.isValidEmail(): Boolean {
    return !TextUtils.isEmpty(this) && Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

fun RandomeString():String{
    val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
    return (1..5)
        .map { allowedChars.random() }
        .joinToString("")

}
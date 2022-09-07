package com.jhcreativedeveloper.BarcodeScanner

import android.Manifest
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import me.dm7.barcodescanner.zxing.ZXingScannerView
import android.os.Bundle
import android.widget.Toast
import com.karumi.dexter.Dexter
import com.karumi.dexter.listener.single.PermissionListener
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.PermissionToken
import com.jhcreativedeveloper.BarcodeScanner.classes.AppDatabase
import androidx.room.Room
import com.google.zxing.Result
import com.jhcreativedeveloper.BarcodeScanner.classes.MyDataBase
import com.karumi.dexter.listener.PermissionRequest

class ScannerViewActivity : AppCompatActivity(), ZXingScannerView.ResultHandler {
    var scannerView: ZXingScannerView? = null
    var data=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        scannerView = ZXingScannerView(this)
        setContentView(scannerView)
        Dexter.withContext(this)
            .withPermission(Manifest.permission.CAMERA)
            .withListener(object : PermissionListener {
                override fun onPermissionGranted(response: PermissionGrantedResponse) {
                    scannerView!!.startCamera()
                }

                override fun onPermissionDenied(response: PermissionDeniedResponse) {}
                override fun onPermissionRationaleShouldBeShown(
                    permission: PermissionRequest,
                    token: PermissionToken
                ) {
                    token.continuePermissionRequest()
                }
            }).check()
    }

    override fun handleResult(rawResult: Result) {
        data=rawResult.toString()
        resultData=rawResult.toString()
//        MainActivity.barcode_info!!.text = rawResult.text
  //      MainActivity.barcode_info!!.visibility = View.VISIBLE
    //    MainActivity.LinearLayout!!.visibility = View.VISIBLE
        dbTheread().start()
        startActivity(Intent(this,MainActivity::class.java))
        Toast.makeText(this,"Your Scan Data saved in history",Toast.LENGTH_SHORT).show()
        intent.putExtra("data",rawResult.toString())
        finish()
//        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        onBackPressed()
    }

    override fun onPause() {
        super.onPause()
        scannerView!!.stopCamera()
    }

    override fun onResume() {
        super.onResume()
        scannerView!!.setResultHandler(this)
        scannerView!!.startCamera()
    }

    companion object{
        var resultData=""
    }

    internal inner class dbTheread : Thread() {
        override fun run() {
            super.run()
            val db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "database_room"
            ).build()
            val userDao = db.userDao()
            if (data.isNullOrEmpty()){

            }else
            userDao.insertRecord(MyDataBase(0, data))
        }
    }
}
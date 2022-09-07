package com.jhcreativedeveloper.BarcodeScanner

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.jhcreativedeveloper.BarcodeScanner.Games.APIRequest.RequestViewModel
import com.jhcreativedeveloper.BarcodeScanner.Games.AbooutUsPage
import com.jhcreativedeveloper.BarcodeScanner.Games.Interface.LoaderListener
import com.jhcreativedeveloper.BarcodeScanner.Games.Privacy_Policy
import hotchemi.android.rate.AppRate


open class BaseActivity <T : ViewBinding> : AppCompatActivity(), LoaderListener {

    lateinit var binding: T
    lateinit var viewModel: RequestViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)

        val actionBar: ActionBar?
        actionBar = supportActionBar

        val colorDrawable = ColorDrawable(Color.WHITE)

        // Set BackgroundDrawable

        // Set BackgroundDrawable
        actionBar!!.setBackgroundDrawable(colorDrawable)

//        supportActionBar!!.hide()
        AppRate.with(this)
            .setInstallDays(1)
            .setLaunchTimes(3)
            .setRemindInterval(2)
            .monitor()


        viewModel = ViewModelProvider(this).get(RequestViewModel::class.java)
    }
    fun belowStatusBar() {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
    }
    @RequiresApi(Build.VERSION_CODES.M)
    fun statusBarTextWhite() {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }
    fun statusBarColor(color:Int) {
        window.statusBarColor = ContextCompat.getColor(this,color)

    }

    override fun showLoader() {
        //LoaderFragment.showLoader(supportFragmentManager)
    }

    override fun dismissLoader() {
        //LoaderFragment.dismissLoader(supportFragmentManager)
    }

    override fun showMess(mess: String?) {

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val data="https://play.google.com/store/apps/details?id=com.jhcreativedeveloper.BarcodeScanner"
        val moreApps="https://play.google.com/store/apps/dev?id=5499662467976344220"

        when (item.itemId) {
            R.id.Rating ->{
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(data.toString()))
                startActivity(browserIntent)
            }
            R.id.PrivacyPolicy ->{
                startActivity(Intent(this, Privacy_Policy::class.java))
            }
            R.id.AboutUs ->{
                startActivity(Intent(this, AbooutUsPage::class.java))
            }
            R.id.more ->{
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(moreApps.toString()))
                startActivity(browserIntent)
            }
            R.id.share ->{

                val sharingIntent = Intent(Intent.ACTION_SEND)
                sharingIntent.type = "text/plain"
                val shareBody =
                    """
                    https://play.google.com/store/apps/details?id=com.jhcreativedeveloper.BarcodeScanner

                   Download this Qr and Barcode scanner app and with this enjoy 200+ games for free without install any games

                    """.trimIndent()
                val shareSub = "Look into this Application "
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, shareSub)
                sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody)
                startActivity(Intent.createChooser(sharingIntent, "Share using"))

            }
        }

        return super.onOptionsItemSelected(item)

    }


}
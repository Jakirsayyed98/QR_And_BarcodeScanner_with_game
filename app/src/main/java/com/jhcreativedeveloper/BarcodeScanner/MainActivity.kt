package com.jhcreativedeveloper.BarcodeScanner

import androidx.recyclerview.widget.RecyclerView
import android.os.Bundle
import android.widget.TextView
import android.widget.LinearLayout
import android.content.Intent
import android.content.ClipData
import android.content.ClipboardManager
import android.net.Uri
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.jhcreativedeveloper.BarcodeScanner.RoomDB.AppDatabase
import androidx.room.Room
import androidx.recyclerview.widget.LinearLayoutManager
import com.jhcreativedeveloper.BarcodeScanner.AdapterData.MyAdapter
import com.jhcreativedeveloper.BarcodeScanner.Games.GameAdapter.BannerAdapter
import com.jhcreativedeveloper.BarcodeScanner.Games.GameAdapter.HorizontalGameAdapter
import com.jhcreativedeveloper.BarcodeScanner.Games.GameAdapter.Popular2GamesAdapter
import com.jhcreativedeveloper.BarcodeScanner.Games.GameAdapter.PopulargameAdapter
import com.jhcreativedeveloper.BarcodeScanner.Games.GamesWebViewActivity
import com.jhcreativedeveloper.BarcodeScanner.Games.Interface.ApiListener
import com.jhcreativedeveloper.BarcodeScanner.Games.Interface.RecyclerClickListener
import com.jhcreativedeveloper.BarcodeScanner.Games.ModelClasses.AllGames
import com.jhcreativedeveloper.BarcodeScanner.Games.ModelClasses.Game
import com.jhcreativedeveloper.BarcodeScanner.Games.ModelClasses.SliderModelMain
import com.jhcreativedeveloper.BarcodeScanner.Games.OneActivityForAll
import com.jhcreativedeveloper.BarcodeScanner.databinding.ActivityMainBinding
import hotchemi.android.rate.AppRate
import kotlin.collections.ArrayList

class MainActivity : BaseActivity<ActivityMainBinding>()  {

    private var popularGames: PopulargameAdapter<Game>? = null
    private var GamesForYou: HorizontalGameAdapter<Game>? = null
    private var MostplyedGames: Popular2GamesAdapter<Game>? = null
    private var newlyAddedgames: HorizontalGameAdapter<Game>? = null
    var data=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        statusBarTextWhite()
        statusBarColor(R.color.white)
        LinearLayout = findViewById(R.id.LinearLayout)
        binding.scanBarcode.setOnClickListener {
            startActivity(Intent(applicationContext, ScannerViewActivity::class.java))
            finish()
        }
        AppRate.with(this)
            .setInstallDays(2)
            .setLaunchTimes(3)
            .setRemindInterval(2)
            .monitor()
        val scam=intent.getStringExtra("data")
        if (scam.toString().isNullOrEmpty()){
            binding.barcodeInfo.text="Hello You can play multiple game in below"
        }else{
            binding.barcodeInfo.text=scam
        }
        binding.barcodeInfo.text=ScannerViewActivity.resultData.toString()
        setresult=binding.barcodeInfo.text.toString()

        data = binding.barcodeInfo.text.toString()
        getGamesData()
        gamesAdapterAdding()
        MostPlayedGame()
        PopularGames()
        NewlyAddedGames()
        binding.History.setOnClickListener {
            startActivity(
                Intent(
                    applicationContext,
                    HistoryActivity::class.java
                )
            )
        }

        binding.openLink.setOnClickListener {
            if (data.isNullOrEmpty()) {
            } else if (data.toString().contains("http")) {
                GamesWebViewActivity.openWebView(this@MainActivity,data.toString())

            } else {
                var googleUrl = "https://www.google.co.in/search?q="
                GamesWebViewActivity.openWebView(this@MainActivity,googleUrl +data.toString())
//                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(googleUrl + data.toString()))
//                startActivity(browserIntent)
            }
        }
        binding.PLaymore.setOnClickListener {
            playAllGames()
        }

        binding.PLaymore1.setOnClickListener {
            playAllGames()
        }
        binding.copy.setOnClickListener {
            val data = binding!!.barcodeInfo.getText().toString()
            val clipboard = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
            val data1 = ClipData.newPlainText("text", data)
            clipboard.setPrimaryClip(data1)
            Toast.makeText(applicationContext, "Copied", Toast.LENGTH_SHORT).show()
        }

        binding.share.setOnClickListener {
            if(binding!!.barcodeInfo.getText().toString().isNullOrEmpty()){

            }else{
                val data = binding!!.barcodeInfo.getText().toString()
                val share = Intent(Intent.ACTION_SEND)
                share.type = "text/plain"
                share.putExtra(Intent.EXTRA_TEXT, data)
                startActivity(share)
            }

        }



        //getRoomData();

    }


    private fun playAllGames() {
        OneActivityForAll.openOneActivityForAll(this,"AllGames","All Games")
    }

    private fun gamesAdapterAdding() {
        GamesForYou = HorizontalGameAdapter(object : RecyclerClickListener {
            override fun onRecyclerItemClick(pos: Int, data: Any?, type: String) {
                val browserIntent = Intent(Intent.ACTION_VIEW,Uri.parse(data.toString()))
                startActivity(browserIntent)
                //  GamesWebViewActivity.openWebView(this@MainActivity,data.toString())
            }
        })
        binding.gamesRV.apply {
            //layoutManager = GridLayoutManager(context, 2)
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = GamesForYou
        }
    }
    private fun MostPlayedGame() {
        MostplyedGames = Popular2GamesAdapter(object : RecyclerClickListener {
            override fun onRecyclerItemClick(pos: Int, data: Any?, type: String) {
                val browserIntent = Intent(Intent.ACTION_VIEW,Uri.parse(data.toString()))
                startActivity(browserIntent)
                //  GamesWebViewActivity.openWebView(this@MainActivity,data.toString())
            }
        })
        binding.mostplayingGames.apply {
            layoutManager = GridLayoutManager(context, 3)
            //       layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = MostplyedGames
        }
    }

    private fun PopularGames() {
        popularGames = PopulargameAdapter(object : RecyclerClickListener {
            override fun onRecyclerItemClick(pos: Int, data: Any?, type: String) {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(data.toString()))
                startActivity(browserIntent)

                //GamesWebViewActivity.openWebView(this@MainActivity,data.toString())
            }
        })
        binding.PopularGames.apply {
            //layoutManager = GridLayoutManager(context, 2)
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            adapter = popularGames
        }
    }
    private fun NewlyAddedGames() {
        newlyAddedgames = HorizontalGameAdapter(object : RecyclerClickListener {
            override fun onRecyclerItemClick(pos: Int, data: Any?, type: String) {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(data.toString()))
                startActivity(browserIntent)
                //GamesWebViewActivity.openWebView(this@MainActivity,data.toString())
            }
        })
        binding.newlyAddedGames.apply {
            //  layoutManager = GridLayoutManager(context, 2)
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = newlyAddedgames
        }
    }

    private fun getGamesData() {

        viewModel.GetGames(this, object : ApiListener<AllGames> {
            override fun onSuccess(t: AllGames?, mess: String?) {

                //Newly Added Games
                t.let {
                    it!!.games.let {
                        newlyAddedgames!!.addAllItem(it.reversed().subList(0,6))
                    }
                }

                //Most played games
                t.let {
                    it!!.games.let {
                        MostplyedGames?.addAllItem(if (it.size > 9) it.subList(
                            0,
                            9
                        ) else it.shuffled())
                    }
                }

                //Popular games
                t!!.games.let {
                    val FilteredList = ArrayList<Game>()
                    it.forEach {
                        FilteredList.add(it)
                        Log.d("Games", "$it")
                    }
                    FilteredList.shuffle()
                    popularGames?.addAllItem(if (it.size > 50) FilteredList.subList(
                        0,
                        50
                    ) else FilteredList.shuffled()
                    )
                }


                //Games For You
                t.games.let {
                    val FilteredList = ArrayList<Game>()
                    it.forEach {
                        FilteredList.add(it)
                        Log.d("Games", "$it")
                    }
                    FilteredList.shuffle()
                    GamesForYou?.addAllItem(if (it.size > 20) FilteredList.subList(
                        0,
                        20
                    ) else FilteredList.shuffled()
                    )
                }

                //baneer List
                t.games.let {
                    val FilteredList = ArrayList<Game>()
                    it.forEach {
                        FilteredList.add(it)
                        Log.d("Games", "$it")
                    }
                    FilteredList.shuffle()
                    val Banner1 = ArrayList<Game>()
                    Banner1.addAll(if (it.size > 15) FilteredList.subList(0, 15) else FilteredList.shuffled())
                    bannerdata1(Banner1)
                }

                binding.animationView.visibility=View.GONE
                binding.scrollview.visibility=View.VISIBLE
            }

        })

    }

    private fun bannerdata1(banner1: java.util.ArrayList<Game>) {
        if (banner1.isNullOrEmpty()) {
            binding.banner1.visibility = View.GONE
        } else {
            binding.banner1.visibility = View.VISIBLE
        }

        val imageList = ArrayList<SliderModelMain>()
        //  bannerAdapter = banner1
        for (x in banner1) {
            imageList.add(SliderModelMain(x.assets.wall,x.url,x.name.en.toString(),x.description.en.toString()))
        }
        binding.banner1.adapter =
            BannerAdapter(
                imageList,
                binding!!.banner1,
                object : RecyclerClickListener {
                    override fun onRecyclerItemClick(pos: Int, data: Any?, type: String) {
                        GamesWebViewActivity.openWebView(this@MainActivity,data.toString())
                    }

                })
        binding.banner1.clipToPadding = false
        binding.banner1.clipChildren = false
        binding.banner1.offscreenPageLimit = 3



        binding.banner1.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        binding.banner1.setPadding(40, 0, 40, 0)

        //Handler Start
        Handler().apply {
            val runnable = object : Runnable {
                override fun run() {
                    var i = binding.banner1.currentItem

                    if (i == banner1.size - 1) {
                        i =-1// 0
                        binding.banner1.currentItem = i
                    }
                    i++
                    binding.banner1.setCurrentItem(i, true)
                    postDelayed(this, 4000)
                }
            }
            postDelayed(runnable, 4000)
        }

    }



    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    private val roomData: Unit
        private get() {
            val db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "database_room"
            ).allowMainThreadQueries().build()
            val userDao = db.userDao()
            //scan_RV!!.layoutManager = LinearLayoutManager(this)
            val dataBases = userDao!!.allData
            val adapter = MyAdapter(this, dataBases)
        //    scan_RV!!.adapter = adapter
            adapter.notifyDataSetChanged()
        }

    companion object {
        var setresult=""
        var barcode_info: TextView? = null
        var LinearLayout: LinearLayout? = null
    }
}
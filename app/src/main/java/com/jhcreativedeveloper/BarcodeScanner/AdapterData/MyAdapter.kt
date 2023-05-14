package com.jhcreativedeveloper.BarcodeScanner.AdapterData

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.jhcreativedeveloper.BarcodeScanner.Games.GamesWebViewActivity
import com.jhcreativedeveloper.BarcodeScanner.R
import com.jhcreativedeveloper.BarcodeScanner.RoomDB.MyDataBase
import java.util.*

class MyAdapter(var context: Context, var list: List<MyDataBase>) :
    RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.scandataview, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.id.text = list[position].iD.toString()
        holder.data.text = list[position].data.toString()

        val rnd = Random()
        val color: Int = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
        holder.layout.setBackgroundColor(color)

        holder.cardView.setOnClickListener {
            Toast.makeText(
                holder.id.context,
                "Data " + list[position].data,
                Toast.LENGTH_SHORT
            ).show()
            var data=list[position].data
            if (data.isNullOrEmpty()) {
            } else if (data.toString().contains("http")) {
                GamesWebViewActivity.openWebView(holder.itemView.context,data.toString())

            } else {
                var googleUrl = "https://www.google.co.in/search?q="
                GamesWebViewActivity.openWebView(holder.itemView.context,googleUrl +data.toString())
//                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(googleUrl + data.toString()))
//                startActivity(browserIntent)
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var id: TextView
        var data: TextView
        var cardView: CardView
        lateinit var layout: LinearLayout
        init {
            id = itemView.findViewById(R.id.id)
            data = itemView.findViewById(R.id.data)
            cardView = itemView.findViewById(R.id.cardView)
            layout = itemView.findViewById(R.id.layout)
        }
    }
}
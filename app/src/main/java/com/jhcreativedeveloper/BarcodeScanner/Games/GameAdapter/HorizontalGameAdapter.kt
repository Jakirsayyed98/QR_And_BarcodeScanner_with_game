package com.jhcreativedeveloper.BarcodeScanner.Games.GameAdapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.bumptech.glide.Glide
import com.jhcreativedeveloper.BarcodeScanner.Games.Interface.RecyclerClickListener
import com.jhcreativedeveloper.BarcodeScanner.Games.ModelClasses.Game
import com.jhcreativedeveloper.BarcodeScanner.databinding.HorizontalGamecardLayoutBinding

import java.text.DecimalFormat

class HorizontalGameAdapter <S>(private val clickListener: RecyclerClickListener) :
    BaseRecyclerAdapter<S, HorizontalGameAdapter<S>.Holder>() {
    private var morePos = 0
    private var showRupee = true
    var totalplays=""


    inner class Holder(private val binding: HorizontalGamecardLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun setData(s: S) {
            var name: String? = ""
            var image: String? = ""
            var banner: String? = ""
            var plays: String? = ""
            var Url: String? = ""
            var rating: String? = ""
            var ColorCode: String? = ""
            if (s is Game) {
                name = s.name.en.toString()
                image = s.assets.cover
                banner = s.assets.wall
                Url = s.url
                rating = s.rating.toString()
                ColorCode = s.colorVibrant
                plays = s.gamePlays.toString()
            }


            suffixFunction(plays!!.toDouble())

            var color= Color.parseColor(ColorCode)
            binding.card.setBackgroundColor(color)


            Glide.with(itemView.context).load(banner).centerCrop()
                // .circleCrop()
                .into(binding.banner)
            binding.name.text = name
            binding.plays.text =totalplays// plays+" Plays"
            binding.root.setOnClickListener {
                clickListener.onRecyclerItemClick(0,Url , "")
//                    WebViewActivity.openWebView(it.context,url,miniAppId,image,tCashType,isFavourite)
            }
        }
    }

    private fun suffixFunction(count: Double) :String {
        val df = DecimalFormat("#.#")
        totalplays = if (Math.abs(count / 1000000) >= 1) {
            df.format(count / 1000000.0).toString() + "M Plays"
        } else if (Math.abs(count / 1000.0) >= 1) {
            df.format(count / 1000.0).toString() + "K Plays"
        } else {
            count.toString()+" Plays"
        }
        return totalplays;
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            HorizontalGamecardLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.setData(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
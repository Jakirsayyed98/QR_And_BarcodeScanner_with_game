package com.jhcreativedeveloper.BarcodeScanner.Games.GameAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.jhcreativedeveloper.BarcodeScanner.Games.Interface.RecyclerClickListener
import com.jhcreativedeveloper.BarcodeScanner.Games.ModelClasses.SliderModelMain
import com.jhcreativedeveloper.BarcodeScanner.R

class BannerAdapter internal constructor(sliderItem: MutableList<SliderModelMain>, viewPager: ViewPager2, val clickListener: RecyclerClickListener):
    RecyclerView.Adapter<BannerAdapter.SliderViewHolder>(){
    lateinit var context: Context
    val SliderItemlist:List<SliderModelMain>
    val ViewPager2: ViewPager2


    init {
        this.SliderItemlist=sliderItem
        this.ViewPager2=viewPager
    }

    class SliderViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val sliderItem: ImageView =itemView.findViewById(R.id.bannerImg)

        fun SliderData(SliderImageItem: SliderModelMain){

            Glide.with(itemView.context).load(SliderImageItem.image).centerCrop().into(sliderItem)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
        return SliderViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.banner_layout,parent,false))
    }

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        holder.SliderData(SliderItemlist[position])

        holder.sliderItem.setOnClickListener {
            clickListener.onRecyclerItemClick(0, SliderItemlist[position].Url, "")
        }
    }


    override fun getItemCount(): Int {
        return SliderItemlist.size
    }

}
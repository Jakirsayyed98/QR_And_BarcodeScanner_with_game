package com.jhcreativedeveloper.BarcodeScanner.Games

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.jhcreativedeveloper.BarcodeScanner.Games.GameAdapter.Popular2GamesAdapter
import com.jhcreativedeveloper.BarcodeScanner.Games.GameAdapter.PopulargameAdapter
import com.jhcreativedeveloper.BarcodeScanner.Games.Interface.ApiListener
import com.jhcreativedeveloper.BarcodeScanner.Games.Interface.RecyclerClickListener
import com.jhcreativedeveloper.BarcodeScanner.Games.ModelClasses.AllGames
import com.jhcreativedeveloper.BarcodeScanner.Games.ModelClasses.Game
import com.jhcreativedeveloper.BarcodeScanner.R
import com.jhcreativedeveloper.BarcodeScanner.databinding.FragmentAllGamesFragmentBinding
import java.util.*


class AllGamesFragmentFragment : BaseFragment<FragmentAllGamesFragmentBinding>() {
 //   private var adapterGames: Popular2GamesAdapter<Game>? = null
    private var adapterGames: PopulargameAdapter<Game>? = null
    private lateinit var itemList: List<Game>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAllGamesFragmentBinding.inflate(inflater, container, false)
        GetGameData()
        statusBarColor(R.color.white)
        statusBarTextWhite()
        getAllGames()
        binding?.searchEt?.addTextChangedListener( object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filterListData(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
        return binding?.root
    }

    private fun filterListData(searchName: String) {
        if (itemList.isNullOrEmpty()){
            Toast.makeText(requireContext(),"No Data Found",Toast.LENGTH_SHORT).show()
        }else{

            var tempList: ArrayList<Game> = ArrayList()
            for (x in itemList) {
                if (searchName.lowercase(Locale.getDefault()) in x.name.toString()
                        .lowercase(Locale.getDefault())
                ) {
                    tempList.add(x)
                }
            }
            adapterGames!!.updatelist(tempList)

        }

    }

    private fun getAllGames() {
        adapterGames = PopulargameAdapter(object : RecyclerClickListener {
            override fun onRecyclerItemClick(pos: Int, data: Any?, type: String) {

                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(data.toString()))
                startActivity(browserIntent)

            }
        })
        binding!!.AllGames.apply {
//            layoutManager = GridLayoutManager(context, 3)
             layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = adapterGames
        }
    }

    private fun GetGameData() {
        viewModel.GetGames(this, object : ApiListener<AllGames> {
            override fun onSuccess(t: AllGames?, mess: String?) {
                t.let {
                    it.let {
                        itemList = it!!.games
                    }
                    it?.games.let {
                        adapterGames!!.addAllItem(it!!.shuffled())
                    }
                }
                binding!!.animationView.visibility = View.GONE
            }
        })
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            AllGamesFragmentFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}





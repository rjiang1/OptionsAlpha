package com.example.optionsalpha

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView

class SearchFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_search, container, false)

        initRecycler(view)

        return view
    }

    private fun initRecycler(view: View?) {
        val recycler = view?.findViewById<RecyclerView>(R.id.stonkList)
        recycler!!.layoutManager = androidx.recyclerview.widget.GridLayoutManager(activity,1)
        val tickers = StockData().tickers
        val myAdapter = StockAdapter(tickers)
        recycler.adapter = myAdapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }


}
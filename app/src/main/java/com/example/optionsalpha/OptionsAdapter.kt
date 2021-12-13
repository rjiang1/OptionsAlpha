package com.example.optionsalpha

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.optionsalpha.OptionsAdapter.*
import com.google.firebase.database.Query
import kotlinx.android.synthetic.main.stock_list_item.view.*
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions

class OptionsAdapter(var modelClass : Class<Option>, var query: Query) : FirebaseRecyclerAdapter<Option, OptionsAdapter.ViewHolder>(
    FirebaseRecyclerOptions.Builder<Option>()
        .setQuery(query, modelClass)
        .build()
) {

    var myListener: MyItemClickListener? = null
    interface MyItemClickListener {
        fun onItemClick(view: View, position: Int)
    }


    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.stock_list_item, parent, false)
        // inflate item view
        return ViewHolder(view)
    }

    fun setMyItemClickListener (listener: HomeFragment){
        this.myListener = listener
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // hold textview

        var tickerName: TextView = itemView.findViewById(R.id.ticker_name)


        init {
            itemView.setOnClickListener {
                if(myListener != null){
                    if(adapterPosition != RecyclerView.NO_POSITION){
                        myListener!!.onItemClick(it, adapterPosition)
                    }
                }
            }


        }
    }


    override fun onBindViewHolder(p0: ViewHolder, p1: Int, p2: Option) {
        p0.tickerName.text = p2.contract
    }

}
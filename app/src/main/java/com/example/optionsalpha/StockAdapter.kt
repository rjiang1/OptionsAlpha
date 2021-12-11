package com.example.optionsalpha

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.stock_list_item.view.*

class StockAdapter(val items : List<String>) : androidx.recyclerview.widget.RecyclerView.Adapter<StockAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.stock_list_item, parent, false)
        // inflate item view
        return ViewHolder(view)
    }

    inner class ViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {
        // hold textview

        val ticker_name = itemView.ticker_name


//        init {
//            itemView.setOnClickListener {
//                if(myListener != null){
//                    if(adapterPosition != androidx.recyclerview.widget.RecyclerView.NO_POSITION){
//                        myListener!!.onItemClick(it, adapterPosition)
//                    }
//                }
//            }
//
//
//        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.ticker_name.text = items.get(index = position)
    }

    override fun getItemCount(): Int {
        return items.size
    }

}
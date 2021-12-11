package com.example.optionsalpha

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.stock_list_item.view.*

class OptionsAdapter(val items : List<String>) : androidx.recyclerview.widget.RecyclerView.Adapter<OptionsAdapter.ViewHolder>() {

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

    inner class ViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {
        // hold textview

        val ticker_name = itemView.ticker_name


        init {
            itemView.setOnClickListener {
                if(myListener != null){
                    if(adapterPosition != androidx.recyclerview.widget.RecyclerView.NO_POSITION){
                        myListener!!.onItemClick(it, adapterPosition)
                    }
                }
            }


        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.ticker_name.text = items.get(index = position)
    }

    override fun getItemCount(): Int {
        return items.size
    }

}
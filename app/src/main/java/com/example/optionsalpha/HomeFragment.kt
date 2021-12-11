package com.example.optionsalpha
import android.os.Bundle
import android.telecom.RemoteConference
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.google.firebase.database.FirebaseDatabase
import com.squareup.moshi.Moshi

import org.w3c.dom.Text
import java.io.IOException


class HomeFragment(var display : String) : Fragment(), OptionsAdapter.MyItemClickListener {
//    lateinit var display_response : String
//    val queue = Volley.newRequestQueue(activity)
    lateinit var myAdapter : OptionsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_home, container, false)
        initRecycler(view)

        view.findViewById<TextView>(R.id.httpDisplay).text = display



//        http2()

        return view
    }

    fun changetxt(s : String, view: TextView){
        view.text = s
    }




//    fun http2() {
//
//        val url = "https://api.polygon.io/v2/aggs/ticker/O:TSLA210903C00700000/range/1/day/2021-07-22/2021-07-22?adjusted=true&sort=asc&limit=120&apiKey=uZHypCwLqUXRJRbB248YPUB0PHQBrUTu"
//        val jsonObjectRequest = JsonObjectRequest(
//            Request.Method.GET, url, null,
//            {
//                    response ->
//                response.getJSONArray("results")
//
//                display_response = response.toString()
//                val sad = response.toString()
//            },
//            { error ->
//                // TODO: Handle error
//                val dla = error
//            }
//        )
//        // Access the RequestQueue through your singleton class.
////        MySingleton.getInstance().addToRequestQueue(jsonObjectRequest)
//    }




    private fun initRecycler(view: View?) {
        val recycler = view?.findViewById<RecyclerView>(R.id.portfolio)
        recycler!!.layoutManager = androidx.recyclerview.widget.GridLayoutManager(activity,1)
//        val tickers = StockData().portfolio_tickers
//        var myAdapter = MyFirebaseRecyclerAdapter

        var query = FirebaseDatabase.getInstance()
            .reference
            .child("tickers")
            .limitToLast(50)


        myAdapter = OptionsAdapter(Option::class.java,query)
        recycler.adapter = myAdapter
        myAdapter.setMyItemClickListener(this)
    }

    override fun onItemClick(view: View, position: Int) {
//        Toast.makeText(context,"click option",Toast.LENGTH_SHORT)


        activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.frag_holder,OptionDetail("Quote Input"))
            ?.addToBackStack("f0")
            ?.commit()
    }

    override fun onStart() {
        super.onStart()
        myAdapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        myAdapter.stopListening()
    }


//    fun jsonParse(view: TextView){
//        var client = OkHttpClient()
//        var url = "https://api.polygon.io/v2/aggs/ticker/O:TSLA210903C00700000/range/1/day/2021-12-01/2021-12-10?adjusted=true&sort=asc&limit=120&apiKey=uZHypCwLqUXRJRbB248YPUB0PHQBrUTu"
//        var request = okhttp3.Request.Builder().url(url).build()
//        request.
//        var response = client.newCall(request).enqueue(
//
//    }

}
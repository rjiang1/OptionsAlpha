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
import com.squareup.moshi.Moshi
import okhttp3.*
import org.w3c.dom.Text
import java.io.IOException


class HomeFragment : Fragment(), OptionsAdapter.MyItemClickListener {

//    val queue = Volley.newRequestQueue(activity)
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

//        http()

        return view
    }

    fun changetxt(s : String, view: TextView){
        view.text = s
    }

    fun http(){
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://api.polygon.io/v2/aggs/ticker/O:TSLA210903C00700000/range/1/day/2021-12-01/2021-12-10?adjusted=true&sort=asc&limit=120&apiKey=uZHypCwLqUXRJRbB248YPUB0PHQBrUTu")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    var myResp = response.body?.string()

                }

                for ((name, value) in response.headers) {
                    println("$name: $value")
                }

                println(response.body!!.string())

            }
        })

    }

    private fun initRecycler(view: View?) {
        val recycler = view?.findViewById<RecyclerView>(R.id.portfolio)
        recycler!!.layoutManager = androidx.recyclerview.widget.GridLayoutManager(activity,1)
        val tickers = StockData().portfolio_tickers
        val myAdapter = OptionsAdapter(tickers)
        recycler.adapter = myAdapter
        myAdapter.setMyItemClickListener(this)
    }

    override fun onItemClick(view: View, position: Int) {
//        Toast.makeText(context,"click option",Toast.LENGTH_SHORT)


        activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.frag_holder,OptionDetail("Quote Input"))
            ?.addToBackStack("f0")
            ?.commit()
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
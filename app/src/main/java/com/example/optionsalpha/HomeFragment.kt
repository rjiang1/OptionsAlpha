package com.example.optionsalpha
import android.os.Bundle
import android.telecom.RemoteConference
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.squareup.moshi.Moshi
import okhttp3.*
import org.w3c.dom.Text
import java.io.IOException


class HomeFragment : Fragment() {

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
        val txtView = view.findViewById<TextView>(R.id.sample)

//        txtView.setOnClickListener(){
//            txtView.text = "poop"
//            jsonParse(view.findViewById(R.id.upperTxt))
//        }

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



//    fun jsonParse(view: TextView){
//        var client = OkHttpClient()
//        var url = "https://api.polygon.io/v2/aggs/ticker/O:TSLA210903C00700000/range/1/day/2021-12-01/2021-12-10?adjusted=true&sort=asc&limit=120&apiKey=uZHypCwLqUXRJRbB248YPUB0PHQBrUTu"
//        var request = okhttp3.Request.Builder().url(url).build()
//        request.
//        var response = client.newCall(request).enqueue(
//
//    }

}
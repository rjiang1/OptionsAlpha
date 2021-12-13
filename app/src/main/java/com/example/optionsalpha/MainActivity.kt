package com.example.optionsalpha

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader

class MainActivity : AppCompatActivity() {
    var search_frag = SearchFragment()
    lateinit var displayName : String
    val map = HashMap<String,Float>()
    lateinit var display_response : String


    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ) { res ->
        this.onSignInResult(res)
    }
//    var homeFrag = HomeFragment(map.get("close_price").toString()))
    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
        val response = result.idpResponse
        if (result.resultCode == RESULT_OK) {
            // Successfully signed in
            val user = FirebaseAuth.getInstance().currentUser
            displayName = user?.displayName.toString()
            Toast.makeText(this,"Hello, {$displayName}!",Toast.LENGTH_LONG)

        } else {
            // Sign in failed. If response is null the user canceled the
            // sign-in flow using the back button. Otherwise check
            // response.getError().getErrorCode() and handle the error.
            // ...
        }
    }

    fun extractCustomJSON(obj : JSONObject){

        map.put("close_price", obj.get("c") as Float)
    }

    fun http2() {
        val url = "https://api.polygon.io/v2/aggs/ticker/O:TSLA210903C00700000/range/1/day/2021-07-22/2021-07-22?adjusted=true&sort=asc&limit=120&apiKey=uZHypCwLqUXRJRbB248YPUB0PHQBrUTu"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            {
                    response ->
                response.getJSONArray("results")
                display_response = response.toString()
                val sad = response.toString()
            },
            { error ->
                // TODO: Handle error
                val dla = error
            }
        )
        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }

    private fun createSignInIntent() {
        // [START auth_fui_create_intent]
        // Choose authentication providers
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build())
//            AuthUI.IdpConfig.PhoneBuilder().build())

        // Create and launch sign-in intent
        val signInIntent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .build()
        signInLauncher.launch(signInIntent)
        // [END auth_fui_create_intent]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        http2()
//        runBlocking {
//            val job : Job = launch(context = Dispatchers.Default){
//                delay(10000)
//            }
//        }

        createSignInIntent()

        supportFragmentManager.beginTransaction().add(R.id.frag_holder,HomeFragment("display_response")).addToBackStack("f0").commit()
//        actionBar!!.title = "Home"
//        var tickers = StockData().tickers


//        changeText()
//        var homeFrags = supportFragmentManager.findFragmentById(R.id.homeFragment)
//        homeFrags.updateT

    }

    private fun signOut() {
        // [START auth_fui_signout]
        AuthUI.getInstance()
            .signOut(this)
            .addOnCompleteListener {
                Toast.makeText(this,"Bye!", Toast.LENGTH_SHORT)
            }
        // [END auth_fui_signout]
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_toolbar,menu)
        val search = menu?.findItem(R.id.app_bar_search)
        val searchView = search?.actionView as SearchView
        searchView.queryHint = "Search a Ticker"
        setUpBottomNav(search)

        searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                search_frag.searchCard(p0!!)
                return true
            }
            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }
        })

        return super.onCreateOptionsMenu(menu)
    }


    fun setUpBottomNav(search : MenuItem){

        val navBar = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        navBar.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.nav_home -> {
                    search.title = "Home"
                    search.isVisible = false
                    supportFragmentManager?.beginTransaction()?.replace(R.id.frag_holder,HomeFragment(map.get("close_price").toString())).addToBackStack("f0").commit()
                    true
                }

                R.id.nav_search -> {
                    search.title = "Search"
                    search.isVisible = true
                    supportFragmentManager?.beginTransaction()?.replace(R.id.frag_holder,SearchFragment()).addToBackStack("f1").commit()
                    true
                }

                R.id.nav_profile ->{
                    search.isVisible = false
                    val viewFrag = ProfileFragment()
                    supportFragmentManager?.beginTransaction()?.replace(R.id.frag_holder,viewFrag).addToBackStack("f2").commit()
                    signOut()
                    Toast.makeText(this,"Bye!", Toast.LENGTH_SHORT)
//                    val signOutButton = viewFrag.activity?.findViewById<Button>(R.id.signOutButton)
//                    signOutButton?.setOnClickListener{
//                        signOut()
//                    }

                    true
                }
                else -> false
            }
        }
    }




}


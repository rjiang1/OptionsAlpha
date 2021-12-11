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
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult

import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader

class MainActivity : AppCompatActivity() {
//    val queue = Volley.newRequestQueue(this)


    // See: https://developer.android.com/training/basics/intents/result
    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ) { res ->
        this.onSignInResult(res)
    }
    var homeFrag = HomeFragment()
    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
        val response = result.idpResponse
        if (result.resultCode == RESULT_OK) {
            // Successfully signed in
            val user = FirebaseAuth.getInstance().currentUser



//            supportFragmentManager.beginTransaction().add(R.id.frag_holder,homeFrag).addToBackStack("f0").commit()

            // ...
        } else {
            // Sign in failed. If response is null the user canceled the
            // sign-in flow using the back button. Otherwise check
            // response.getError().getErrorCode() and handle the error.
            // ...
        }
    }

    private fun createSignInIntent() {
        // [START auth_fui_create_intent]
        // Choose authentication providers
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.PhoneBuilder().build())

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



        createSignInIntent()

        supportFragmentManager.beginTransaction().add(R.id.frag_holder,homeFrag).addToBackStack("f0").commit()
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

//        searchView.setOnQueryTextListener(object)



        return super.onCreateOptionsMenu(menu)
    }




    fun setUpBottomNav(search : MenuItem){

        val navBar = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        navBar.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.nav_home -> {
                    search.title = "Home"
                    search.isVisible = false
                    supportFragmentManager?.beginTransaction()?.replace(R.id.frag_holder,HomeFragment()).addToBackStack("f0").commit()
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

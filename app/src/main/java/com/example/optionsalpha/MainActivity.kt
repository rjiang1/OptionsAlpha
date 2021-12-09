package com.example.optionsalpha

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.MenuItem
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val nav_bar = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        nav_bar.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.nav_home -> {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    true
                }

                R.id.nav_search -> {
                    val intent = Intent(this,SearchActivity::class.java)
                    startActivity(intent)
                    true
                }

                R.id.nav_profile ->{
                    val intent = Intent(this,ProfileActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
    }
//
//    override fun onCreateView(
//        parent: View?,
//        name: String,
//        context: Context,
//        attrs: AttributeSet
//    ): View? {
//
//
//        return super.onCreateView(parent, name, context, attrs)
//    }

}

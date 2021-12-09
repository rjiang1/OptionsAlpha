package com.example.optionsalpha

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

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
}
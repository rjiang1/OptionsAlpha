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

//        activity?.supportFragmentManager?.beginTransaction()
//            ?.add(R.id.fragmentLayout,details)?.addToBackStack("f0")?.commit()

        supportFragmentManager?.beginTransaction()?.add(R.id.frag_holder,HomeFragment()).addToBackStack("f0").commit()

        val nav_bar = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        nav_bar.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.nav_home -> {
                    supportFragmentManager?.beginTransaction()?.replace(R.id.frag_holder,HomeFragment()).addToBackStack("f0").commit()
                    true
                }

                R.id.nav_search -> {
                    supportFragmentManager?.beginTransaction()?.replace(R.id.frag_holder,SearchFragment()).addToBackStack("f1").commit()
                    true
                }

                R.id.nav_profile ->{
                    supportFragmentManager?.beginTransaction()?.replace(R.id.frag_holder,ProfileFragment()).addToBackStack("f2").commit()
                    true
                }
                else -> false
            }
        }
    }

    override fun onCreateView(
        parent: View?,
        name: String,
        context: Context,
        attrs: AttributeSet
    ): View? {


        return super.onCreateView(parent, name, context, attrs)
    }

}

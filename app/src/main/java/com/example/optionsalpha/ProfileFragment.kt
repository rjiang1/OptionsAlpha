package com.example.optionsalpha

import android.content.Context
import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.firebase.ui.auth.AuthUI


class ProfileFragment() : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        fun signOut() {
            // [START auth_fui_signout]
            AuthUI.getInstance()
                .signOut(container!!.context)
                .addOnCompleteListener {
                    Toast.makeText(container.context,"Bye!",Toast.LENGTH_SHORT)
                }
            // [END auth_fui_signout]
        }

        val btn = view.findViewById<Button>(R.id.signOutButton)
        btn.setOnClickListener{
            signOut()

        }

        return view
    }

}
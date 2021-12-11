package com.example.optionsalpha

import android.content.Context
import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.firebase.ui.auth.AuthUI


class ProfileFragment() : Fragment() {

    private fun signOut() {
        // [START auth_fui_signout]
        AuthUI.getInstance()
            .signOut(requireContext())
            .addOnCompleteListener {
                // ...
            }
        // [END auth_fui_signout]
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        val signOutButton = view.findViewById<Button>(R.id.signOutButton)
        signOutButton.setOnClickListener{
            signOut()
        }

        return view
    }

}
package com.example.suit_team1

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.suit_team1.databinding.FragmentLandingPage3Binding
import com.example.suit_team1.ui.login.LoginActivity

class LandingPage3Fragment : Fragment() {

    private lateinit var binding: FragmentLandingPage3Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLandingPage3Binding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnNext.setOnClickListener {
            Intent(requireActivity(), LoginActivity::class.java).apply {
                startActivity(this)
            }

        }
    }

}
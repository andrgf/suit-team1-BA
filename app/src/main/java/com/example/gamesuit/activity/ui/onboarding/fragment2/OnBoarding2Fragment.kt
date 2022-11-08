package com.example.gamesuit.activity.ui.onboarding.fragment2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gamesuit.databinding.FragmentOnBoarding2Binding
import com.example.gamesuit.until.UserLevel

class OnBoarding2Fragment : Fragment() {


    private var _binding: FragmentOnBoarding2Binding? = null
    private val binding get() = _binding!!
    private lateinit var presenterMain: PresenterFragment2


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOnBoarding2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenterMain = PresenterFragment2(this)

        presenterMain.getData().observe(viewLifecycleOwner) {
            val recyclerView = binding.rcTopscore
            recyclerView.setHasFixedSize(true)

            binding.rcTopscore.apply {
                recyclerView.adapter
                layoutManager = LinearLayoutManager(activity)
                recyclerView.adapter = AdapterFragment2(UserLevel.sortUsersLevel(it))
            }
        }
        }

    }
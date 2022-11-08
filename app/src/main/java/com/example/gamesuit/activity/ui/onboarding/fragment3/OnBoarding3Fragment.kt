package com.example.gamesuit.activity.ui.onboarding.fragment3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gamesuit.activity.ui.onboarding.OnboardingActivity
import com.example.gamesuit.activity.ui.onboarding.OnboardingFinishActivity
import com.example.gamesuit.activity.ui.onboarding.fragment2.AdapterFragment2
import com.example.gamesuit.activity.ui.onboarding.fragment2.PresenterFragment2
import com.example.gamesuit.databinding.FragmentOnBoarding3Binding
import com.example.gamesuit.until.UserLevel
import com.example.gamesuit.until.goto


class OnBoarding3Fragment : Fragment() {
    private var _binding: FragmentOnBoarding3Binding? = null
    private val binding get() = _binding!!
    private lateinit var presenterMain: PresenterFragment3


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnBoarding3Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenterMain = PresenterFragment3(this)

        presenterMain.getData().observe(viewLifecycleOwner) {
            val recyclerView = binding.rcHistory
            recyclerView.setHasFixedSize(true)

            binding.rcHistory.apply {
                recyclerView.adapter
                layoutManager = LinearLayoutManager(activity)
                recyclerView.adapter = AdapterFragment2(UserLevel.sortUsersLevel(it))
            }
        }

        binding.btnNext.setOnClickListener {
            (activity as OnboardingActivity).goto(OnboardingFinishActivity::class.java)
            }
        }
    }



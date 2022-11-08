package com.example.gamesuit.activity.ui.game

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.gamesuit.databinding.FragmentDialogHasilBinding

class CallbackDialogHasilFragment : DialogFragment() {
    private var dialogView: CallbackDialogHasil? = null
    private lateinit var binding: FragmentDialogHasilBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.setCancelable(false)
        binding = FragmentDialogHasilBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val result = arguments?.getString(RESULT)
        binding.tvResult.text = result

        binding.btnMainlagi.setOnClickListener {
            dismiss()
            dialogView?.reset(android.R.color.transparent)

        }

        binding.btnMenu.setOnClickListener {
            activity?.finish()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            dialogView = context as CallbackDialogHasil
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    companion object {
        const val RESULT = "result"
        const val TAG = "dialog_result"
    }
}
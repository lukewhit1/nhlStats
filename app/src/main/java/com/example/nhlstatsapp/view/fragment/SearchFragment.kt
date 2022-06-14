package com.example.nhlstatsapp.view.fragment

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import androidx.core.content.ContextCompat.getSystemService
import com.example.nhlstatsapp.R
import com.example.nhlstatsapp.databinding.FragmentSearchBinding

class SearchFragment: ViewModelFragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding: FragmentSearchBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(layoutInflater)

        binding.etInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(editable: Editable) {
                binding.btnSearchGo.isEnabled = true
            }
        })

        binding.btnSearchGo.setOnClickListener {
            val input = binding.etInput.text.toString()
            if (input.isNotBlank()) {
                viewModel.setSearchLoadingState()
                goToListFragment(input)
            }
        }

        return binding.root
    }

    private fun goToListFragment(playerName: String) {
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.main, ListFragment(playerName))
            ?.addToBackStack(null)
            ?.commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
package com.example.nhlstatsapp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.example.nhlstatsapp.databinding.PopupDetailsBinding
import com.example.nhlstatsapp.model.PlayerDisplayStats

class PopupFragment: DialogFragment() {
    private var _binding: PopupDetailsBinding? = null
    private val binding: PopupDetailsBinding? get() = _binding

    companion object {
        const val KEY = "KEY"
        fun newInstance(playerDisplayStats: PlayerDisplayStats): PopupFragment {
            val fragment = PopupFragment()
            val bundle = Bundle()
            bundle.putParcelable(KEY, playerDisplayStats)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = PopupDetailsBinding.inflate(layoutInflater)
        val playerDisplayStats: PlayerDisplayStats? = arguments?.getParcelable(KEY)

        binding.let {
            it?.let { it1 ->
                binding?.let { it2 ->
                    Glide.with(it1.ivPopupImage)
                        .load("https://www.vmcdn.ca/f/files/via/import/pitb/4590568-loui-eriksson-derp.png")
                        .into(it2.ivPopupImage)
                }
            }
        }

        binding?.tvPopupName?.text = playerDisplayStats?.fullName
        binding?.tvBirthday?.text = "Born: ${playerDisplayStats?.birthday}"
        binding?.tvHeight?.text = playerDisplayStats?.height
        binding?.tvWeight?.text = "${playerDisplayStats?.weight} lb"

        binding?.btnAddToTeam?.setOnClickListener {
            Toast.makeText(context, "TODO: db stuff", Toast.LENGTH_SHORT).show()
        }

        return binding?.root
    }
}
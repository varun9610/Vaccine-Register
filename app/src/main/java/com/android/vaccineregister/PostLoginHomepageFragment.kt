package com.android.vaccineregister

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.android.vaccineregister.databinding.FragmentPostLoginHomepageBinding

class PostLoginHomepageFragment : Fragment() {

    private var _binding: FragmentPostLoginHomepageBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPostLoginHomepageBinding.inflate(inflater, container, false)
        binding.button5.setOnClickListener {
            view?.findNavController()
                ?.navigate(R.id.action_postLoginHomepageFragment_to_registerforVaccineFragment)
        }
        binding.button6.setOnClickListener {
            view?.findNavController()
                ?.navigate(R.id.action_postLoginHomepageFragment_to_statusFragment)
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
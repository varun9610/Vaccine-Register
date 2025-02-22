package com.android.vaccineregister

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.vaccineregister.databinding.FragmentContactusBinding


class ContactusFragment : Fragment() {


    private var _binding: FragmentContactusBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContactusBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}
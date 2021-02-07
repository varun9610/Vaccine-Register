package com.android.vaccineregister

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.android.vaccineregister.databinding.FragmentTitlePageBinding

class TitlePage : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentTitlePageBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_title_page, container, false
        )
        binding.btnloginTitlePage.setOnClickListener { v: View ->
            v.findNavController().navigate(R.id.action_titlePage_to_loginFragment)
        }
        binding.btnlocateTitlePage.setOnClickListener { v: View ->
            v.findNavController().navigate(R.id.action_titlePage_to_locateCentersFragment)
        }
        binding.btnsignupTitlePage.setOnClickListener { v: View ->
            v.findNavController().navigate(R.id.action_titlePage_to_registrationFragment)
        }
        // Inflate the layout for this fragment
        return binding.root
    }


}
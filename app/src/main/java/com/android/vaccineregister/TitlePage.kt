package com.android.vaccineregister

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.android.vaccineregister.databinding.FragmentTitlePageBinding

class TitlePage : Fragment() {
    private var _binding: FragmentTitlePageBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.

    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTitlePageBinding.inflate(inflater, container, false)

        binding.btnloginTitlePage.setOnClickListener { v: View ->
            v.findNavController().navigate(R.id.action_titlePage_to_loginFragment)
        }
        binding.btnlocateTitlePage.setOnClickListener { v: View ->
            v.findNavController().navigate(R.id.action_titlePage_to_locateCentersFragment)
        }
        binding.btnsignupTitlePage.setOnClickListener { v: View ->
            v.findNavController().navigate(R.id.action_titlePage_to_registrationFragment)
        }

        setHasOptionsMenu(true)

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.before_login, menu)
    }

    // Method is called when an option is selected from options menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}
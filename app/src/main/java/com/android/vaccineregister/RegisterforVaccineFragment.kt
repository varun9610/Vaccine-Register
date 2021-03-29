package com.android.vaccineregister

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import com.android.vaccineregister.databinding.FragmentRegisterforVaccineBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class RegisterforVaccineFragment : Fragment() {


    private var _binding: FragmentRegisterforVaccineBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private var progressBar: ProgressBar? = null
    private lateinit var auth: FirebaseAuth
    private lateinit var selectedRadioButton: RadioButton
    private val binding get() = _binding!!
    val db = Firebase.firestore

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterforVaccineBinding.inflate(inflater, container, false)
        auth = Firebase.auth
        binding.button2.setOnClickListener {
            onClick()
        }
        return binding.root
    }

    fun setProgressBar(bar: ProgressBar) {
        progressBar = bar
    }

    fun showProgressBar() {
        progressBar?.visibility = View.VISIBLE
    }

    fun hideProgressBar() {
        progressBar?.visibility = View.INVISIBLE
    }

    private fun onClick() {
        val currentUser = auth.currentUser!!
        val selectedId: Int = binding.radio.checkedRadioButtonId
        selectedRadioButton = view?.findViewById(selectedId)!!
        val gendertext: String = selectedRadioButton.text.toString()
        val user = hashMapOf(
            "uuid" to auth.uid,
            "dob" to binding.etDate.text.toString(),
            "email" to currentUser.email.toString(),
            "name" to binding.etName.text.toString(),
            "phonenumber" to binding.etmobilenumber.text.toString(),
            "gender" to gendertext,
            "idcardtype" to binding.spinner.selectedItem.toString(),
            "idcardnumber" to binding.etidcard.text.toString(),
            "status" to false
        )
        db.collection("vaccine_user_entry")
            .add(user)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
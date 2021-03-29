package com.android.vaccineregister

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.android.vaccineregister.databinding.FragmentRegistrationBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class RegistrationFragment : Fragment() {

    private var _binding: FragmentRegistrationBinding? = null
    private var progressBar: ProgressBar? = null

    // This property is only valid between onCreateView and
    // onDestroyView.

    private val binding get() = _binding!!

    private lateinit var auth: FirebaseAuth
    val db = Firebase.firestore

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        binding.button.setOnClickListener {
            onClick()
        }
        setProgressBar(binding.progressBar)

        auth = Firebase.auth

        binding.logintv.setOnClickListener {
            it.findNavController().navigate(R.id.action_registrationFragment_to_loginFragment)
        }
        binding.forgotpasswordtv2.setOnClickListener {
            it.findNavController()
                .navigate(R.id.action_registrationFragment_to_forgetPasswordFragment)
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
        createAccount(
            binding.editTextTextEmailAddress3.text.toString(),
            binding.etpassword.text.toString()
        )
    }

    private fun createAccount(email: String, password: String) {
        if (!validateForm()) {
            return
        }
        showProgressBar()
        // [START create_user_with_email]
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Toast.makeText(
                        requireContext(), "Signup Successful",
                        Toast.LENGTH_SHORT
                    ).show()

                    val user = hashMapOf(
                        "uuid" to auth.uid,
                        "email" to binding.editTextTextEmailAddress3.text.toString(),
                        "name" to binding.etFullName.text.toString(),
                        "phonenumber" to binding.editTextTextEmailAddress4.text.toString()
                    )
                    db.collection("users")
                        .add(user)
                    sendEmailVerification()
                    binding.editTextTextEmailAddress3.setText("")
                    binding.etFullName.setText("")
                    binding.editTextTextEmailAddress4.setText("")
                    binding.etpassword.setText("")

                } else {
                    // If sign in fails, display a message to the user.

                    Toast.makeText(
                        requireContext(),
                        "Registration failed. Please try again after some time.",
                        Toast.LENGTH_SHORT
                    ).show()

                }
                hideProgressBar()
            }
        // [END create_user_with_email]
    }


    private fun sendEmailVerification() {


        // Send verification email
        // [START send_email_verification]
        val user = auth.currentUser!!
        user.sendEmailVerification()
            .addOnCompleteListener(requireActivity()) { task ->
                // [START_EXCLUDE]

                if (task.isSuccessful) {
                    Toast.makeText(
                        requireContext(),
                        "Verification email sent to ${user.email} ",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Log.e(TAG, "sendEmailVerification", task.exception)
                    Toast.makeText(
                        requireContext(),
                        "Failed to send verification email.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                // [END_EXCLUDE]
            }
        // [END send_email_verification]
    }

    private fun validateForm(): Boolean {
        val email = binding.editTextTextEmailAddress3.text.toString()
        var valid = true

        if (TextUtils.isEmpty(email)) {
            binding.editTextTextEmailAddress3.error = "Required."
            valid = false
        } else {
            binding.editTextTextEmailAddress3.error = null
        }

        val password = binding.etpassword.text.toString()
        if (TextUtils.isEmpty(password)) {
            binding.etpassword.error = "Required."
            valid = false
        } else {
            binding.etpassword.error = null
        }

        return valid
    }

    companion object {
        private const val TAG = "Main"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
package com.android.vaccineregister

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.android.vaccineregister.databinding.FragmentStatusBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class StatusFragment : Fragment() {


    private var _binding: FragmentStatusBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private lateinit var auth: FirebaseAuth

    private val binding get() = _binding!!
    val db = Firebase.firestore

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStatusBinding.inflate(inflater, container, false)
        auth = Firebase.auth
        val currentUser = auth.currentUser!!

        db.collection("vaccine_user_entry")
            .whereEqualTo("uuid", auth.uid)
            .get()
            .addOnFailureListener { e ->
                Toast.makeText(
                    requireContext(),
                    "Error getting records",
                    Toast.LENGTH_SHORT
                ).show()
                Log.w(ContentValues.TAG, "Error getting document", e)
            }
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    Log.d(ContentValues.TAG, "${document.id} => ${document.data}")
                    binding.textView14.text = currentUser.email
                    binding.textView15.text = document.data["status"].toString()
                    binding.textView18.text = currentUser.email
                    binding.textView16.text = document.data["status"].toString()
//                    binding.textView20.text = currentUser.email
//                    binding.textView19.text = document.data["status"].toString()
                }


            }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
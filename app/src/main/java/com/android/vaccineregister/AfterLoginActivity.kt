package com.android.vaccineregister

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.vaccineregister.databinding.ActivityAfterLoginBinding


class AfterLoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAfterLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAfterLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}
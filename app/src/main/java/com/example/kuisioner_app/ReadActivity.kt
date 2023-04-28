package com.example.kuisioner_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.kuisioner_app.databinding.ActivityReadBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class ReadActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReadBinding
    val db = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val docRef = db.collection("users").document("SF")
        docRef.get()
            .addOnSuccessListener { documentSnapshot ->
                val city = documentSnapshot.toObject<City>()
                binding.tvTeks.text = city?.country.toString()

            }
            .addOnFailureListener { exception ->
                Log.d("READ", "get failed with ", exception)
            }
    }
}
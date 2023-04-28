package com.example.kuisioner_app

import android.annotation.SuppressLint
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
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val ref = db.collection("cities").document("SF")
           ref.get()
               .addOnSuccessListener {
                   document ->
                   val dataCity = document.toObject<City>()
                   if (document != null){
                       binding.tvTeks.text = dataCity?.country.toString()
                       Log.d("PESAN", "DocumentSnapshot data: ${document.data}")
                       Log.d("PESAN2", dataCity?.country.toString())
                   } else {
                       Log.d("PESAN", "No such document")
                   }
               }
               .addOnFailureListener { exception ->
                   Log.d("PESAN", "get failed with ", exception)
               }

    }

}
package com.example.kuisioner_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.kuisioner_app.databinding.ActivityAddQuizBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AddQuizActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddQuizBinding
    val db = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dQuestion = binding.tvQuestion.text.toString()
        val dOptionA = binding.optionA.text.toString()
        val dOptionB = binding.optionB.text.toString()
        val dOptionC = binding.optionC.text.toString()

        val dtQuestion = hashMapOf(
            "dQuestion" to dQuestion,
            "dOptionA" to dOptionA,
            "dOptionB" to dOptionB,
            "dOptionC" to dOptionC
        )

        binding.btnSave.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            db.collection("users").document("question").set(dtQuestion)
                .addOnSuccessListener {
                    Log.d("PESAN", "Data berhasil di tambahkan")
                }
            startActivity(intent)
        }

    }
}
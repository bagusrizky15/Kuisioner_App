package com.example.kuisioner_app

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.kuisioner_app.databinding.ActivityMainBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = Firebase.firestore

        binding.btnAdd.setOnClickListener{
            // Create a new user with a first and last name
            val user = hashMapOf(
                "first" to "Ada",
                "last" to "Lovelace",
                "born" to 1815,
            )

// Add a new document with a generated ID
            db.collection("users")
                .add(user)
                .addOnSuccessListener { documentReference ->
                    Log.d("TAG", "DocumentSnapshot added with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Log.w("TAG", "Error adding document", e)
                }
        }

        binding.btnAddAgain.setOnClickListener{
            // Create a new user with a first, middle, and last name
            val user = hashMapOf(
                "first" to "Alan",
                "middle" to "Mathison",
                "last" to "Turing",
                "born" to 1912,
            )

// Add a new document with a generated ID
            db.collection("users")
                .add(user)
                .addOnSuccessListener { documentReference ->
                    Log.d("TAG", "DocumentSnapshot added with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Log.w("TAG", "Error adding document", e)
                }
        }

        binding.btnReadData.setOnClickListener {
            val intent = Intent(this, ReadActivity::class.java)
            val cities = db.collection("cities")

            val data1 = hashMapOf(
                "name" to "San Francisco",
                "state" to "CA",
                "country" to "USA",
                "capital" to false,
                "population" to 860000,
                "regions" to listOf("west_coast", "norcal"),
            )
            cities.document("SF").set(data1)

            val data2 = hashMapOf(
                "name" to "Los Angeles",
                "state" to "CA",
                "country" to "USA",
                "capital" to false,
                "population" to 3900000,
                "regions" to listOf("west_coast", "socal"),
            )
            cities.document("LA").set(data2)

            startActivity(intent)
        }

        binding.btnQuiz.setOnClickListener {
            startActivity(Intent(this, QuizActivity::class.java))
        }

        binding.btnAddQuiz.setOnClickListener {
            startActivity(Intent(this, AddQuizActivity::class.java))
        }
    }
}
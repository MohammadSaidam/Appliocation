package com.example.callapp

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.callapp.databinding.ActivityAddBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AddActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddBinding

    var db = Firebase.firestore
    var name: String = ""
    var number: String = ""
    var address: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        var binding: ActivityAddBinding = ActivityAddBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)

        setContentView(binding.root)


        binding.saveButton.setOnClickListener {
            name = binding.nameFeild.text.toString()
            number = binding.numberFeild.text.toString()
            address = binding.addressFeild.text.toString()

            this.validationTextFeild()
        }


    }

    fun validationTextFeild() {

        if (this.name.isNotEmpty() && number.isNotEmpty() && address.isNotEmpty()) {
            this.addFireStore()
            Toast.makeText(this, "Addedd Sucessfully", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        } else {
            Toast.makeText(this, "Please Fill Felids", Toast.LENGTH_SHORT).show()
        }


    }


    fun addFireStore() {
        val Users = hashMapOf(
            "Name" to this.name,
            "Number" to this.number,
            "Address" to this.address
        )

// Add a new document with a generated ID
        db.collection("Users")
            .add(Users)
            .addOnSuccessListener { user ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${user.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
    }
}
package com.example.callapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.callapp.Adapter.MyAdapter
import com.example.callapp.Modul.Users
import com.example.callapp.databinding.ActivityAddBinding
import com.example.callapp.databinding.ActivityMainBinding
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var reyeclerView: RecyclerView
    private lateinit var userArrayList: ArrayList<Users>
    private lateinit var myAdapter: MyAdapter
    private lateinit var db: FirebaseFirestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // get Data From fireStore
        readData()

        binding.addButton.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }


    }


    private fun readData() {
        db = FirebaseFirestore.getInstance()
        reyeclerView = binding.recyclerView
        reyeclerView.layoutManager = LinearLayoutManager(this)
        userArrayList = arrayListOf()

        db.collection(Users.FIRESTORE_COLLECTION_NAME).get()
            .addOnSuccessListener {
                if (!it.isEmpty)
                    for (data in it.documents) {
                        val user: Users? = data.toObject<Users>(Users::class.java)
                        userArrayList.add(user!!)

                    }
                reyeclerView.adapter = MyAdapter(userArrayList)


            }

            .addOnFailureListener {
                Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
            }
        reyeclerView.adapter = MyAdapter(userArrayList)

    }


}

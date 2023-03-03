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
import com.example.callapp.Modul.User
import com.example.callapp.databinding.ActivityMainBinding
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var reyeclerView: RecyclerView
    private lateinit var userArrayList: ArrayList<User>
    private lateinit var myAdapter: MyAdapter
    private lateinit var db: FirebaseFirestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        reyeclerView = binding.recyclerView
        reyeclerView.layoutManager = LinearLayoutManager(this)


        userArrayList = arrayListOf()
        db = FirebaseFirestore.getInstance()
        db.collection("Users").get().addOnSuccessListener {
            if (!it.isEmpty) {
                for (data in it.documents) {
                    val user: User? = data.toObject(User::class.java)
                    if (user != null)
                        userArrayList.add(user)
                }

            }
            reyeclerView.adapter = MyAdapter(userArrayList)
        }
            .addOnFailureListener {
                Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
            }


        binding.addButton.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }
    }


//    private fun eventChange() {
//        db = FirebaseFirestore.getInstance()
//        db.collection("Users").addSnapshotListener(object : EventListener<QuerySnapshot> {
//            @SuppressLint("NotifyDataSetChanged")
//            override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
//                if (error != null) {
//                    Log.e("Firestore Error ", error.message.toString())
//                    return
//                }
//                for (dc: DocumentChange in value?.documentChanges!!) {
//                    if (dc.type == DocumentChange.Type.ADDED) {
//                        userArrayList.add(dc.document.toObject(User::class.java))
//                    }
//                }
//                myAdapter.notifyDataSetChanged()
//            }
//
//        })
}

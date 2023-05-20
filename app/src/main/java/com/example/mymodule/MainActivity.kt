package com.example.mymodule

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Website
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mymodule.model.RestaurantData
import com.example.mymodule.view.RestaurantAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var addingBtn: FloatingActionButton
    private lateinit var recv: RecyclerView
    private lateinit var restaurantList: ArrayList<RestaurantData>
    private lateinit var restaurantAdapter: RestaurantAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //set data list
        restaurantList = ArrayList()
        //find ID
        addingBtn = findViewById(R.id.addingBtn)
        recv = findViewById(R.id.restaurantRecycler)
        //set adapter
        restaurantAdapter = RestaurantAdapter(this, restaurantList)
        //set recycler view adapter
        recv.layoutManager = LinearLayoutManager(this)
        recv.adapter = restaurantAdapter
        //set function
        addingBtn.setOnClickListener{addRestaurant()}


    }

    private fun addRestaurant() {
        val inflater = LayoutInflater.from(this)
        val v = inflater.inflate(R.layout.add_restaurant, null)
        //set view

        val restaurantName = v.findViewById<EditText>(R.id.editRestaurantName)
        val restaurantAddress = v.findViewById<EditText>(R.id.editAddress)
        val restaurantRating = v.findViewById<EditText>(R.id.editRating)
        val restaurantLikes = v.findViewById<EditText>(R.id.editLike)
        val restaurantLink = v.findViewById<EditText>(R.id.editLink)

        val addDialog = AlertDialog.Builder(this)

        addDialog.setView(v)
        addDialog.setPositiveButton("Add"){
            dialog, _->

            val name = restaurantName.text.toString()
            val address = restaurantAddress.text.toString()
            val rating = restaurantRating.text.toString()
            val likes = restaurantLikes.text.toString()
            val link = restaurantLink.text.toString()

            restaurantList.add(RestaurantData("$name", "Rating: $rating", "$address", "Total like: $likes", "$link"))
            restaurantAdapter.notifyDataSetChanged()
            Toast.makeText(this, "Added Successful", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }
        addDialog.setNegativeButton("Cancel"){
            dialog, _->
            dialog.dismiss()
            Toast.makeText(this, "Cancel", Toast.LENGTH_SHORT).show()
        }
        addDialog.create()
        addDialog.show()
    }






}





package com.example.mymodule.view

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.mymodule.R
import com.example.mymodule.model.RestaurantData
import org.w3c.dom.Text

class RestaurantAdapter(val c: Context, val restaurantList: ArrayList<RestaurantData>) : RecyclerView.Adapter<RestaurantAdapter.restaurantViewHolder>() {

    inner class restaurantViewHolder(val v: View): RecyclerView.ViewHolder(v){
        var name: TextView
        var address: TextView
        var rating: TextView
        var likes: TextView
        var website: TextView
        var menu: ImageView

        init {
            name = v.findViewById<TextView>(R.id.Name)
            address = v.findViewById<TextView>(R.id.Address)
            rating = v.findViewById<TextView>(R.id.Rating)
            likes = v.findViewById<TextView>(R.id.likes)
            website = v.findViewById<TextView>(R.id.Website)
            menu = v.findViewById(R.id.Settings)

            menu.setOnClickListener { editMenu(it) }
        }

        /*private fun editMenu(v:View) {
            val position = restaurantList[adapterPosition]
            val editMenu = PopupMenu(c, v)
            editMenu.inflate(R.menu.edit_menu)
            editMenu.setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.editInfo->{
                        val v = LayoutInflater.from(c).inflate(R.layout.add_restaurant, null)
                        val name = v.findViewById<EditText>(R.id.editRestaurantName)
                        val address = v.findViewById<EditText>(R.id.editAddress)
                        val rating = v.findViewById<EditText>(R.id.editRating)
                        val website = v.findViewById<EditText>(R.id.editLink)
                        val like = v.findViewById<EditText>(R.id.editLike)

                        AlertDialog.Builder(c).setView(v).setPositiveButton("Update"){
                            dialog, _->
                            position.restaurantName = name.text.toString()
                            position.restaurantAddress = address.text.toString()
                            position.restaurantRating = rating.text.toString()
                            position.restaurantLink = website.text.toString()
                            position.restaurantLikes = like.text.toString()
                            notifyDataSetChanged()
                            Toast.makeText(c,"Info Updated",Toast.LENGTH_SHORT).show()
                            dialog.dismiss()
                        }
                            .setNegativeButton("Cancel"){
                                dialog, _->
                                dialog.dismiss()
                            }
                            .create()
                            .show()
                        true
                    }
                    R.id.delete-> {
                        AlertDialog.Builder(c).setTitle("Delete").setMessage("Are you sure to delete this info?").setPositiveButton("Yes"){
                            dialog, _->
                            restaurantList.removeAt(adapterPosition)
                            notifyDataSetChanged()
                            Toast.makeText(c, "Deleted Successfully", Toast.LENGTH_SHORT).show()
                            dialog.dismiss()
                        }
                            .setNegativeButton("No"){
                                dialog, _->
                                dialog.dismiss()
                            }
                            .create()
                            .show()
                        true
                    }
                    else -> true
                }
            }
            editMenu.show()
            val popup = PopupMenu::class.java.getDeclaredField("Edit")
            popup.isAccessible = true
            val menu = popup.get(editMenu)
            menu.javaClass.getDeclaredMethod("setForceShowIcon", Boolean::class.java).invoke(menu, true)
        }*/

        private fun editMenu(view: View) {
            val position = restaurantList[adapterPosition]
            val v = LayoutInflater.from(c).inflate(R.layout.add_restaurant, null)
            val editName = v.findViewById<EditText>(R.id.editRestaurantName)
            val editAddress = v.findViewById<EditText>(R.id.editAddress)
            val editRating = v.findViewById<EditText>(R.id.editRating)
            val editLink = v.findViewById<EditText>(R.id.editLink)
            val editLike = v.findViewById<EditText>(R.id.editLike)

            // Set initial values
            editName.setText(position.restaurantName)
            editAddress.setText(position.restaurantAddress)
            editRating.setText(position.restaurantRating)
            editLink.setText(position.restaurantLink)
            editLike.setText(position.restaurantLikes)

            val dialogBuilder = AlertDialog.Builder(c)
                .setTitle("Edit Restaurant Info")
                .setView(v)
                .setPositiveButton("Update") { dialog, _ ->
                    // Update the values in the position object
                    position.restaurantName = editName.text.toString()
                    position.restaurantAddress = editAddress.text.toString()
                    position.restaurantRating = editRating.text.toString()
                    position.restaurantLink = editLink.text.toString()
                    position.restaurantLikes = editLike.text.toString()

                    notifyDataSetChanged()
                    Toast.makeText(c, "Info Updated", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                }
                .setNegativeButton("Delete") { dialog, _ ->
                    AlertDialog.Builder(c)
                        .setTitle("Delete")
                        .setMessage("Are you sure you want to delete this restaurant?")
                        .setPositiveButton("Yes") { _, _ ->
                            restaurantList.removeAt(adapterPosition)
                            notifyDataSetChanged()
                            Toast.makeText(c, "Restaurant Deleted", Toast.LENGTH_SHORT).show()
                        }
                        .setNegativeButton("No") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .create()
                        .show()
                }

            val alertDialog = dialogBuilder.create()
            alertDialog.show()
        }


    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RestaurantAdapter.restaurantViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.restaurant_record, parent, false)
        return restaurantViewHolder(v)


    }

    override fun onBindViewHolder(holder: RestaurantAdapter.restaurantViewHolder, position: Int) {
        val newList = restaurantList[position]
        holder.name.text = newList.restaurantName
        holder.address.text = newList.restaurantAddress
        holder.rating.text = newList.restaurantRating
        holder.likes.text = newList.restaurantLikes
        holder.website.text = newList.restaurantLink
    }

    override fun getItemCount(): Int {
        return restaurantList.size
    }
}
package com.example.notepadsqlite.adapter

import android.app.AlertDialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.notepadsqlite.R
import com.example.notepadsqlite.database.DBHelper
import com.example.notepadsqlite.model.Notepad
import javax.security.auth.login.LoginException

class MyRecycleAdapter(var list: List<Notepad>, var onClick: (notepad: Notepad) -> Unit) :
    RecyclerView.Adapter<MyRecycleAdapter.ViewHolder>() {

    private lateinit var dbHelper: DBHelper

    var lastPosition = -1

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(notepad: Notepad) {
            val title = itemView.findViewById<AppCompatTextView>(R.id.item_title)
            val description = itemView.findViewById<AppCompatTextView>(R.id.item_description)
            val update = itemView.findViewById<AppCompatButton>(R.id.item_update)
            val delete = itemView.findViewById<AppCompatButton>(R.id.item_delete)

            itemView.apply {
                title.text = notepad.title
                description.text = notepad.description
            }

            update.setOnClickListener {
                onClick(notepad)
            }

//            dbHelper = DBHelper(itemView.context)
//            delete.setOnClickListener {
//                dbHelper.delete(notepad.id.toString())
////                Log.e("TTT", "list size: ${list.size}")
////                notifyItemRemoved(adapterPosition)
////                onClick(notepad)
//            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_recycle, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(list[position])
        setAdapter(holder.itemView, position)
    }

    override fun getItemCount(): Int = list.size

    fun setAdapter(view: View, position: Int) {
        if (position > lastPosition) {
            val animation = AnimationUtils.loadAnimation(view.context, android.R.anim.slide_in_left)
            view.startAnimation(animation)
            lastPosition = position
        }
    }
}
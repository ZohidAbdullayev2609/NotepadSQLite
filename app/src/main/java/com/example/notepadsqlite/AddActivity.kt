package com.example.notepadsqlite

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.notepadsqlite.database.DBHelper
import com.example.notepadsqlite.databinding.ActivityAddBinding
import com.example.notepadsqlite.model.Notepad

class AddActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddBinding
    private lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = DBHelper(this)
        binding.checkAdd.setOnClickListener {
            val title = binding.titleAdd.text.toString()
            val description = binding.descriptionAdd.text.toString()

            if (title.isEmpty() && description.isEmpty()) {
                Toast.makeText(this, "Ma'lumotlarni to'ldiring", Toast.LENGTH_SHORT).show()
            } else if (title.isEmpty()) {
                Toast.makeText(this, "Sarlavhani kiriting", Toast.LENGTH_SHORT).show()
            } else if (description.isEmpty()) {
                Toast.makeText(this, "Tavsifini kiriting", Toast.LENGTH_SHORT).show()
            } else {
                val notepad = Notepad(title, description)
                dbHelper.insert(notepad)

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()

            }
        }

        binding.titleAdd.setText("")
        binding.descriptionAdd.setText("")

        binding.backAdd.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.clearDescriptionAdd.setOnClickListener {
            binding.descriptionAdd.setText("")
        }

        binding.clearTitleAdd.setOnClickListener {
            binding.titleAdd.setText("")
        }

    }
}
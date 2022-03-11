package com.example.notepadsqlite

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.notepadsqlite.database.DBHelper
import com.example.notepadsqlite.databinding.ActivityUpdateBinding
import com.example.notepadsqlite.model.Notepad

class UpdateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateBinding
    private lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = DBHelper(this)

        val notepadUpdate = intent.getSerializableExtra("position") as Notepad
        binding.titleUpgrade.setText(notepadUpdate.title)
        binding.descriptionUpgrade.setText(notepadUpdate.description)

        binding.delete.setOnClickListener {
            dbHelper.delete(notepadUpdate.id.toString())
            startActivity(Intent(applicationContext, MainActivity::class.java))
        }

        binding.checkUpgrade.setOnClickListener {
            val title = binding.titleUpgrade.text.toString()
            val description = binding.descriptionUpgrade.text.toString()

            if (title.isNotEmpty() && description.isNotEmpty()) {
//                Toast.makeText(this, "Ma'lumotlarni to'ldiring", Toast.LENGTH_SHORT).show()
//            } else if (title.isEmpty()) {
//                Toast.makeText(this, "Sarlavhani kiriting", Toast.LENGTH_SHORT).show()
//            } else if (description.isEmpty()) {
//                Toast.makeText(this, "Tavsifini kiriting", Toast.LENGTH_SHORT).show()
//            } else {
                val notepad = Notepad(title, description)
                dbHelper.update(notepad, notepadUpdate.id.toString())

                startActivity(Intent(this, MainActivity::class.java))
            }
        }

        binding.backUpgrade.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.clearDescriptionUpgrade.setOnClickListener {
            binding.descriptionUpgrade.setText("")
        }

        binding.clearTitleUpgrade.setOnClickListener {
            binding.titleUpgrade.setText("")
        }


    }
}
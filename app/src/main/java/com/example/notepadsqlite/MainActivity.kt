package com.example.notepadsqlite

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.example.notepadsqlite.adapter.MyRecycleAdapter
import com.example.notepadsqlite.database.DBHelper
import com.example.notepadsqlite.databinding.ActivityMainBinding
import com.example.notepadsqlite.model.Notepad
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MyRecycleAdapter
    private lateinit var dbHelper: DBHelper
    private lateinit var list: List<Notepad>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = DBHelper(this)

        binding.cardAdd.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
            finish()
        }

        attachAdapter(dbHelper.readAllUsers())

        binding.search.itemSearchHeaderEditText.doOnTextChanged { text, _, _, _ ->
            val query = text.toString().toLowerCase(Locale.getDefault())
            filterWithQuery(query)
            toggleImageView(query)
        }

    }

    private fun toggleImageView(query: String) {
        if (query.isNotEmpty()) {
            binding.search.itemSearchHeaderClear.visibility = View.VISIBLE
            binding.search.itemSearchHeaderVoice.visibility = View.INVISIBLE
        } else {
            binding.search.itemSearchHeaderClear.visibility = View.INVISIBLE
            binding.search.itemSearchHeaderVoice.visibility = View.VISIBLE
        }
    }

    private fun filterWithQuery(query: String) {
        if (query.isNotEmpty()) {
            val filteretedList: List<Notepad> = onFilterQuery(query)
            attachAdapter(filteretedList)
            toggleRecycleView(filteretedList)
        } else if (query.isEmpty()) {
            attachAdapter(dbHelper)
        }
    }

    private fun attachAdapter(list: DBHelper) {

    }

    private fun toggleRecycleView(filteretedList: List<Notepad>) {
        if (filteretedList.isEmpty()) {
            binding.rv.visibility = View.INVISIBLE
            binding.itemOgilAfsus.visibility = View.VISIBLE
        } else {
            binding.rv.visibility = View.VISIBLE
            binding.itemOgilAfsus.visibility = View.INVISIBLE
        }
    }

    private fun onFilterQuery(query: String): List<Notepad> {
        val filteretedList = ArrayList<Notepad>()
        for (currenName in list) {
            if (currenName.title.toLowerCase(Locale.getDefault()).contains(query)) {
                filteretedList.add(currenName)
            }
        }
        return filteretedList
    }

    private fun attachAdapter(list: List<Notepad>) {
        adapter = MyRecycleAdapter(list) {
            val intent = Intent(applicationContext, UpdateActivity::class.java)
            intent.putExtra("position", it)
            startActivity(intent)
        }

        binding.rv.adapter = adapter
    }

}
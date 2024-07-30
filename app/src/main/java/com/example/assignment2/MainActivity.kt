package com.example.assignment2

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var itemEditText: EditText
    private lateinit var categorySpinner: Spinner
    private lateinit var addButton: Button
    private lateinit var itemListView: ListView
    private val itemList = mutableListOf<String>()
    private lateinit var itemAdapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views using findViewById
        itemEditText = findViewById(R.id.itemEditText)
        categorySpinner = findViewById(R.id.categorySpinner)
        addButton = findViewById(R.id.addButton)
        itemListView = findViewById(R.id.itemListView)

        // Set up Spinner
        val categories = arrayOf("Fruits", "Vegetables", "Dairy", "Bakery")
        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, categories)
        categorySpinner.adapter = spinnerAdapter

        // Set up ListView
        itemAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, itemList)
        itemListView.adapter = itemAdapter

        // Add button click listener
        addButton.setOnClickListener {
            val itemName = itemEditText.text.toString()
            val category = categorySpinner.selectedItem.toString()
            if (itemName.isNotEmpty()) {
                val item = "$category: $itemName"
                itemList.add(item)
                itemAdapter.notifyDataSetChanged()
                itemEditText.text.clear()
            }
        }

        // Item click listener to show fragment
        itemListView.setOnItemClickListener { _, _, position, _ ->
            val selectedItem = itemList[position]
            showItemDetailFragment(selectedItem)
        }
    }

    private fun showItemDetailFragment(item: String) {
        val fragment = ItemDetailFragment.newInstance(item)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_clear -> {
                itemList.clear()
                itemAdapter.notifyDataSetChanged()
                true
            }
            R.id.action_about -> {
                // Show about info
                Toast.makeText(this, "Shopping List App Version 1.0", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}

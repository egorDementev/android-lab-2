package com.example.androidlab2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), ShoppingAdapter.OnDeleteItemClickListener {

    private lateinit var adapter: ShoppingAdapter
    private val shoppingList = mutableListOf<ShoppingItem>()
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = ShoppingAdapter()
        adapter.onDeleteItemClickListener = this
        recyclerView.adapter = adapter
    }

    fun addItem(view: View) {
        val editTextNewItem: EditText = findViewById(R.id.editTextNewItem)
        val newItemName = editTextNewItem.text.toString()

        if (newItemName.isNotEmpty()) {
            val newItem = ShoppingItem(newItemName)
            shoppingList.add(newItem)

            adapter.submitList(shoppingList.toMutableList()) {
                adapter.notifyItemInserted(shoppingList.size - 1)
            }

            editTextNewItem.text.clear()
        }
    }

    override fun onDeleteItemClick(position: Int) {
        if (position in 0 until shoppingList.size) {
            shoppingList.removeAt(position)
            adapter.submitList(shoppingList.toList())
        }
    }
}

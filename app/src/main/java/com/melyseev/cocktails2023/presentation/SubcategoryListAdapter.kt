package com.melyseev.cocktails2023.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.melyseev.cocktails2023.R
import com.melyseev.cocktails2023.presentation.communications.Communications

class SubcategoryListAdapter : RecyclerView.Adapter<SubcategoryListAdapter.SubcategoryViewHolder>(),
    Communications.Change<List<SubcategoryUI>> {

    val subcategories  = mutableListOf<SubcategoryUI>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubcategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.subcategory, parent, false)
        return SubcategoryViewHolder( view )
    }

    override fun onBindViewHolder(holder: SubcategoryViewHolder, position: Int) {
       holder.button.text = subcategories[position].title
    }

    override fun getItemCount(): Int  = subcategories.size

    class SubcategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val button = itemView.findViewById<Button>(R.id.subcategory_btn)
    }

    override fun map(source: List<SubcategoryUI>) {
        subcategories.clear()
        subcategories.addAll(source)
    }
}
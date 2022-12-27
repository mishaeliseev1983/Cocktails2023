package com.melyseev.cocktails2023.presentation.main.list_subcategories.recyclerview

import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.melyseev.cocktails2023.R

class SubcategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val button = itemView.findViewById<Button>(R.id.subcategory_btn)
}
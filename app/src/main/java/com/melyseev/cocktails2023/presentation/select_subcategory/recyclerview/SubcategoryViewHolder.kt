package com.melyseev.cocktails2023.presentation.select_subcategory.recyclerview

import android.view.View
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.melyseev.cocktails2023.R


class SubcategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val checkBox = itemView.findViewById<CheckBox>(R.id.checkBox)
}
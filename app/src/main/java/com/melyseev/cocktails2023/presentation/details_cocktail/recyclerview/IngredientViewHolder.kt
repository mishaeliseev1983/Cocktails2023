package com.melyseev.cocktails2023.presentation.details_cocktail.recyclerview

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.melyseev.cocktails2023.R

class IngredientViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tvIngredient: TextView= itemView.findViewById(R.id.tv_ingredient)
    val tvMeasure: TextView = itemView.findViewById(R.id.tv_measure)
}
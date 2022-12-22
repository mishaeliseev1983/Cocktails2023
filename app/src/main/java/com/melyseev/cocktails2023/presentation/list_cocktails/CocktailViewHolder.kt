package com.melyseev.cocktails2023.presentation.list_cocktails

import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.melyseev.cocktails2023.R



class CocktailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tv_cocktail_title = itemView.findViewById<Button>(R.id.tv_cocktail_title)
}
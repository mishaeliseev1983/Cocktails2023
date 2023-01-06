package com.melyseev.cocktails2023.presentation.main.list_cocktails.recyclerview

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.melyseev.cocktails2023.R


class CocktailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tv_cocktail_title = itemView.findViewById<TextView>(R.id.tv_cocktail_title)
    val img = itemView.findViewById<ImageView>(R.id.imageView)
}
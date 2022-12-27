package com.melyseev.cocktails2023.presentation.main.list_cocktails.recyclerview

import androidx.recyclerview.widget.DiffUtil
import com.melyseev.cocktails2023.presentation.main.list_cocktails.CocktailUI

class CocktailItemDiffCallback : DiffUtil.ItemCallback<CocktailUI>() {
    override fun areItemsTheSame(oldItem: CocktailUI, newItem: CocktailUI) =
        oldItem.title == newItem.title

    override fun areContentsTheSame(oldItem: CocktailUI, newItem: CocktailUI) =
        oldItem == newItem
}
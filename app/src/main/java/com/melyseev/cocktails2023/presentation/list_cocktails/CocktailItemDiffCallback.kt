package com.melyseev.cocktails2023.presentation.list_cocktails

import androidx.recyclerview.widget.DiffUtil
import com.melyseev.cocktails2023.presentation.list_subcategories.SubcategoryUI

class CocktailItemDiffCallback : DiffUtil.ItemCallback<SubcategoryUI>() {
    override fun areItemsTheSame(oldItem: SubcategoryUI, newItem: SubcategoryUI) =
        oldItem.title == newItem.title

    override fun areContentsTheSame(oldItem: SubcategoryUI, newItem: SubcategoryUI) =
        oldItem == newItem
}
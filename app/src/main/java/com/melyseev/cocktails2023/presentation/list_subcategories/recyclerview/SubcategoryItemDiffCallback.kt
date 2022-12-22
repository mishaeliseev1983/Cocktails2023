package com.melyseev.cocktails2023.presentation.list_subcategories.recyclerview

import androidx.recyclerview.widget.DiffUtil
import com.melyseev.cocktails2023.presentation.list_subcategories.SubcategoryUI

class SubcategoryItemDiffCallback : DiffUtil.ItemCallback<SubcategoryUI>() {
    override fun areItemsTheSame(oldItem: SubcategoryUI, newItem: SubcategoryUI) =
        oldItem.title == newItem.title

    override fun areContentsTheSame(oldItem: SubcategoryUI, newItem: SubcategoryUI) =
        oldItem == newItem
}
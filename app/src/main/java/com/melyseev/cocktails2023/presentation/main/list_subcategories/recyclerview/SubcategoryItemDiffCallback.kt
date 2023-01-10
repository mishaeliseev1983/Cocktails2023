package com.melyseev.cocktails2023.presentation.main.list_subcategories.recyclerview

import androidx.recyclerview.widget.DiffUtil
import com.melyseev.cocktails2023.presentation.select_subcategory_ui_objects.SubcategoryUI

class SubcategoryItemDiffCallback : DiffUtil.ItemCallback<SubcategoryUI>() {
    override fun areItemsTheSame(oldItem: SubcategoryUI, newItem: SubcategoryUI) =
        oldItem.title == newItem.title

    override fun areContentsTheSame(oldItem: SubcategoryUI, newItem: SubcategoryUI) =
        oldItem == newItem
}
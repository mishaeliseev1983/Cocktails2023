package com.melyseev.cocktails2023.presentation.recyclerview

import androidx.recyclerview.widget.DiffUtil
import com.melyseev.cocktails2023.presentation.SubcategoryUI

class SubcategoryListDiffCallback(
    private val oldList: List<SubcategoryUI>,
    private val newList: List<SubcategoryUI>,
) : DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].title == newList[newItemPosition].title

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].isSelected == newList[newItemPosition].isSelected
}
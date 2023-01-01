package com.melyseev.cocktails2023.presentation.select_subcategory.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.melyseev.cocktails2023.R
import com.melyseev.cocktails2023.common.Communications
import com.melyseev.cocktails2023.presentation.SubcategoryUI


class SubcategoriesListAdapter() :
    ListAdapter<SubcategoryUI, SubcategoryViewHolder>(SubcategoryItemDiffCallback()),
    Communications.Change<Unit, List<SubcategoryUI>> {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubcategoryViewHolder {
        val layout = R.layout.subcategory_check
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return SubcategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: SubcategoryViewHolder, position: Int) {
        val element = getItem(position)
        holder.checkBox.text = element.title
        holder.checkBox.isChecked = element.isSelected
    }

    override fun change(source: List<SubcategoryUI>) {
        submitList(source)
    }


}
package com.melyseev.cocktails2023.presentation.select_subcategory.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.melyseev.cocktails2023.R
import com.melyseev.cocktails2023.common.Communications
import com.melyseev.cocktails2023.presentation.select_subcategory_ui_objects.SubcategoryUI
import com.melyseev.cocktails2023.presentation.select_subcategory_ui_objects.SubcategoryUIEmpty


class SubcategoriesListAdapter(private val onCheckedSubcategory: (SubcategoryUI, Boolean) -> Unit) :
    ListAdapter<SubcategoryUI, SubcategoryViewHolder>(SubcategoryItemDiffCallback()),
    Communications.Change<Unit, List<SubcategoryUI>> {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubcategoryViewHolder {
        var layout = R.layout.subcategory_check
        if (viewType == EMPTY_ELEMENT)
            layout = R.layout.subcategory_no_check
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return SubcategoryViewHolder(view)
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position) == SubcategoryUIEmpty) return EMPTY_ELEMENT else SUBCATEGORY_ELEMENT
    }

    override fun onBindViewHolder(holder: SubcategoryViewHolder, position: Int) {
        if (getItem(position) == SubcategoryUIEmpty) return
        val element = getItem(position)
        holder.checkBox.text = element.title
        holder.checkBox.isChecked = element.isSelected

        holder.checkBox.setOnClickListener {
            onCheckedSubcategory.invoke(element, holder.checkBox.isChecked)
        }
    }

    override fun change(source: List<SubcategoryUI>) {
        submitList(source)
    }


    companion object {
        const val EMPTY_ELEMENT = -1
        const val SUBCATEGORY_ELEMENT = 0
    }
}
package com.melyseev.cocktails2023.presentation.main.list_subcategories.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.melyseev.cocktails2023.R
import com.melyseev.cocktails2023.common.Communications
import com.melyseev.cocktails2023.presentation.select_subcategory_ui_objects.SubcategoryUI


class SubcategoryListAdapter(private val subcategoryUIClick: SubcategoryUIClick) :
    ListAdapter<SubcategoryUI, SubcategoryViewHolder>(SubcategoryItemDiffCallback()),
    Communications.Change<Unit, List<SubcategoryUI>> {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubcategoryViewHolder {
        val layout= R.layout.subcategory
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return SubcategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: SubcategoryViewHolder, position: Int) {
        val element = getItem(position)
        holder.button.text = element.title
        holder.button.setOnClickListener {
            subcategoryUIClick.click(element)
        }


        if( currentList[position].isSelected)
            holder.button.setBackgroundColor(holder.itemView.context.getColor(R.color.purple_200))
        else
            holder.button.setBackgroundColor(holder.itemView.context.getColor(R.color.purple_500))

    }

    override fun change(source: List<SubcategoryUI>) {
        submitList(source)
    }
}
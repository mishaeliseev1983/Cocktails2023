package com.melyseev.cocktails2023.presentation.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.melyseev.cocktails2023.R
import com.melyseev.cocktails2023.presentation.SubcategoryUI
import com.melyseev.cocktails2023.presentation.communications.Communications


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

/*
class SubcategoryListAdapter(private val subcategoryUIClick: SubcategoryUIClick) :
    RecyclerView.Adapter<SubcategoryViewHolder>(),
    Communications.Change<List<SubcategoryUI>> {

    var subcategoryList = mutableListOf<SubcategoryUI>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubcategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.subcategory, parent, false)
        return SubcategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: SubcategoryViewHolder, position: Int) {
       val element = subcategoryList[position]
       holder.button.text = element.title
        if( element.isSelected)
            holder.button.setBackgroundColor(holder.itemView.context.getColor(R.color.purple_200))
        else
            holder.button.setBackgroundColor(holder.itemView.context.getColor(R.color.purple_500))


        holder.button.setOnClickListener {
            subcategoryUIClick.click(element)
        }
    }

    override fun getItemCount() = subcategoryList.size
    override fun map(value: List<SubcategoryUI>) {

        val callback = SubcategoryListDiffCallback(subcategoryList, value)
        val diffResult = DiffUtil.calculateDiff(callback)
        diffResult.dispatchUpdatesTo(this)

        subcategoryList.clear()
        subcategoryList.addAll(value)

    }

}
*/




/*
class SubcategoryListAdapter : RecyclerView.Adapter<SubcategoryViewHolder>(),
    Communications.Change<List<SubcategoryUI>> {

    val subcategories  = mutableListOf<SubcategoryUI>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubcategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.subcategory, parent, false)
        return SubcategoryViewHolder( view )
    }

    override fun onBindViewHolder(holder: SubcategoryViewHolder, position: Int) {
       holder.button.text = subcategories[position].title
    }

    override fun getItemCount(): Int  = subcategories.size


    override fun map(source: List<SubcategoryUI>) {
        subcategories.clear()
        subcategories.addAll(source)
    }
}
*/

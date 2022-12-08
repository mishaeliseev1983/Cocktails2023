package com.melyseev.cocktails2023.presentation.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.melyseev.cocktails2023.R
import com.melyseev.cocktails2023.presentation.SubcategoryUI
import com.melyseev.cocktails2023.presentation.communications.Communications

class SubcategoryListAdapter :
    ListAdapter<SubcategoryUI, SubcategoryViewHolder>(SubcategoryItemDiffCallback()),
    Communications.Change<List<SubcategoryUI>> {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubcategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.subcategory, parent, false)
        return SubcategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: SubcategoryViewHolder, position: Int) {
        holder.button.text = currentList[position].title
    }

    override fun map(source: List<SubcategoryUI>) {
        submitList(source)
    }


}
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

package com.melyseev.cocktails2023.presentation.list_cocktails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.melyseev.cocktails2023.R
import com.melyseev.cocktails2023.domain.cocktails.CocktailDomain
import com.melyseev.cocktails2023.presentation.communications.Communications
import com.melyseev.cocktails2023.presentation.list_subcategories.SubcategoryUI


class CocktailsListAdapter(){}
/*:
    ListAdapter<SubcategoryUI, CocktailViewHolder>(CocktailItemDiffCallback()),
    Communications.Change<Unit, List<CocktailDomain>> {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CocktailViewHolder {
        val layout = R.layout.subcategory
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return CocktailViewHolder(view)
    }
    }


    override fun onBindViewHolder(holder: CocktailViewHolder, position: Int) {
        val element = getItem(position)
        holder.tv_cocktail_title.text = ""
        holder.button.setOnClickListener {
            //subcategoryUIClick.click(element)
        }

    override fun change(source: List<CocktailDomain>) {
        submitList(source)
    }

}*/
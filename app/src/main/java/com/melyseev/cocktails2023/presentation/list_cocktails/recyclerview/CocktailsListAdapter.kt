package com.melyseev.cocktails2023.presentation.list_cocktails.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.melyseev.cocktails2023.R
import com.melyseev.cocktails2023.presentation.communications.Communications
import com.melyseev.cocktails2023.presentation.list_cocktails.CocktailUI


class CocktailsListAdapter() :
    ListAdapter<CocktailUI, CocktailViewHolder>(CocktailItemDiffCallback()),
    Communications.Change<Unit, List<CocktailUI>> {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CocktailViewHolder {
        val layout = R.layout.cocktail_short
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return CocktailViewHolder(view)
    }

    override fun onBindViewHolder(holder: CocktailViewHolder, position: Int) {
        val element = getItem(position)
        holder.tv_cocktail_title.text = element.title
    }

    override fun change(source: List<CocktailUI>) {
        submitList(source)
    }


}
package com.melyseev.cocktails2023.presentation.main.list_cocktails.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.melyseev.cocktails2023.R
import com.melyseev.cocktails2023.common.Communications
import com.melyseev.cocktails2023.presentation.main.list_cocktails.CocktailUI


class CocktailsListAdapter(val onSelectCocktail: (idCocktail: Int) -> Unit) :
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

        holder.img.setOnClickListener {
            onSelectCocktail.invoke(element.idCocktail)
        }

        Glide.with(holder.itemView.context)
            .load(element.urlImage)
            .centerCrop()
            .into(holder.img)


    }

    override fun change(source: List<CocktailUI>) {
        submitList(source)
    }


}
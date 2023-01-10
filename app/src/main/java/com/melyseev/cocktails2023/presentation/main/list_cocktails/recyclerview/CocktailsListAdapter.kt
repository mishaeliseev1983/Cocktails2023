package com.melyseev.cocktails2023.presentation.main.list_cocktails.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.melyseev.cocktails2023.R
import com.melyseev.cocktails2023.common.Communications
import com.melyseev.cocktails2023.presentation.main.list_cocktails.CocktailUI
import com.melyseev.cocktails2023.presentation.main.list_cocktails.CocktailUIEmpty


class CocktailsListAdapter(private val onSelectCocktail: (idCocktail: Int) -> Unit) :
    ListAdapter<CocktailUI, CocktailViewHolder>(CocktailItemDiffCallback()),
    Communications.Change<Unit, List<CocktailUI>> {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CocktailViewHolder {
        var layout = R.layout.cocktail_short
        if (viewType == CHOOSE_LIKE_ELEMENT)
            layout = R.layout.cocktail_short_choose_like
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return CocktailViewHolder(view)
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position) == CocktailUIEmpty) return CHOOSE_LIKE_ELEMENT else COCKTAIL_ELEMENT
    }

    override fun onBindViewHolder(holder: CocktailViewHolder, position: Int) {
        if (getItem(position) == CocktailUIEmpty) return
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


    companion object {
        const val CHOOSE_LIKE_ELEMENT    = -1
        const val COCKTAIL_ELEMENT      = 0
    }
}
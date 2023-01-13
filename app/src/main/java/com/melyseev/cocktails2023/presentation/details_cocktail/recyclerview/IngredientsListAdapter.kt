package com.melyseev.cocktails2023.presentation.details_cocktail.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.melyseev.cocktails2023.R
import com.melyseev.cocktails2023.common.Communications
import com.melyseev.cocktails2023.presentation.details_cocktail.ui_objects.IngredientCocktailUI

class IngredientsListAdapter :
    ListAdapter<IngredientCocktailUI, IngredientViewHolder>(IngredientItemDiffCallback()),
    Communications.Change<Unit, List<IngredientCocktailUI>> {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder {
        val layout = R.layout.details_measures_ingredients
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return IngredientViewHolder(view)
    }

    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) {
        holder.tvIngredient.text = getItem(position).ingredient
        holder.tvMeasure.text = getItem(position).measure
    }

    override fun change(source: List<IngredientCocktailUI>) {
       submitList(source)
    }
}
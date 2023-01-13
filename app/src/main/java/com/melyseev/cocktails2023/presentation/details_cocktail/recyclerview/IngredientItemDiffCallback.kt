package com.melyseev.cocktails2023.presentation.details_cocktail.recyclerview

import androidx.recyclerview.widget.DiffUtil
import com.melyseev.cocktails2023.presentation.details_cocktail.ui_objects.IngredientCocktailUI


class IngredientItemDiffCallback : DiffUtil.ItemCallback<IngredientCocktailUI>() {
    override fun areItemsTheSame(oldItem: IngredientCocktailUI, newItem: IngredientCocktailUI) =
        oldItem.ingredient == newItem.ingredient

    override fun areContentsTheSame(oldItem: IngredientCocktailUI, newItem: IngredientCocktailUI) =
        oldItem == newItem
}
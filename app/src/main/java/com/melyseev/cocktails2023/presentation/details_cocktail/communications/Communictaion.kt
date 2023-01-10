package com.melyseev.cocktails2023.presentation.details_cocktail.communications

import com.melyseev.cocktails2023.common.Communications
import com.melyseev.cocktails2023.presentation.details_cocktail.CocktailFavoriteStateResultUI
import com.melyseev.cocktails2023.presentation.details_cocktail.DetailsCocktailResultUI
import javax.inject.Inject

interface DetailsCocktailStateCommunication: Communications.Mutable<DetailsCocktailResultUI> {
    class Base @Inject constructor(): DetailsCocktailStateCommunication, Communications.Post<DetailsCocktailResultUI>()
}

interface LikeStateCommunication: Communications.Mutable<CocktailFavoriteStateResultUI> {
    class Base @Inject constructor(): LikeStateCommunication, Communications.Post<CocktailFavoriteStateResultUI>()
}
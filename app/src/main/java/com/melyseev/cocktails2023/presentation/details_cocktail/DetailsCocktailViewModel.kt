package com.melyseev.cocktails2023.presentation.details_cocktail

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.melyseev.cocktails2023.domain.details_cocktail.DetailsCocktailDomain
import com.melyseev.cocktails2023.domain.details_cocktail.ResultCocktailFavoriteState
import com.melyseev.cocktails2023.domain.details_cocktail.ResultDetailsCocktail
import com.melyseev.cocktails2023.domain.main.CocktailsInteractor
import com.melyseev.cocktails2023.presentation.details_cocktail.communications.DetailsCocktailCommunications
import com.melyseev.cocktails2023.presentation.details_cocktail.communications.ObserveDetailsCocktail
import com.melyseev.cocktails2023.presentation.details_cocktail.ui_objects.DetailsCocktailResultUI
import com.melyseev.cocktails2023.presentation.details_cocktail.ui_objects.IngredientCocktailUI
import com.melyseev.cocktails2023.presentation.main.DispatchersList
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailsCocktailViewModel @Inject constructor(
    private val dispatchersList: DispatchersList,
    private val communications: DetailsCocktailCommunications,
    private val interactor: CocktailsInteractor
) : ViewModel(),
    ObserveDetailsCocktail, FetchDetailsCocktail {
    override fun observeProgress(owner: LifecycleOwner, observer: Observer<Int>) {
        communications.observeProgress(owner, observer)
    }

    override fun observeStateDetailsCocktail(
        owner: LifecycleOwner,
        observer: Observer<DetailsCocktailResultUI>
    ) {
        communications.observeStateDetailsCocktail(owner, observer)
    }

    override fun observeStateLikeCocktail(
        owner: LifecycleOwner,
        observer: Observer<CocktailFavoriteStateResultUI>
    ) {
        communications.observeStateLikeCocktail(owner, observer)
    }

    override fun fetchDetailsCocktail(cocktailId: Int) {
        viewModelScope.launch(dispatchersList.io()) {
            communications.showProgress(View.VISIBLE)

            //set like
            val result = interactor.getCocktailFavoriteState(cocktailId = cocktailId)
            val resulUI = result.map(object :
                ResultCocktailFavoriteState.Mapper<CocktailFavoriteStateResultUI> {
                override fun mapToUI(
                    isFavorite: Boolean,
                    message: String
                ): CocktailFavoriteStateResultUI {
                    return if (message.isEmpty()) {
                        CocktailFavoriteStateResultUI.Success(isFavorite = isFavorite)
                    } else {
                        CocktailFavoriteStateResultUI.Error(message = message)
                    }
                }
            })
            communications.showLike(resulUI)

            //set image, title, instructions
            val resultDetailsCocktail = interactor.getDetailsCocktailById(cocktailId)
            val resultDetailsCocktailUI = resultDetailsCocktail.map(object :
                ResultDetailsCocktail.Mapper<DetailsCocktailResultUI> {
                override fun mapToUI(
                    detailsCocktailDomain: DetailsCocktailDomain,
                    message: String
                ): DetailsCocktailResultUI {
                    return if (message.isEmpty()) {

                        val listIngredientCocktailUI = mutableListOf<IngredientCocktailUI>()

                        detailsCocktailDomain.ingredients.forEachIndexed { index, element ->

                            if (detailsCocktailDomain.measures[index].isNotEmpty()
                                && element.isNotEmpty()
                            )
                                listIngredientCocktailUI.add(
                                    IngredientCocktailUI(
                                        element,
                                        detailsCocktailDomain.measures[index]
                                    )
                                )
                        }

                        DetailsCocktailResultUI.Success(
                            title = detailsCocktailDomain.title,
                            image = detailsCocktailDomain.image,
                            instructions = detailsCocktailDomain.instructions,
                            ingredients = listIngredientCocktailUI
                        )
                    } else {
                        DetailsCocktailResultUI.Error(message = message)
                    }
                }
            })
            communications.showDetailsCocktailState(resultDetailsCocktailUI)
            communications.showProgress(View.GONE)
        }
    }


    fun updateCocktailFavoriteState(
        cocktailId: Int,
        cocktailTitle: String,
        cocktailImageUrl: String
    ) {
        viewModelScope.launch(dispatchersList.io()) {
            communications.showProgress(View.VISIBLE)

            val result = interactor.getUpdatedCocktailFavoriteState(
                cocktailId,
                cocktailTitle,
                cocktailImageUrl
            )
            val resulUI = result.map(object :
                ResultCocktailFavoriteState.Mapper<CocktailFavoriteStateResultUI> {
                override fun mapToUI(
                    isFavorite: Boolean,
                    message: String
                ): CocktailFavoriteStateResultUI {
                    return if (message.isEmpty()) {
                        CocktailFavoriteStateResultUI.Success(isFavorite = isFavorite)
                    } else {
                        CocktailFavoriteStateResultUI.Error(message = message)
                    }
                }
            })

            communications.showLike(resulUI)
            communications.showProgress(View.GONE)
        }
    }

}
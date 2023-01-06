package com.melyseev.cocktails2023.presentation.details_cocktail

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.melyseev.cocktails2023.domain.main.CocktailsInteractor
import com.melyseev.cocktails2023.domain.main.details_cocktail.DetailsCocktailDomain
import com.melyseev.cocktails2023.domain.main.details_cocktail.ResultDetailsCocktail
import com.melyseev.cocktails2023.presentation.details_cocktail.communications.DetailsCocktailCommunications
import com.melyseev.cocktails2023.presentation.details_cocktail.communications.ObserveDetailsCocktail
import com.melyseev.cocktails2023.presentation.main.DispatchersList
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailsCocktailViewModel @Inject constructor(
    private val dispatchersList: DispatchersList,
    //private val mapperDomainToUI: DetailsCocktailDomain.Mapper<DetailsCocktailResultUI>,
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

    override fun fetchDetailsCocktail(cocktailId: Int) {
        viewModelScope.launch(dispatchersList.io()) {
            communications.showProgress(View.VISIBLE)
            val resultDetailsCocktail = interactor.getDetailsCocktailById(cocktailId)

            val resultDetailsCocktailUI = resultDetailsCocktail.map(object : ResultDetailsCocktail.Mapper<DetailsCocktailResultUI> {
                override fun mapToUI(
                    detailsCocktailDomain: DetailsCocktailDomain,
                    message: String
                ): DetailsCocktailResultUI {
                    return  if(message.isEmpty()){
                        DetailsCocktailResultUI.Success(detailsCocktailDomain.title, detailsCocktailDomain.image, detailsCocktailDomain.instructions)
                    }else{
                        DetailsCocktailResultUI.Error(message = message)
                    }
                }
            })
            communications.showDetailsCocktailState(resultDetailsCocktailUI)
            communications.showProgress(View.GONE)
        }
    }

}
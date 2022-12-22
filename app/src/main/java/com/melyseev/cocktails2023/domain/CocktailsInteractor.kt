package com.melyseev.cocktails2023.domain

import com.melyseev.cocktails2023.domain.cocktails.ResultCocktail
import com.melyseev.cocktails2023.domain.subcategories.ResultSubcategory
import javax.inject.Inject

interface CocktailsInteractor {
    suspend fun fetchListSubcategory(category: String): ResultSubcategory
    suspend fun fetchListCocktails(category: String, subcategory: String): ResultCocktail


    class Base @Inject constructor(
        private val repository: CocktailsRepository,
        private val handleDomainExceptionToString: HandleDomainExceptionToString
    ) : CocktailsInteractor {

        override suspend fun fetchListSubcategory(category: String): ResultSubcategory {
            return try {
                val list = repository.fetchListSubcategory(category)
                ResultSubcategory.Success(list)
            } catch (e: DomainException) {
                ResultSubcategory.Error(handleDomainExceptionToString.handleError(e))
            }
        }

        override suspend fun fetchListCocktails(category: String, subcategory: String): ResultCocktail {
            return try {
                val list = repository.fetchListCocktails(category, subcategory)
                ResultCocktail.Success(list)
            } catch (e: DomainException) {
                ResultCocktail.Error(handleDomainExceptionToString.handleError(e))
            }
        }
    }

}
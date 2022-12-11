package com.melyseev.cocktails2023.domain

import javax.inject.Inject

interface CocktailsInteractor {
    suspend fun fetchListSubcategory(category: String): ResultSubcategory
    suspend fun fetchListCocktail(subcategory: String): List<SubcategoryDomain>


    class Base @Inject constructor(private val repository: CocktailsRepository, private val handleDomainExceptionToString: HandleDomainExceptionToString): CocktailsInteractor{
        override suspend fun fetchListSubcategory(category: String): ResultSubcategory {
            return try {
                val list = repository.fetchListSubcategory(category)
                ResultSubcategory.Success(list)
            } catch (e: DomainException){
                ResultSubcategory.Error(handleDomainExceptionToString.handleError(e))
            }
        }

        override suspend fun fetchListCocktail(subcategory: String): List<SubcategoryDomain> {
           // val list = repository.fetchListSubcategory(category)
            return emptyList()
        }
    }
}
package com.melyseev.cocktails2023.domain

interface CocktailsInteractor {
    suspend fun fetchListSubcategory(category: String): ResultSubcategory
    //fun fetchListCocktail(subcategory: String): List<SubcategoryDomain>


    class Base(private val repository: CocktailsRepository, private val handleDomainExceptionToString: HandleDomainExceptionToString): CocktailsInteractor{
        override suspend fun fetchListSubcategory(category: String): ResultSubcategory {
            return try {
                val list = repository.fetchListSubcategory(category)
                ResultSubcategory.Success(list)
            } catch (e: DomainException){
                ResultSubcategory.Error(handleDomainExceptionToString.handleError(e))
            }
        }
    }
}
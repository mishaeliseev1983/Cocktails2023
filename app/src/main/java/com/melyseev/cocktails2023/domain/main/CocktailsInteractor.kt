package com.melyseev.cocktails2023.domain.main

import com.melyseev.cocktails2023.domain.DomainException
import com.melyseev.cocktails2023.domain.HandleDomainExceptionToString
import com.melyseev.cocktails2023.domain.SelectCategorySubcategoryRepository
import com.melyseev.cocktails2023.domain.main.details_cocktail.ResultDetailsCocktail
import com.melyseev.cocktails2023.domain.main.short_cocktail.ResultCocktail
import com.melyseev.cocktails2023.domain.main.subcategories.ResultSubcategory
import javax.inject.Inject

interface CocktailsInteractor {
    suspend fun fetchListSubcategory(category: String): ResultSubcategory
    suspend fun fetchListSubcategorySelected(category: String): ResultSubcategory
    suspend fun fetchListCocktails(category: String, subcategory: String): ResultCocktail
    suspend fun updateSelectSubcategory(category: String, subcategory: String, isSelected: Boolean)
    suspend fun getDetailsCocktailById(cocktailId: Int): ResultDetailsCocktail
    fun getCategory():String
    fun getSubcategory():String
    fun changeCategory(category: String)
    fun changeSubcategory(subcategory: String)


    class Base @Inject constructor(
        private val repository: CocktailsRepository,
        private val repositorySelect: SelectCategorySubcategoryRepository,
        private val handleDomainExceptionToString: HandleDomainExceptionToString
    ) : CocktailsInteractor {

        override fun getCategory() = repositorySelect.getCategory()
        override fun getSubcategory() = repositorySelect.getSubcategory()
        override fun changeCategory(category: String) = repositorySelect.changeCategory(category)
        override fun changeSubcategory(subcategory: String) = repositorySelect.changeSubcategory(subcategory)

        override suspend fun fetchListSubcategory(category: String): ResultSubcategory {
            return try {
                val list = repository.fetchListSubcategories(category)
                ResultSubcategory.Success(list)
            } catch (e: DomainException) {
                ResultSubcategory.Error(handleDomainExceptionToString.handleError(e))
            }
        }

        override suspend fun fetchListSubcategorySelected(category: String): ResultSubcategory {
            return try {
                val list = repository.fetchListSubcategories(category)
                ResultSubcategory.Success(list.filter { it.isSelected })
            } catch (e: DomainException) {
                ResultSubcategory.Error(handleDomainExceptionToString.handleError(e))
            }
        }


        override suspend fun fetchListCocktails(
            category: String,
            subcategory: String
        ): ResultCocktail {
            return try {
                val list = repository.fetchListCocktails(category, subcategory)
                ResultCocktail.Success(list)
            } catch (e: DomainException) {
                ResultCocktail.Error(handleDomainExceptionToString.handleError(e))
            }
        }

        override suspend fun updateSelectSubcategory(
            category: String,
            subcategory: String,
            isSelected: Boolean
        ) {
            repository.updateSelectSubcategory(category, subcategory, isSelected)
        }

        override suspend fun getDetailsCocktailById(cocktailId: Int): ResultDetailsCocktail {
            return try {
                val detailsCocktailDomain = repository.getDetailsCocktailById(cocktailId)
                ResultDetailsCocktail.Success(detailsCocktailDomain)
            }
            catch (e: DomainException){
                ResultDetailsCocktail.Error(handleDomainExceptionToString.handleError(e))
            }
        }
    }

}
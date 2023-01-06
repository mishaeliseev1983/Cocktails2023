package com.melyseev.cocktails2023.domain

import com.melyseev.cocktails2023.domain.main.CocktailsInteractor
import com.melyseev.cocktails2023.domain.main.CocktailsRepository
import com.melyseev.cocktails2023.domain.main.short_cocktail.CocktailShortDomain
import com.melyseev.cocktails2023.domain.main.subcategories.ResultSubcategory
import com.melyseev.cocktails2023.domain.main.subcategories.SubcategoryDomain
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class TestCocktailsInteractor {


    companion object {
        const val CATEGORY = "Non alcoholic"
    }

    lateinit var interactor: CocktailsInteractor

    lateinit var repository: TestRepositoryImpl

    lateinit var repositorySelect: SelectCategorySubcategoryRepository

    lateinit var handleDomainExceptionToString: HandleDomainExceptionToString
    @Before
    fun setUp() {

        repositorySelect = TestSelectCategorySubcategoryRepositoryImpl()
        handleDomainExceptionToString = HandleDomainExceptionToString.Base()
        repository = TestRepositoryImpl()
        interactor = CocktailsInteractor.Base(repository, repositorySelect, handleDomainExceptionToString)
    }


    @Test
    fun fetchListSuccess() = runBlocking {

        repository.expectListSubcategoryDomain.clear()
        repository.expectListSubcategoryDomain.add(SubcategoryDomain("1"))
        repository.expectListSubcategoryDomain.add(SubcategoryDomain("2"))


        val result = interactor.fetchListSubcategory(CATEGORY)

        assertEquals(
            ResultSubcategory.Success(
                listOf(
                    SubcategoryDomain("1"),
                    SubcategoryDomain("2")
                )
            ), result
        )
    }

    @Test
    fun fetchListError() = runBlocking {

        repository.expectError = true
        repository.expectDomainError = DomainException.NoInternetException

        val result = interactor.fetchListSubcategory(CATEGORY)

        assertEquals(
            ResultSubcategory.Error(
                "no connection"
            ), result
        )
    }


    class TestSelectCategorySubcategoryRepositoryImpl : SelectCategorySubcategoryRepository {
        override fun changeCategory(category: String) {

        }

        override fun getCategory(): String {
            TODO("Not yet implemented")
        }

        override fun changeSubcategory(category: String) {
            TODO("Not yet implemented")
        }

        override fun getSubcategory(): String {
            TODO("Not yet implemented")
        }

    }

    class TestRepositoryImpl : CocktailsRepository {

        var expectError = false
        var expectListSubcategoryDomain = mutableListOf<SubcategoryDomain>()
        lateinit var expectDomainError: DomainException

        override suspend fun fetchListSubcategories(category: String): List<SubcategoryDomain> {
            if (expectError)
                throw expectDomainError
            return expectListSubcategoryDomain
        }

        override suspend fun fetchListCocktails(
            category: String,
            subcategory: String
        ): List<CocktailShortDomain> {
            TODO("Not yet implemented")
        }

    }

}
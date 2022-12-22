package com.melyseev.cocktails2023.domain

import com.melyseev.cocktails2023.domain.cocktails.CocktailDomain
import com.melyseev.cocktails2023.domain.subcategories.ResultSubcategory
import com.melyseev.cocktails2023.domain.subcategories.SubcategoryDomain
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

    lateinit var handleDomainExceptionToString: HandleDomainExceptionToString
    @Before
    fun setUp() {

        handleDomainExceptionToString = HandleDomainExceptionToString.Base()
        repository = TestRepositoryImpl()
        interactor = CocktailsInteractor.Base(repository, handleDomainExceptionToString)
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


    class TestRepositoryImpl : CocktailsRepository {

        var expectError = false
        var expectListSubcategoryDomain = mutableListOf<SubcategoryDomain>()
        lateinit var expectDomainError: DomainException

        override suspend fun fetchListSubcategory(category: String): List<SubcategoryDomain> {
            if (expectError)
                throw expectDomainError
            return expectListSubcategoryDomain
        }

        override suspend fun fetchListCocktails(
            category: String,
            subcategory: String
        ): List<CocktailDomain> {
            TODO("Not yet implemented")
        }

    }

}
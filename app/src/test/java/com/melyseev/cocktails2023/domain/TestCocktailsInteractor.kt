package com.melyseev.cocktails2023.domain

import com.melyseev.cocktails2023.domain.details_cocktail.DetailsCocktailDomain
import com.melyseev.cocktails2023.domain.details_cocktail.FavoriteStateCocktailDomain
import com.melyseev.cocktails2023.domain.main.CocktailsInteractor
import com.melyseev.cocktails2023.domain.main.CocktailsRepository
import com.melyseev.cocktails2023.domain.main.short_cocktail.CocktailShortDomain
import com.melyseev.cocktails2023.domain.main.short_cocktail.ResultCocktail
import com.melyseev.cocktails2023.domain.main.subcategories.ResultSubcategory
import com.melyseev.cocktails2023.domain.main.subcategories.SubcategoryDomain
import com.melyseev.cocktails2023.presentation.main.ManageResources
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

    lateinit var manageResources: TestManageResources

    @Before
    fun setUp() {

        manageResources = TestManageResources()
        repositorySelect = TestSelectCategorySubcategoryRepositoryImpl()
        repository = TestRepositoryImpl()
        interactor =
            CocktailsInteractor.Base(repository, repositorySelect,  HandleDomainExceptionToString.Base(manageResources))
    }

    @Test
    fun fetchListSubcategorySuccess() = runBlocking {

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
    fun fetchListSubcategoryError() = runBlocking {

        repository.expectError = true
        repository.expectDomainError = DomainException.UnknownErrorException
        manageResources.changeValueExpected("no connection")

        val actual = interactor.fetchListSubcategory(CATEGORY)

        val expected = ResultSubcategory.Error("no connection")
        assertEquals(expected, actual)
    }

    @Test
    fun fetchCocktailListSuccess() = runBlocking {

        repository.expectCocktailListDomain.clear()
        repository.expectCocktailListDomain.add(CocktailShortDomain(1, "1", "image1"))
        repository.expectCocktailListDomain.add(CocktailShortDomain(2, "2", "image2"))
        repository.expectCocktailListDomain.add(CocktailShortDomain(3, "3", "image3"))
        val actual = interactor.fetchListCocktails("Alcoholic", "Alcoholic")
        val expected = ResultCocktail.Success( listOf(
            CocktailShortDomain(1, "1", "image1"),
            CocktailShortDomain(2, "2", "image2"),
            CocktailShortDomain(5, "3", "image3")
        )
        )

        assertEquals(actual, expected)
    }

    @Test
    fun fetchCocktailListError() = runBlocking {
        repository.expectError = true
        repository.expectDomainError = DomainException.NoInternetConnectionException
        manageResources.changeValueExpected("no connection")

        val actual = interactor.fetchListCocktails("1", "2")
        val expected = ResultCocktail.Error("no connection")

        assertEquals(actual, expected)
    }

    class TestSelectCategorySubcategoryRepositoryImpl : SelectCategorySubcategoryRepository {
        override fun changeCategory(category: String) {

        }

        override fun getCategory(): String {
            TODO("Not yet implemented")
        }

        override fun changeSubcategory(subcategory: String) {
            TODO("Not yet implemented")
        }

        override fun getSubcategory(): String {
            TODO("Not yet implemented")
        }

    }

}

class TestManageResources : ManageResources {

    var expectedValue: String = ""
    fun changeValueExpected(value: String) {
        expectedValue = value
    }

    override fun string(id: Int): String {
        return expectedValue
    }
}

class TestRepositoryImpl : CocktailsRepository {

    var expectError = false
    lateinit var expectDomainError: DomainException

    var expectListSubcategoryDomain = mutableListOf<SubcategoryDomain>()
    val expectCocktailListDomain = mutableListOf<CocktailShortDomain>()

    override suspend fun getListSubcategories(category: String): List<SubcategoryDomain> {
        if (expectError)
            throw expectDomainError
        return expectListSubcategoryDomain
    }

    override suspend fun getListCocktails(
        category: String,
        subcategory: String
    ): List<CocktailShortDomain> {
       if(expectError)
           throw expectDomainError
        return expectCocktailListDomain
    }

    override suspend fun updateSelectSubcategory(
        category: String,
        subcategory: String,
        isSelected: Boolean
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun getDetailsCocktailById(cocktailId: Int): DetailsCocktailDomain {
        TODO("Not yet implemented")
    }

    override suspend fun getUpdatedCocktailFavoriteState(
        cocktailId: Int,
        cocktailTitle: String,
        cocktailImage: String
    ): FavoriteStateCocktailDomain {
        TODO("Not yet implemented")
    }

    override suspend fun getCocktailFavoriteState(cocktailId: Int): FavoriteStateCocktailDomain {
        TODO("Not yet implemented")
    }

}


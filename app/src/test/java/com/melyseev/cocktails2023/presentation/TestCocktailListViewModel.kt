package com.melyseev.cocktails2023.presentation

import com.melyseev.cocktails2023.domain.ResultSubcategory
import com.melyseev.cocktails2023.domain.SubcategoryDomain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class TestCocktailListViewModel {

    lateinit var communications: BaseTestCocktailsListViewModel.TestSubcategoryCommunications
    lateinit var interactor: BaseTestCocktailsListViewModel.TestCocktailsInteractor
    lateinit var viewModel: CocktailsListViewModel
    lateinit var dispatchersList: DispatchersList

    @Before
    fun setUp() {
        communications = BaseTestCocktailsListViewModel.TestSubcategoryCommunications()
        interactor = BaseTestCocktailsListViewModel.TestCocktailsInteractor()
        dispatchersList = BaseTestCocktailsListViewModel.TestDisptachersList()

        viewModel = CocktailsListViewModel(dispatchersList, communications, interactor)
    }


    @Test
    fun init_success() {

        //expect
        val listExpected = mutableListOf<SubcategoryDomain>()
        listExpected.add(SubcategoryDomain("12"))
        listExpected.add(SubcategoryDomain("13"))
        listExpected.add(SubcategoryDomain("14"))
        interactor.changeExpectedResultSubcategory(ResultSubcategory.Success(listExpected))

        //action
        viewModel.init()

        //check
        assertEquals(2, communications.listShowProgress.size)
        assertEquals(1, communications.listShowState.size)
        assertEquals(
            ResultUI.Success(listOf( SubcategoryUI("12"),
                    SubcategoryUI("13"),
                    SubcategoryUI("14"))), communications.listShowState[0])
}


    @Test
    fun init_failure() {

        //expect
        val errorMessage= "no connection"
        interactor.changeExpectedResultSubcategory(ResultSubcategory.Error(errorMessage))

        //action
        viewModel.init()

        //check
        assertEquals(2, communications.listShowProgress.size)
        assertEquals(1, communications.listShowState.size)
        assertEquals(
            ResultUI.Failure(errorMessage), communications.listShowState[0])
    }



}

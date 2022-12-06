package com.melyseev.cocktails2023

import com.melyseev.cocktails2023.domain.CocktailsInteractor
import com.melyseev.cocktails2023.domain.SubcategoryDomain
import com.melyseev.cocktails2023.presentation.CocktailsListViewModel
import com.melyseev.cocktails2023.presentation.communications.Communications
import com.melyseev.cocktails2023.presentation.communications.SubcategoryCommunications
import org.junit.Before
import org.junit.Test

class TestCocktailListViewModel {

    lateinit var communications: SubcategoryCommunications
    lateinit var interactor: BaseTestCocktailsListViewModel.TestCocktailsInteractor
    lateinit var viewModel: CocktailsListViewModel

    @Before
    fun setUp() {
        communications = BaseTestCocktailsListViewModel.TestSubcategoryCommunications()
        interactor = BaseTestCocktailsListViewModel.TestCocktailsInteractor()

        viewModel = CocktailsListViewModel(communications, interactor)
    }


    @Test
    fun init(){


        //expect
        val listExpected = mutableListOf<SubcategoryDomain>()
        listExpected.add(SubcategoryDomain("12"))
        listExpected.add(SubcategoryDomain("13"))
        listExpected.add(SubcategoryDomain("14"))
        interactor.changeExpectedResult(listExpected)

        //action
        viewModel.init()

        //check
    }



}


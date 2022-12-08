package com.melyseev.cocktails2023.data

import com.melyseev.cocktails2023.domain.CocktailsRepository
import com.melyseev.cocktails2023.domain.SubcategoryDomain

class CocktailsRepositoryImpl(private val handleErrorToDomainException: HandleErrorToDomainException): CocktailsRepository {
    override suspend fun fetchListSubcategory(category: String): List<SubcategoryDomain> {

        val list = listOf<SubcategoryDomain>(
            SubcategoryDomain("1"),
            SubcategoryDomain("21"),
            SubcategoryDomain("31"),
            SubcategoryDomain("41")
        )
        return  list
        //throw handleErrorToDomainException.handle( UnknownHostException() )
    }
}
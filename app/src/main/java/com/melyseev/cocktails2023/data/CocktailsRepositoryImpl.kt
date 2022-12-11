package com.melyseev.cocktails2023.data

import com.melyseev.cocktails2023.domain.CocktailsRepository
import com.melyseev.cocktails2023.domain.SubcategoryDomain
import javax.inject.Inject

class CocktailsRepositoryImpl @Inject constructor(private val handleErrorToDomainException: HandleErrorToDomainException): CocktailsRepository {
    override suspend fun fetchListSubcategory(category: String): List<SubcategoryDomain> {

        val list = listOf<SubcategoryDomain>(
            SubcategoryDomain("11"),
            SubcategoryDomain("22"),
            SubcategoryDomain("33"),
            SubcategoryDomain("44")
        )
        return  list
        //throw handleErrorToDomainException.handle( UnknownHostException() )
    }
}
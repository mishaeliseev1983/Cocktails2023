package com.melyseev.cocktails2023.data

import com.melyseev.cocktails2023.data.cloud.CloudDataSource
import com.melyseev.cocktails2023.data.common.BEGIN_A
import com.melyseev.cocktails2023.data.common.BEGIN_C
import com.melyseev.cocktails2023.data.common.BEGIN_G
import com.melyseev.cocktails2023.data.common.BEGIN_I
import com.melyseev.cocktails2023.data.list_category.MapDrinkToDomain
import com.melyseev.cocktails2023.data.short_describe_cocktail.Drink
import com.melyseev.cocktails2023.domain.CocktailsRepository
import com.melyseev.cocktails2023.domain.DomainException
import com.melyseev.cocktails2023.domain.cocktails.CocktailDomain
import com.melyseev.cocktails2023.domain.subcategories.SubcategoryDomain
import java.net.UnknownHostException
import javax.inject.Inject

class CocktailsRepositoryImpl @Inject constructor(
    private val cloudDataSource: CloudDataSource,
    private val mapperToSubcategoryDomain: MapDrinkToDomain.Mapper<SubcategoryDomain>,
    private val handleErrorToDomainException: HandleErrorToDomainException
) :
    CocktailsRepository {

    override suspend fun fetchListSubcategory(category: String): List<SubcategoryDomain> {
        try {
            val listSubcategoryDomain = when (category.first().lowercase()) {
                BEGIN_C -> cloudDataSource.getSubcategoriesByCategory().drinks.map {
                    mapperToSubcategoryDomain.map(it.strCategory)
                }
                BEGIN_A -> cloudDataSource.getSubcategoriesByAlcoholic().drinks.map {
                    mapperToSubcategoryDomain.map(it.strAlcoholic)
                }
                BEGIN_I -> cloudDataSource.getSubcategoriesByIngredient().drinks.map {
                    mapperToSubcategoryDomain.map(it.strIngredient1)
                }
                BEGIN_G -> cloudDataSource.getSubcategoriesByGlass().drinks.map {
                    mapperToSubcategoryDomain.map(it.strGlass)
                }
                else -> throw UnknownHostException()
            }
            return listSubcategoryDomain
        }catch (e: Exception){
            throw  handleErrorToDomainException.handle(e)
        }
    }

    override suspend fun fetchListCocktails(category: String, subcategory: String): List<CocktailDomain> {
        try {
            val shortDto = cloudDataSource.getCocktailsBySubcategory(
                category = category,
                subcategory = subcategory
            )
            val resultListCocktailDomain = shortDto.drinks.map {
                it.mapToDomain(object : Drink.Mapper<CocktailDomain> {
                    override fun map(
                        idDrink: String,
                        strDrink: String,
                        strDrinkThumb: String
                    ): CocktailDomain {
                        return CocktailDomain(
                            id = idDrink.toInt(),
                            title = strDrink,
                            urlImage = strDrinkThumb
                        )
                    }
                })
            }
            return resultListCocktailDomain
        }catch (e: Exception){
            throw  handleErrorToDomainException.handle(e)
        }
    }

}
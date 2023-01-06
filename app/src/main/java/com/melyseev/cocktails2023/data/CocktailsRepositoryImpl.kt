package com.melyseev.cocktails2023.data

import com.melyseev.cocktails2023.common.*
import com.melyseev.cocktails2023.data.cache.CacheDataSource
import com.melyseev.cocktails2023.data.cache.entity.SubcategoryEntity
import com.melyseev.cocktails2023.data.cloud.CloudDataSource
import com.melyseev.cocktails2023.data.list_category.MapDrinkToDomain
import com.melyseev.cocktails2023.data.short_cocktail.Drink
import com.melyseev.cocktails2023.domain.main.CocktailsRepository
import com.melyseev.cocktails2023.domain.main.details_cocktail.DetailsCocktailDomain
import com.melyseev.cocktails2023.domain.main.short_cocktail.CocktailShortDomain
import com.melyseev.cocktails2023.domain.main.subcategories.SubcategoryDomain
import java.net.UnknownHostException
import javax.inject.Inject

class CocktailsRepositoryImpl @Inject constructor(
    private val cloudDataSource: CloudDataSource,
    private val cacheDataSource: CacheDataSource,
    private val mapperToSubcategoryDomain: MapDrinkToDomain.Mapper<SubcategoryDomain>,
    private val mapperToSubcategoryData: MapDrinkToDomain.Mapper<SubcategoryData>,
    private val handleErrorToDomainException: HandleErrorToDomainException
) :
    CocktailsRepository {

    override suspend fun fetchListSubcategories(category: String): List<SubcategoryDomain> {
        try {
            //1. create table if not
            cacheDataSource.fillTableCategory()

            //2. get db data
            val idCategory = cacheDataSource.getIdCategory(category)
            var listSubcategoryData =
                cacheDataSource.getListSubcategories(idCategory = idCategory)


            if (listSubcategoryData.isEmpty()) {

                //get internet data
                listSubcategoryData = when (category.first().lowercase()) {
                    BEGIN_C -> cloudDataSource.getSubcategoriesByCategory().drinks.map {
                        mapperToSubcategoryData.map(it.strCategory)
                    }
                    BEGIN_A -> cloudDataSource.getSubcategoriesByAlcoholic().drinks.map {
                        mapperToSubcategoryData.map(it.strAlcoholic)
                    }
                    BEGIN_I -> cloudDataSource.getSubcategoriesByIngredient().drinks.map {
                        mapperToSubcategoryData.map(it.strIngredient1)
                    }
                    BEGIN_G -> cloudDataSource.getSubcategoriesByGlass().drinks.map {
                        mapperToSubcategoryData.map(it.strGlass)
                    }
                    else -> throw UnknownHostException()
                }

                listSubcategoryData = listSubcategoryData.mapIndexed { index,
                                                                       subcategoryData ->
                    SubcategoryData(subcategoryData.name, index < 3)
                }
                //save db
                val listEntities = listSubcategoryData.map { subcategoryData ->
                    SubcategoryEntity(
                        idCategory = idCategory,
                        subcategoryName = subcategoryData.name,
                        subcategoryChecked = subcategoryData.checked.toInt()
                    )
                }
                cacheDataSource.insertListSubcategories(
                    listEntities
                )
            }

            return listSubcategoryData.map {
                SubcategoryDomain(
                    title = it.name,
                    isSelected = it.checked
                )
            }
        } catch (e: Exception) {
            throw  handleErrorToDomainException.handle(e)
        }
    }


    override suspend fun fetchListCocktails(
        category: String,
        subcategory: String
    ): List<CocktailShortDomain> {
        if (subcategory.isEmpty()) return emptyList()

        try {
            val shortDto = cloudDataSource.getCocktailsBySubcategory(
                category = category,
                subcategory = subcategory
            )
            val resultListCocktailDomain = shortDto.drinks.map {
                it.mapToDomain(object : Drink.Mapper<CocktailShortDomain> {
                    override fun map(
                        idDrink: String,
                        strDrink: String,
                        strDrinkThumb: String
                    ): CocktailShortDomain {
                        return CocktailShortDomain(
                            id = idDrink.toInt(),
                            title = strDrink,
                            urlImage = strDrinkThumb
                        )
                    }
                })
            }

            return resultListCocktailDomain
        } catch (e: Exception) {
            throw  handleErrorToDomainException.handle(e)
        }
    }

    override suspend fun updateSelectSubcategory(
        category: String,
        subcategory: String,
        isSelected: Boolean
    ) {
        val idCategory = cacheDataSource.getIdCategory(category)
        cacheDataSource.changeCheckSubcategory(
            idCategory = idCategory,
            subcategory = subcategory,
            isSelected.toInt()
        )
    }

    override suspend fun getDetailsCocktailById(cocktailId: Int): DetailsCocktailDomain {
        try {
            val listData = cloudDataSource.getDetailsCocktailById(cocktailId = cocktailId)
            val drink = listData.drinks[0]
            return DetailsCocktailDomain(
                title = drink.strDrink ?: "",
                image = drink.strDrinkThumb?:"",
                ingredients = emptyList(),
                instructions = drink.strInstructions ?: ""
            )
        }catch (e: Exception) {
            throw  handleErrorToDomainException.handle(e)
        }
    }


}
package com.melyseev.cocktails2023.data

import com.melyseev.cocktails2023.common.*
import com.melyseev.cocktails2023.data.cache.CacheDataSource
import com.melyseev.cocktails2023.data.cache.entity.FavoriteEntity
import com.melyseev.cocktails2023.data.cache.entity.SubcategoryEntity
import com.melyseev.cocktails2023.data.cloud.CloudDataSource
import com.melyseev.cocktails2023.data.details_cocktail.DetailsCocktailDto
import com.melyseev.cocktails2023.data.list_category.MapDrinkToDomain
import com.melyseev.cocktails2023.data.short_cocktail.ShortDto
import com.melyseev.cocktails2023.domain.main.CocktailsRepository
import com.melyseev.cocktails2023.domain.details_cocktail.DetailsCocktailDomain
import com.melyseev.cocktails2023.domain.details_cocktail.FavoriteStateCocktailDomain
import com.melyseev.cocktails2023.domain.main.short_cocktail.CocktailShortDomain
import com.melyseev.cocktails2023.domain.main.subcategories.SubcategoryDomain
import java.net.UnknownHostException
import javax.inject.Inject

class CocktailsRepositoryImpl @Inject constructor(
    private val cloudDataSource: CloudDataSource,
    private val cacheDataSource: CacheDataSource,
    private val mapperDetailsCocktailDtoToDomain: DetailsCocktailDto.Mapper<DetailsCocktailDomain>,
    private val mapperSubcategoryDataToSubcategoryDomain: SubcategoryData.Mapper<SubcategoryDomain>,
    private val mapperListCategoriesDtoToSubcategoryData: MapDrinkToDomain.Mapper<SubcategoryData>,
    private val mapperSubcategoryEntityToSubcategoryData: SubcategoryEntity.Mapper<SubcategoryData>,
    private val mapperFavoriteEntityToCocktailShortDomain: FavoriteEntity.Mapper<CocktailShortDomain>,
    private val mapperShortDtoToListCocktailShortDomain: ShortDto.Mapper<List<CocktailShortDomain>>,
    private val mapperFavoriteEntityToFavoriteStateCocktailDomain: FavoriteEntity.Mapper<FavoriteStateCocktailDomain>,
    private val handleErrorToDomainException: HandleErrorToDomainException
) :
    CocktailsRepository {

    override suspend fun fetchListSubcategories(category: String): List<SubcategoryDomain> {
        if (category == CATEGORY_BY_FAVORITE) return emptyList()
        try {

            //1. create table if not
            cacheDataSource.fillTableCategory()

            //2. get db data
            val idCategory = cacheDataSource.getIdCategory(category)


            var listSubcategoryData =
                cacheDataSource.getListSubcategories(idCategory = idCategory).map {
                    it.mapToData(mapperSubcategoryEntityToSubcategoryData)
                }

            if (listSubcategoryData.isEmpty()) {

                //get internet data
                listSubcategoryData = when (category.first().lowercase()) {
                    BEGIN_C -> cloudDataSource.getSubcategoriesByCategory().drinks.map {
                        mapperListCategoriesDtoToSubcategoryData.map(it.strCategory)
                    }
                    BEGIN_A -> cloudDataSource.getSubcategoriesByAlcoholic().drinks.map {
                        mapperListCategoriesDtoToSubcategoryData.map(it.strAlcoholic)
                    }
                    BEGIN_I -> cloudDataSource.getSubcategoriesByIngredient().drinks.map {
                        mapperListCategoriesDtoToSubcategoryData.map(it.strIngredient1)
                    }
                    BEGIN_G -> cloudDataSource.getSubcategoriesByGlass().drinks.map {
                        mapperListCategoriesDtoToSubcategoryData.map(it.strGlass)
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
                it.mapToDomain(mapperSubcategoryDataToSubcategoryDomain)
            }

        } catch (e: Exception) {
            throw  handleErrorToDomainException.handle(e)
        }
    }


    override suspend fun fetchListCocktails(
        category: String,
        subcategory: String
    ): List<CocktailShortDomain> {


        //favorites - only in db
        if (category == CATEGORY_BY_FAVORITE) {
            val listFavorites = cacheDataSource.getAllCocktailFavorites()
            return listFavorites.map {
                it.mapToDomain(mapperFavoriteEntityToCocktailShortDomain)
            }
        }
        if (subcategory.isEmpty()) return emptyList()
        try {
            val shortDto = cloudDataSource.getCocktailsBySubcategory(
                category = category,
                subcategory = subcategory
            )
            return shortDto.map(mapperShortDtoToListCocktailShortDomain)
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
            checkValue = isSelected.toInt()
        )
    }

    override suspend fun getDetailsCocktailById(cocktailId: Int): DetailsCocktailDomain {
        try {
            val listData = cloudDataSource.getDetailsCocktailById(cocktailId = cocktailId)
            return listData.map(mapperDetailsCocktailDtoToDomain)
        } catch (e: Exception) {
            throw  handleErrorToDomainException.handle(e)
        }
    }

    override suspend fun getUpdatedCocktailFavoriteState(
        cocktailId: Int,
        cocktailTitle: String,
        cocktailImage: String
    ): FavoriteStateCocktailDomain {
        try {

            if (!cacheDataSource.isSavedCocktailFavoriteState(cocktailId = cocktailId)) {
                cacheDataSource.insertCocktailFavoriteState(
                    cocktailId,
                    cocktailTitle,
                    cocktailImage
                )
            }
            val favoriteEntity = cacheDataSource.updateAndGetCocktailFavoriteState(cocktailId)
            return favoriteEntity.mapToDomain(mapperFavoriteEntityToFavoriteStateCocktailDomain)
        } catch (e: Exception) {
            throw  handleErrorToDomainException.handle(e)
        }
    }

    override suspend fun getCocktailFavoriteState(cocktailId: Int): FavoriteStateCocktailDomain {
        try {
            val favoriteEntity = cacheDataSource.getCocktailFavoriteState(cocktailId)
            return favoriteEntity.mapToDomain(mapperFavoriteEntityToFavoriteStateCocktailDomain)
        } catch (e: Exception) {
            throw  handleErrorToDomainException.handle(e)
        }
    }


}
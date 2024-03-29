package com.melyseev.cocktails2023.domain.details_cocktail

data class DetailsCocktailDomain(
    val title: String,
    val image: String,
    val instructions: String,
    val ingredients: List<String>,
    val measures: List<String>,

) {
    fun <T> mapToUI(mapper: Mapper<T>) = mapper.map(title, image, instructions)

    interface Mapper<T> {
        fun map(title: String, image: String, instructions: String): T
    }

    companion object{
        fun Empty(): DetailsCocktailDomain {
            return DetailsCocktailDomain("","", "", emptyList(), emptyList())
        }
    }
}
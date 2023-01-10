package com.melyseev.cocktails2023.presentation.select_category

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.melyseev.cocktails2023.domain.SelectCategorySubcategoryRepository
import com.melyseev.cocktails2023.presentation.select_category.communications.ObserveSelectedCategory
import com.melyseev.cocktails2023.presentation.select_category.communications.SelectCategoryCommunications
import kotlinx.coroutines.launch
import javax.inject.Inject

class SelectCategoryViewModel @Inject constructor(

    private val communications: SelectCategoryCommunications,
    private val repositorySelect: SelectCategorySubcategoryRepository
) :
    ViewModel(), ObserveSelectedCategory {

    override fun observeSelectedCategory(owner: LifecycleOwner, observer: Observer<String>) {
        communications.observeSelectedCategory(owner, observer)
    }

    fun fetchSelectedCategory(newCategory: String = "") {
        if (newCategory.isNotEmpty()) {
            communications.showCheckedCategory(newCategory)
            return
        }
        viewModelScope.launch {
            val category = repositorySelect.getCategory()
            communications.showCheckedCategory(checked = category)
        }
    }

    fun changeCategory(newCategory: String) {
        if (repositorySelect.getCategory() == newCategory) return
        repositorySelect.changeCategory(category = newCategory)
        repositorySelect.changeSubcategory("")
    }
}
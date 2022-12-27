package com.melyseev.cocktails2023.presentation.select_category.communications

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.melyseev.cocktails2023.common.Communications
import javax.inject.Inject

interface SelectCategoryCommunications : ObserveSelectedCategory{
    fun showCheckedCategory(checked: String)

    class Base @Inject constructor(private val selectedCategory: Communications.SelectedCategoryCommunication): SelectCategoryCommunications{
        override fun showCheckedCategory(checked: String) {
                selectedCategory.change(checked)
        }

        override fun observeSelectedCategory(owner: LifecycleOwner, observer: Observer<String>) {
                selectedCategory.observe(owner, observer)
        }
    }
}
package com.melyseev.cocktails2023.common

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.melyseev.cocktails2023.presentation.select_subcategory_ui_objects.SubcategoryResultUI
import com.melyseev.cocktails2023.presentation.main.list_cocktails.CocktailResultUI
import javax.inject.Inject

interface Communications {

    interface Mapper<R, S> {
        fun map(source: S): R
    }

    // interface Change<S> : Mapper<Unit, S>
    // interface Mutable<T> : Observe<T>, Change<T>

    interface Change<Unit, S> {
        fun change(source: S): Unit
    }

    interface Observe<T> {
        fun observe(owner: LifecycleOwner, observer: Observer<T>)
    }


    interface Mutable<T> : Observe<T>, Change<Unit, T>

    abstract class Abstract<T>(val liveData: MutableLiveData<T>) : Mutable<T> {
        override fun observe(owner: LifecycleOwner, observer: Observer<T>) {
            liveData.observe(owner, observer)
        }
    }

    abstract class Post<T>(liveData: MutableLiveData<T> = MutableLiveData()) :
        Abstract<T>(liveData) {
        override fun change(source: T) {
            source.let {
                liveData.postValue(it)
            }
        }
    }
    abstract class SinglePost<T> : Post<T>(SingleLiveEvent())

    interface ProgressCommunication : Mutable<Int> {
        class Base @Inject constructor() : ProgressCommunication, Post<Int>()
    }

    interface SubcategoryListStateCommunication : Mutable<SubcategoryResultUI> {
        class Base @Inject constructor() : SubcategoryListStateCommunication,
            Post<SubcategoryResultUI>()
    }

    interface CocktailListStateCommunication : Mutable<CocktailResultUI> {
        class Base @Inject constructor() : CocktailListStateCommunication,
            Post<CocktailResultUI>()
    }

    interface CategoryNameStateCommunication : Mutable<String> {
        class Base @Inject constructor() : CategoryNameStateCommunication,
            Post<String>()
    }

    interface SelectedCategoryCommunication : Mutable<String> {
        class Base @Inject constructor() : SelectedCategoryCommunication, Post<String>()
    }

}
package com.melyseev.cocktails2023.presentation.communications

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.melyseev.cocktails2023.presentation.SubcategoryUI
import com.melyseev.cocktails2023.presentation.ResultUI

interface Communications {

    interface Mapper<R, S> {
        fun map(source: S): R
    }

    interface Change<S> : Mapper<Unit, S>

    interface Observe<T> {
        fun observe(owner: LifecycleOwner, observer: Observer<T>)
    }

    interface Mutable<T> : Observe<T>, Change<T>

    abstract class Abstract<T>(val liveData: MutableLiveData<T>) : Mutable<T> {
        override fun observe(owner: LifecycleOwner, observer: Observer<T>) {
            liveData.observe(owner, observer)
        }
    }

    abstract class UI<T>(liveData: MutableLiveData<T> = MutableLiveData()) : Abstract<T>(liveData) {
        override fun map(source: T) {
            liveData.value = source
        }
    }

    abstract class Post<T>(liveData: MutableLiveData<T> = MutableLiveData()) : Abstract<T>(liveData) {
        override fun map(source: T) {
            liveData.postValue(source)
        }
    }


    interface ProgressCommunication : Mutable<Int> {
        class Base : ProgressCommunication, Post<Int>()
    }

    interface SubcategoryStateCommunication : Mutable<ResultUI> {
        class Base : SubcategoryStateCommunication,
            Post<ResultUI>()
    }

    interface SubcategoryListCommunication : Mutable<List<SubcategoryUI>> {
        class Base : SubcategoryListCommunication,
            Post<List<SubcategoryUI>>()
    }

}
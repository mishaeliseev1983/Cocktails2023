package com.melyseev.cocktails2023.presentation.communications

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.melyseev.cocktails2023.presentation.SubcategoryUI
import com.melyseev.cocktails2023.presentation.ResultUI
import javax.inject.Inject

interface Communications {

    interface Mapper<R, S> {
        fun map(source: S): R
    }

   // interface Change<S> : Mapper<Unit, S>

    interface Change<Unit,S> {
        fun change(source: S) : Unit
    }

    interface Observe<T> {
        fun observe(owner: LifecycleOwner, observer: Observer<T>)
    }

   // interface Mutable<T> : Observe<T>, Change<T>

    interface Mutable<T> : Observe<T>, Change<Unit, T>

    abstract class Abstract<T>(val liveData: MutableLiveData<T>) : Mutable<T> {
        override fun observe(owner: LifecycleOwner, observer: Observer<T>) {
            liveData.observe(owner, observer)
        }
    }
/*
    abstract class UI<T>(liveData: MutableLiveData<T> = MutableLiveData()) : Abstract<T>(liveData) {
        override fun map(source: T) {
            liveData.value = source
        }
    }
*/
    abstract class Post<T>(liveData: MutableLiveData<T> = MutableLiveData()) : Abstract<T>(liveData) {
        override fun change(source: T) {
            liveData.postValue(source)
        }
    }


    interface ProgressCommunication : Mutable<Int> {
        class Base @Inject constructor(): ProgressCommunication, Post<Int>()
    }

    interface SubcategoryStateCommunication : Mutable<ResultUI> {
        class Base  @Inject constructor():  SubcategoryStateCommunication,
            Post<ResultUI>()
    }

}
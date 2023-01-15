package com.melyseev.cocktails2023.domain

import com.melyseev.cocktails2023.R
import com.melyseev.cocktails2023.presentation.main.ManageResources
import javax.inject.Inject

interface HandleDomainExceptionToString {
    fun handleError(exception: Exception): String

    class Base @Inject constructor(private val manageResources: ManageResources): HandleDomainExceptionToString{
        override fun handleError(exception: Exception): String {
           return when(exception){
                DomainException.NoInternetConnectionException -> manageResources.string(R.string.no_connection)
                else -> manageResources.string(R.string.unknown_error)
            }
        }
    }

}
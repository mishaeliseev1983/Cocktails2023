package com.melyseev.cocktails2023.domain

import javax.inject.Inject

interface HandleDomainExceptionToString {

    fun handleError(exception: Exception): String

    class Base @Inject constructor(): HandleDomainExceptionToString{
        override fun handleError(exception: Exception): String {
           return when(exception){
                DomainException.NoInternetException -> "no connection"
                else -> "unknown error"
            }
        }
    }

}
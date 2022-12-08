package com.melyseev.cocktails2023.domain

interface HandleDomainExceptionToString {

    fun handleError(exception: Exception): String

    class Base: HandleDomainExceptionToString{
        override fun handleError(exception: Exception): String {
           return when(exception){
                DomainException.NoInternetException -> "no connection"
                else -> "unknown error"
            }
        }
    }

}
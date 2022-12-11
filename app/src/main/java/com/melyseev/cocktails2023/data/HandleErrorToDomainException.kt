package com.melyseev.cocktails2023.data

import com.melyseev.cocktails2023.domain.DomainException
import java.net.UnknownHostException
import javax.inject.Inject

interface HandleErrorToDomainException {
    fun handle(exception: Exception) : DomainException
    class Base @Inject constructor(): HandleErrorToDomainException {
        override fun handle(exception: Exception): DomainException {
            return when (exception) {
                is UnknownHostException -> DomainException.NoInternetException
                else -> DomainException.UnknownErrorException
            }
        }
    }
}
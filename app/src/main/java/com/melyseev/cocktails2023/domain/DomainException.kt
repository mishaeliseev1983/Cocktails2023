package com.melyseev.cocktails2023.domain

sealed class DomainException: IllegalStateException() {
    object NoInternetConnectionException : DomainException()
    object UnknownErrorException : DomainException()
}
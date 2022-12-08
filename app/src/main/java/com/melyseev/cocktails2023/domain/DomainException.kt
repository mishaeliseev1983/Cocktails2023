package com.melyseev.cocktails2023.domain

sealed class DomainException: IllegalStateException() {
    object NoInternetException : DomainException()
    object UnknownErrorException : DomainException()
}
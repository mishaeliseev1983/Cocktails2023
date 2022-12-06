package com.melyseev.cocktails2023.presentation

sealed class ResultUI{
    class Success(): ResultUI()
    data class Failure(val message: String): ResultUI()
}
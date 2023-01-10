package com.melyseev.cocktails2023.presentation

import androidx.fragment.app.Fragment

interface ShowFragment {
    fun show(fragment: Fragment, add: Boolean)
}
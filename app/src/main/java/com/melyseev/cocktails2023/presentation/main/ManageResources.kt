package com.melyseev.cocktails2023.presentation.main

import android.content.Context
import javax.inject.Inject

interface ManageResources {
    fun string(id: Int): String
    class Base @Inject constructor(val context: Context): ManageResources{
        override fun string(id: Int): String {
                return context.getString(id)
        }
    }
}
package com.example.navigation


import com.example.model.New
import kotlinx.serialization.Serializable
import kotlin.reflect.typeOf

sealed interface NewsScreen {
    @Serializable
    data object Home : NewsScreen

    @Serializable
    data class Details(val new: New) : NewsScreen {
        companion object {
            val typeMap = mapOf(typeOf<New>() to NewType)
        }
    }
}

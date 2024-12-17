package com.example.viewmodel

fun interface ViewModelIntentSender<I> {

    fun sendIntent(intent: I)
}

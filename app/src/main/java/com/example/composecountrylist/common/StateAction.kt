package com.example.composecountrylist.common

sealed interface StateAction {
    class SUCCESS<T>(val response: T, val message: String) : StateAction
    class ERROR(val error: Throwable) : StateAction
    object LOADING : StateAction
}
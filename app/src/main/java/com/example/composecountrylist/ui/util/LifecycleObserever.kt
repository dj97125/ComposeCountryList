package com.example.composecountrylist.ui.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

@Composable
fun LifecycleObserver(
    lifecycleOwneer: LifecycleOwner = LocalLifecycleOwner.current,
    onCreate:() -> Unit = { },
    onResume:() -> Unit = { }
) {
    DisposableEffect(lifecycleOwneer) {
        val observer = LifecycleEventObserver { source, event ->
            when(event){
                Lifecycle.Event.ON_CREATE -> onCreate
                Lifecycle.Event.ON_RESUME -> onResume
                else -> {}
            }
        }
        lifecycleOwneer.lifecycle.addObserver(observer)

        return@DisposableEffect onDispose {
            lifecycleOwneer.lifecycle.removeObserver(observer)
        }
    }
}
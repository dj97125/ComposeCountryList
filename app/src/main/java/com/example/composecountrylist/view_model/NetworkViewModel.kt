package com.example.composecountrylist.view_model

import android.os.Trace
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.composecountrylist.common.StateAction
import com.example.composecountrylist.domain.UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import javax.inject.Inject

@HiltViewModel
class NetworkViewModel @Inject constructor(
    private val useCase: UseCase,
    private val coroutineScope: CoroutineScope,
    private val handler: CoroutineExceptionHandler
) : ViewModel() {


    private val _countryResponse: MutableStateFlow<StateAction> =
        MutableStateFlow(StateAction.LOADING)
    val countryResponse: StateFlow<StateAction>
        get() = _countryResponse.asStateFlow()

    private val _isRefreshing: MutableStateFlow<Boolean> =
        MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean>
        get() = _isRefreshing.asStateFlow()


    init {
        getCountryList()
    }


    fun getCountryList(isRefreshing: Boolean = false) {

        if (isRefreshing) _isRefreshing.value = true

        Trace.beginSection("Ticket123")
        coroutineScope.launch(handler) {
            supervisorScope {
                launch() {
                    delay(5000)/// this is just for showing the shimmer efect
                    useCase().collect() { stateAction ->
                        _countryResponse.value = stateAction
                        _isRefreshing.value = false

                        // DefaultDispatcher-worker-2
                        Log.i("NetWoorkVieewModel", "WorkerName = ${Thread.currentThread().name}")
                    }
                }

            }

        }
        Trace.endSection()
    }
}









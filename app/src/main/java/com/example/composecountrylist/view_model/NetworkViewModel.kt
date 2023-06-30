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


    private val _countryResponse: MutableStateFlow<StateAction?> = MutableStateFlow(StateAction.LOADING)

    val countryResponse: StateFlow<StateAction?>
        get() = _countryResponse.asStateFlow()

    var countryResponseeCompose by mutableStateOf(null)
    private set


    init {
        getCountryList()
    }


    fun getCountryList() {
        Trace.beginSection("Ticket123")
        coroutineScope.launch(handler) {
            supervisorScope {
                launch() {
                    useCase().collect() { stateAction ->
                        _countryResponse.value = stateAction

                        // DefaultDispatcher-worker-2
                        Log.i("NetWoorkVieewModel", "WorkerName = ${Thread.currentThread().name}")
                    }
                }

            }

        }
        Trace.endSection()
    }
}









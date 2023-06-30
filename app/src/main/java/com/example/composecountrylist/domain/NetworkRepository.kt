package com.example.composecountrylist.domain


import com.example.composecountrylist.common.FailedCache
import com.example.composecountrylist.common.InternetCheck
import com.example.composecountrylist.common.StateAction
import com.example.composecountrylist.model.local.LocalDataSource
import com.example.composecountrylist.model.network.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface NetworkRepository {
    fun countryList(): Flow<StateAction>
}

class NetworkRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : NetworkRepository {


    override fun countryList(): Flow<StateAction> = flow {
        val connected = InternetCheck()
        val remoteService = remoteDataSource.countryCached()
        if (connected.isConnected()) {
            remoteService.collect() { stateAction ->
                when (stateAction) {
                    is StateAction.SUCCESS<*> -> {
                        val retrievedMessage = stateAction.message
                        val retrievedCountries = stateAction.response as List<Country>
                        emit(StateAction.SUCCESS(retrievedCountries, retrievedMessage))
                        localDataSource.insertLocalCountry(retrievedCountries).collect()

                    }
                    is StateAction.ERROR -> {
                        val retrievedError = stateAction.error
                        emit(StateAction.ERROR(retrievedError))
                    }

                    else -> {}
                }
            }
        } else {
            val cache = localDataSource.getAllCachedCountries()
            cache.collect() { stateAction ->
                when (stateAction) {
                    is StateAction.SUCCESS<*> -> {
                        val retrievedMessage = stateAction.message
                        val retrievedCountries = stateAction.response as List<Country>
                        emit(StateAction.SUCCESS(retrievedCountries,retrievedMessage))
                    }
                    is StateAction.ERROR -> {
                        emit(StateAction.ERROR(FailedCache()))
                    }

                    else -> {}
                }

            }


        }
    }
}



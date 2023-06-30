package com.example.composecountrylist.model.network

import com.example.composecountrylist.common.FailedResponseException
import com.example.composecountrylist.common.NullResponseException
import com.example.composecountrylist.common.StateAction
import com.example.composecountrylist.domain.Country
import com.example.composecountrylist.model.network.country_response.CountriesResponseItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface RemoteDataSource {
    fun countryCached(): Flow<StateAction>
}

class ServiceDataSource @Inject constructor(
    private val service: NetworkAPI
) : RemoteDataSource {

    override fun countryCached(): Flow<StateAction> = flow {
        val service = service.getCountriesList()
        if (service.isSuccessful) {
            service.body()?.let { result ->
                emit(StateAction.SUCCESS(result.map { it.toDomainModel() },"Data From Network..."))
            } ?: throw NullResponseException()
        }else{
            emit(StateAction.ERROR(FailedResponseException()))
        }
    }
}


private fun List<CountriesResponseItem>.toDomainModel(): List<Country> = map {
    it.toDomainModel()
}

private fun CountriesResponseItem.toDomainModel(): Country =
    Country(capital, code, name, region)

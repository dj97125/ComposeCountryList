package com.example.composecountrylist.model.network

import com.example.composecountrylist.common.END_POINT_COUNTRIES_KTOR
import com.example.composecountrylist.common.StateAction
import com.example.composecountrylist.domain.Country
import com.example.composecountrylist.model.network.country_response.CountriesResponseItem
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.decodeFromString
import javax.inject.Inject

interface RemoteDataSource {
    fun countryCached(): Flow<StateAction>
}

class ServiceDataSource @Inject constructor(
    private val service: HttpClient
) : RemoteDataSource {

    override fun countryCached(): Flow<StateAction> = flow {
        try {
            val response: List<CountriesResponseItem> = service.get {
                url(END_POINT_COUNTRIES_KTOR)
            }
            emit(StateAction.SUCCESS(response.toDomainModel(), "Data From Network"))
        } catch (e:NoTransformationFoundException){
            val responseString: String = service.get(END_POINT_COUNTRIES_KTOR)

            val json = kotlinx.serialization.json.Json {
                ignoreUnknownKeys = true
            }
            val response = json.decodeFromString<List<CountriesResponseItem>>(responseString)
            emit(StateAction.SUCCESS(response.toDomainModel(), "Data From Network"))
        }



    }
}


private fun List<CountriesResponseItem>.toDomainModel(): List<Country> = map {
    it.toDomainModel()
}

private fun CountriesResponseItem.toDomainModel(): Country =
    Country(capital, code, name, region)

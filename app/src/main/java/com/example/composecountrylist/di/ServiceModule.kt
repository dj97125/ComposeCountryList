package com.example.composecountrylist.di


import android.content.Context
import android.util.Log
import androidx.room.Room
import com.example.composecountrylist.common.BASE_URL
import com.example.composecountrylist.common.DATABASE_NAME
import com.example.composecountrylist.domain.NetworkRepository
import com.example.composecountrylist.domain.NetworkRepositoryImpl
import com.example.composecountrylist.model.local.CountryDataBase
import com.example.composecountrylist.model.local.LocalDataSource
import com.example.composecountrylist.model.local.RoomDataSource
import com.example.composecountrylist.model.network.RemoteDataSource
import com.example.composecountrylist.model.network.ServiceDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers


@Module
@InstallIn(ViewModelComponent::class)
interface ServiceModule {

    @ViewModelScoped
    @Binds
    fun bindLocalDataSource(
        roomDataSource: RoomDataSource
    ): LocalDataSource

    @ViewModelScoped
    @Binds
    fun bindRemoteDataSource(
        serviceDataSource: ServiceDataSource
    ): RemoteDataSource

    @ViewModelScoped
    @Binds
    fun bindRepository(
        networkRepositoryImpl: NetworkRepositoryImpl
    ): NetworkRepository

    companion object {

        @Provides
        fun provideHttpClient(): HttpClient {
            return HttpClient(Android) {
                val time = 3000L
                install(Logging) {
                    level = LogLevel.ALL
                }
                install(JsonFeature) {

                    serializer = KotlinxSerializer()
                }
                install(HttpTimeout) {
                    requestTimeoutMillis = time
                    connectTimeoutMillis = time
                    socketTimeoutMillis = time
                }
                defaultRequest {
                    if (method != HttpMethod.Get) contentType(ContentType.Application.Json)
                    accept(ContentType.Application.Json)
                }
            }
        }

        @Provides
        fun provideExceptionHandler(): CoroutineExceptionHandler =
            CoroutineExceptionHandler { context, throwable ->
                Log.d(
                    "ViewModel",
                    "provideExceptionHandler: $throwable"
                )
            }

        @Provides
        @ApiUrl
        fun provideUrl(): String = BASE_URL


        @Provides
        fun provideCoroutineDispatcher(): CoroutineDispatcher = Dispatchers.IO

        @Provides
        fun provideCoroutineScope(dispatcher: CoroutineDispatcher): CoroutineScope =
            CoroutineScope(dispatcher)

        @Provides
        @ProductionDB
        fun provideRoom(@ApplicationContext context: Context): CountryDataBase =
            Room.databaseBuilder(
                context,
                CountryDataBase::class.java, DATABASE_NAME
            ).fallbackToDestructiveMigration().build()


        @Provides
        fun provideCountryDao(@ProductionDB dataBase: CountryDataBase) = dataBase.countryDao()

    }
}


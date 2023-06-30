package com.example.composecountrylist.di

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.example.composecountrylist.common.BASE_URL
import com.example.composecountrylist.common.DATABASE_NAME
import com.example.composecountrylist.model.local.CountryDataBase
import com.example.composecountrylist.model.local.LocalDataSource
import com.example.composecountrylist.model.local.RoomDataSource
import com.example.composecountrylist.domain.NetworkRepository
import com.example.composecountrylist.domain.NetworkRepositoryImpl
import com.example.composecountrylist.model.network.NetworkAPI
import com.example.composecountrylist.model.network.RemoteDataSource
import com.example.composecountrylist.model.network.ServiceDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


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
        fun provideService(@ApiUrl apiUrl: String): NetworkAPI =
            Retrofit.Builder()
                .baseUrl(apiUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(provideOkHttpClient())
                .build()
                .create(NetworkAPI::class.java)


        @Provides
        fun provideOkHttpClient(): OkHttpClient =
            OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build()

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


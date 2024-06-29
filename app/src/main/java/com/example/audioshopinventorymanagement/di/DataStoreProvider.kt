package com.example.audioshopinventorymanagement.di


import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.dataStoreFile
import com.example.audioshopinventorymanagement.BuildConfig
import com.example.audioshopinventorymanagement.jwttokensdatastore.JwtTokenRepository
import com.example.audioshopinventorymanagement.jwttokensdatastore.JwtTokenRepositoryImpl
import com.example.audioshopinventorymanagement.jwttokensdatastore.JwtTokenSerializer
import com.example.audioshopinventorymanagement.jwttokensdatastore.JwtTokens
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataStoreProvider {
    @Singleton
    @Provides
    fun provideDataStore(
        @ApplicationContext context: Context
    ): DataStore<JwtTokens> {
        return DataStoreFactory.create(
            serializer = JwtTokenSerializer(),
            produceFile = { context.dataStoreFile(BuildConfig.FILENAME) },
            scope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
            corruptionHandler = ReplaceFileCorruptionHandler(
                produceNewData = { JwtTokens(
                        accessToken = "",
                        refreshToken = ""
                    )
                }
            ),
        )
    }

    @Singleton
    @Provides
    fun providesJwtTokenRepository(
        dataStore: DataStore<JwtTokens>,
    ) : JwtTokenRepository {
        return JwtTokenRepositoryImpl(dataStore)
    }
}
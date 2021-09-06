package com.chohtet.android.codetest.spacex.di

import com.android.codetest.data.mapper.LaunchMapper
import com.android.codetest.data.mapper.MissionMapper
import com.android.codetest.data.services.ApiService
import com.apollographql.apollo.ApolloClient
import com.chohtet.android.codetest.spacex.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
class NetworkModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val client = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            client.addInterceptor(interceptor)
        }
        return client.build()

    }

    @Provides
    @Singleton
    fun provideApolloClient(okHttpClient: OkHttpClient): ApolloClient {
        return ApolloClient.builder()
            .serverUrl("https://api.spacex.land/graphql/")
            .okHttpClient(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(
        apolloClient: ApolloClient,
        launchMapper: LaunchMapper,
        missionMapper: MissionMapper
    ):ApiService = ApiService(apolloClient,launchMapper,missionMapper)

//    @Provides
//    @Singleton
//    fun provideApiService(apolloClient: ApolloClient): ApiService = ApiService(apolloClient)
}
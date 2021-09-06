package com.chohtet.android.codetest.spacex.di

import com.android.codetest.data.dataSource.RemoteDataSource
import com.android.codetest.data.dataSource.RemoteDataSourceImpl
import com.android.codetest.data.repository.LaunchRepositoryImpl
import com.android.codetest.domain.repositories.LaunchRepository
import com.android.codetest.domain.usecases.impl.GetLaunchListUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class LaunchModule {
//    @Binds
//    abstract fun launchRepository(launchRepositoryImpl: LaunchRepositoryImpl): LaunchRepository

    @Binds
    abstract fun  launchRepository(launchRepositoryImpl: LaunchRepositoryImpl):LaunchRepository

    @Binds
    abstract fun remoteDataSource(remoteDataSourceImpl: RemoteDataSourceImpl):RemoteDataSource
}
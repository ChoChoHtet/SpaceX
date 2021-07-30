package com.chohtet.android.codetest.spacex.di

import com.chohtet.android.codetest.spacex.repository.LaunchRepository
import com.chohtet.android.codetest.spacex.repository.LaunchRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class LaunchModule {
    @Binds
    abstract fun launchRepository(launchRepositoryImpl: LaunchRepositoryImpl): LaunchRepository
}
package com.chohtet.android.codetest.spacex.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.chohtet.android.codetest.spacex.viewmodel.LaunchViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(LaunchViewModel::class)
    abstract fun launchViewModel(launchViewModel: LaunchViewModel): ViewModel
}
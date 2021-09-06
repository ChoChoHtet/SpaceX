package com.chohtet.android.codetest.spacex.di

import android.content.Context
import com.chohtet.android.codetest.spacex.SpaceXApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        NetworkModule::class,
        ViewModelModule::class,
        ActivityModule::class,
        FragmentModule::class,
        LaunchModule::class,
        ModelMapperModule::class
    ]
)
interface AppComponent : AndroidInjector<SpaceXApp> {
    @Component.Factory
    interface Builder {
        fun create(@BindsInstance context: Context): AppComponent
    }

    override fun inject(instance: SpaceXApp)
}
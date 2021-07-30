package com.chohtet.android.codetest.spacex.di

import com.chohtet.android.codetest.spacex.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun mainActivity():MainActivity

}
package com.chohtet.android.codetest.spacex.di

import com.chohtet.android.codetest.spacex.ui.LaunchDetailFragment
import com.chohtet.android.codetest.spacex.ui.LaunchListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun launchListFragment():LaunchListFragment

    @ContributesAndroidInjector
    abstract fun launchDetailFragment():LaunchDetailFragment

}
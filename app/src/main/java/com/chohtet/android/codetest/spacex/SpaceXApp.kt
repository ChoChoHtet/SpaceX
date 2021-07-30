package com.chohtet.android.codetest.spacex

import com.chohtet.android.codetest.spacex.di.DaggerAppComponent
import dagger.android.support.DaggerApplication

class SpaceXApp:DaggerApplication() {
    override fun onCreate() {
        super.onCreate()
       applicationInjector().inject(this)
    }

    override fun applicationInjector() = DaggerAppComponent.factory().create(this)
}
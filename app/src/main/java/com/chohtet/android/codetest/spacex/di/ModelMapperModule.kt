package com.chohtet.android.codetest.spacex.di

import com.android.codetest.data.mapper.LaunchMapper
import com.android.codetest.data.mapper.MissionMapper
import dagger.Module
import dagger.Provides

/**
 * Module and dependency setting for ObjectMapper
 */
@Module
object ModelMapperModule {
	@Provides
	fun launchMapper(): LaunchMapper = LaunchMapper()

	@Provides
	fun missionMapper():MissionMapper = MissionMapper()
	

}
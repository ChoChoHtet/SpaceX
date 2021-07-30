package com.chohtet.android.codetest.spacex.repository

import com.chohtet.android.codetest.spacex.GetLaunchListQuery
import com.chohtet.android.codetest.spacex.MissionQuery
import io.reactivex.Observable
import io.reactivex.Single

interface LaunchRepository {
    fun queryLaunchList():Observable<List<GetLaunchListQuery.Launch?>?>
    fun queryMission(id:String):Observable<MissionQuery.Mission>
}
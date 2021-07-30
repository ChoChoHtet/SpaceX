package com.chohtet.android.codetest.spacex.repository

import com.apollographql.apollo.rx2.Rx2Apollo
import com.chohtet.android.codetest.spacex.GetLaunchListQuery
import com.chohtet.android.codetest.spacex.MissionQuery
import com.chohtet.android.codetest.spacex.network.ApiService
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class LaunchRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : LaunchRepository {
    override fun queryLaunchList(): Observable<List<GetLaunchListQuery.Launch?>?> {
        return apiService.getLaunchList().map { it.launches }
    }

    override fun queryMission(id: String): Observable<MissionQuery.Mission> {
        return apiService.getMission(id).map { it.mission }
    }
}
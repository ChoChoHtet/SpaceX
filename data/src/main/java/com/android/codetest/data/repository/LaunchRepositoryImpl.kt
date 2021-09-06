package com.android.codetest.data.repository

import com.android.codetest.data.dataSource.RemoteDataSource
import com.android.codetest.data.services.ApiService
import com.android.codetest.domain.model.Launch
import com.android.codetest.domain.model.Mission
import com.android.codetest.domain.repositories.LaunchRepository
import io.reactivex.Observable
import javax.inject.Inject

class LaunchRepositoryImpl @Inject constructor(
    private val dataSource: RemoteDataSource) : LaunchRepository {
    override fun queryLaunchList(): Observable<List<Launch>> {
        return dataSource.launchList()
    }

    override fun queryMission(id: String): Observable<Mission> {
        return dataSource.mission(id)
    }
}
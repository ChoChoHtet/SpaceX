package com.android.codetest.data.dataSource

import com.android.codetest.data.services.ApiService
import com.android.codetest.domain.model.Launch
import com.android.codetest.domain.model.Mission
import io.reactivex.Observable
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
private val apiService: ApiService
):RemoteDataSource {
    override fun launchList(): Observable<List<Launch>> {
        return apiService.getLaunchList()
    }

    override fun mission(id:String): Observable<Mission> {
        return apiService.getMission(id)
    }
}
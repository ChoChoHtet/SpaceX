package com.android.codetest.data.services

import com.android.codetest.data.mapper.LaunchMapper
import com.android.codetest.data.mapper.MissionMapper
import com.android.codetest.domain.model.Launch
import com.android.codetest.domain.model.Mission
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.rx2.Rx2Apollo
import com.chohtet.android.codetest.spacex.GetLaunchListQuery
import com.chohtet.android.codetest.spacex.MissionQuery
import io.reactivex.Observable
import javax.inject.Inject

class ApiService @Inject constructor(
    private val apolloClient: ApolloClient,
    private val launchMapper: LaunchMapper,
    private val missionMapper: MissionMapper
) {
    fun getLaunchList(): Observable<List<Launch>> {
        return Rx2Apollo.from(apolloClient.query(GetLaunchListQuery())).map {
            it.data?.launches?.map { launch ->
                launchMapper.map(launch!!)
            }
        }
    }

    fun getMission(id: String): Observable<Mission> {
        return Rx2Apollo.from(apolloClient.query(MissionQuery(id))).map {
            missionMapper.map(it.data?.mission!!)
        }
    }

}
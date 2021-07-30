package com.chohtet.android.codetest.spacex.network

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.rx2.Rx2Apollo
import com.chohtet.android.codetest.spacex.GetLaunchListQuery
import com.chohtet.android.codetest.spacex.MissionQuery
import io.reactivex.Observable
import javax.inject.Inject

class ApiService  @Inject constructor(private var apolloClient: ApolloClient){
    fun getLaunchList():Observable<GetLaunchListQuery.Data>{
        return Rx2Apollo.from(apolloClient.query(GetLaunchListQuery())).map {
            it.data
        }
    }
    fun getMission(missionId:String):Observable<MissionQuery.Data>{
        return Rx2Apollo.from(apolloClient.query(MissionQuery(missionId))).map {
            it.data
        }
    }
}
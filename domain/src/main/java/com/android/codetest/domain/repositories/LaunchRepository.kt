package com.android.codetest.domain.repositories

import com.android.codetest.domain.model.Launch
import com.android.codetest.domain.model.Mission
import io.reactivex.Observable

interface LaunchRepository {
    fun queryLaunchList(): Observable<List<Launch>>
    fun queryMission(id:String):Observable<Mission>
}
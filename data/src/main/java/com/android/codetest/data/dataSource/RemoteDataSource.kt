package com.android.codetest.data.dataSource

import com.android.codetest.domain.model.Launch
import com.android.codetest.domain.model.Mission
import io.reactivex.Observable

interface RemoteDataSource {
    fun launchList():Observable<List<Launch>>
    fun mission(id:String):Observable<Mission>
}
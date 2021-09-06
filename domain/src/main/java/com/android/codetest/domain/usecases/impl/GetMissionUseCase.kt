package com.android.codetest.domain.usecases.impl

import com.android.codetest.domain.model.Mission
import com.android.codetest.domain.repositories.LaunchRepository
import com.android.codetest.domain.usecases.ObservableUseCase
import io.reactivex.Observable
import javax.inject.Inject

class GetMissionUseCase @Inject constructor(private val launchRepository: LaunchRepository) {
    operator fun invoke(id: String): Observable<Mission> {
        return launchRepository.queryMission(id)
    }

}
package com.android.codetest.domain.usecases.impl

import com.android.codetest.domain.model.Launch
import com.android.codetest.domain.repositories.LaunchRepository
import com.android.codetest.domain.usecases.ObservableUseCase
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class GetLaunchListUseCase @Inject constructor(private val launchRepository: LaunchRepository) {
    operator fun invoke(): Observable<List<Launch>> {
        return launchRepository.queryLaunchList()
    }
}
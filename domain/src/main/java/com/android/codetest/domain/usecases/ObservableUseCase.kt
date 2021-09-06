package com.android.codetest.domain.usecases

import io.reactivex.Observable
import io.reactivex.Single

interface ObservableUseCase<R> {
    fun execute():Observable<R>
}
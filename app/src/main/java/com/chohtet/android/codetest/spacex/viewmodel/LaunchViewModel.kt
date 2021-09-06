package com.chohtet.android.codetest.spacex.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.codetest.domain.model.Launch
import com.android.codetest.domain.model.Mission
import com.android.codetest.domain.usecases.impl.GetLaunchListUseCase
import com.android.codetest.domain.usecases.impl.GetMissionUseCase
import com.chohtet.android.codetest.spacex.utils.ViewState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LaunchViewModel @Inject constructor(
    private val launchListUseCase: GetLaunchListUseCase,
    private val missionUseCase: GetMissionUseCase
) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    private val _launchList by lazy { MutableLiveData<ViewState<List<Launch?>?>>() }
    private val _mission by lazy { MutableLiveData<ViewState<Mission>>() }
    val launchesList: LiveData<ViewState<List<Launch?>?>>
        get() = _launchList
    val mission: LiveData<ViewState<Mission>>
        get() = _mission

    fun getLaunchList() {
        addDisposable(
            launchListUseCase.invoke()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    _launchList.value = ViewState.Loading()
                }
                .subscribe(
                    { result ->
                        _launchList.value = ViewState.Success(result)
                    }, { error ->
                        //_launchList.value = ViewState.Error(error.message)
                    }
                )
        )
    }

    fun getMission(id: String) {
        addDisposable(
            missionUseCase.invoke(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    _mission.value = ViewState.Loading()
                }
                .subscribe(
                    { result ->
                        _mission.value = ViewState.Success(result)
                    }, { error ->
                        //_mission.value = ViewState.Error(error.message)
                    }
                )
        )
    }

    private fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

}
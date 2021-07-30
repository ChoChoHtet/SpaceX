package com.chohtet.android.codetest.spacex.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chohtet.android.codetest.spacex.GetLaunchListQuery
import com.chohtet.android.codetest.spacex.MissionQuery
import com.chohtet.android.codetest.spacex.repository.LaunchRepository
import com.chohtet.android.codetest.spacex.utils.ViewState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LaunchViewModel @Inject constructor(
    private val launchRepository: LaunchRepository
) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    private val _launchList by lazy { MutableLiveData<ViewState<List<GetLaunchListQuery.Launch?>?>>() }
    private val _mission by lazy { MutableLiveData<ViewState<MissionQuery.Mission>>() }
    val launchesList: LiveData<ViewState<List<GetLaunchListQuery.Launch?>?>>
        get() = _launchList
    val mission: LiveData<ViewState<MissionQuery.Mission>>
        get() = _mission

    fun getLaunchList() {
        addDisposable(
            launchRepository.queryLaunchList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    _launchList.value = ViewState.Loading()
                }
                .subscribe(
                    { result ->
                        _launchList.value = ViewState.Success(result)
                    }, { error ->
                        _launchList.value = ViewState.Error(error.message)
                    }
                )
        )
    }

    fun getMission(id: String) {
        addDisposable(
            launchRepository.queryMission(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    _mission.value = ViewState.Loading()
                }
                .subscribe(
                    { result ->
                        _mission.value = ViewState.Success(result)
                    }, { error ->
                        _mission.value = ViewState.Error(error.message)
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
package com.mvvm.mvvmdagger.ui.home

import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableArrayList
import android.databinding.ObservableList
import android.util.Log
import android.view.View
import com.mvvm.mvvmdagger.data.db.AppDbHelper
import com.mvvm.mvvmdagger.data.model.db.User
import com.mvvm.mvvmdagger.data.remote.ApiInterface
import com.mvvm.mvvmdagger.ui.base.BaseViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomeViewModel(
    private val mApiInterface: ApiInterface,
    private val mAppDbHelper: AppDbHelper
) : BaseViewModel() {

    val mUsersList: ObservableList<User> = ObservableArrayList<User>()

    var mUserLiveList: MutableLiveData<List<User>> = MutableLiveData()
    val mLoadingVisibility: MutableLiveData<Int> = MutableLiveData()

    /**
     * Get users list.
     */
    fun getUsers() {
        getCompositeDisposable().add(
            getUsersList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { mLoadingVisibility.value = View.VISIBLE }
                .doOnTerminate { mLoadingVisibility.value = View.GONE }
                .subscribe({ response ->
                    if (response == null) return@subscribe
                    else {
                        mUserLiveList.value = response
                    }

                }, { t ->
                    Log.d("", t.toString())

                })
        )
    }

    private fun getUsersList(): Observable<List<User>> {
        val observableFromApi = getUsersFromApi()
        val observableFromDb = getUsersFromDb()
        return Observable.concatArrayEager(observableFromApi, observableFromDb)
    }

    private fun getUsersFromApi(): Observable<List<User>> {
        return mApiInterface.getUsers().doOnNext {
            for (item in it) {
                mAppDbHelper.insertUser(item)
            }
        }
    }


    private fun getUsersFromDb(): Observable<List<User>> {
        return mAppDbHelper.getAllUsers().toObservable()
    }

    fun addUserItemsToList(users: List<User>) {
        mUsersList.clear()
        mUsersList.addAll(users)
    }
}

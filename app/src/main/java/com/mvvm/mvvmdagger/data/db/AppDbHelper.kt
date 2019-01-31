package com.mvvm.mvvmdagger.data.db

import android.annotation.SuppressLint
import com.mvvm.mvvmdagger.data.model.db.User
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppDbHelper @Inject
constructor(private val mAppDatabase: AppDatabase) {

    @SuppressLint("CheckResult")
    fun insertUser(user: User) {
        Observable.fromCallable<Any> {
            mAppDatabase.userDao().insert(user)
        }.subscribeOn(Schedulers.newThread()).observeOn(
            AndroidSchedulers.mainThread()
        ).subscribe { }
    }

    @SuppressLint("CheckResult")
    fun insertAll(user: List<User>) {
        Observable.fromCallable<Any> {
            mAppDatabase.userDao().insertAll(user)
        }.subscribeOn(Schedulers.newThread()).observeOn(
            AndroidSchedulers.mainThread()
        ).subscribe { }
    }

    fun getAllUsers(): Single<List<User>> {
        return mAppDatabase.userDao().getAllUsers()
    }
}

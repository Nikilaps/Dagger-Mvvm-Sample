package com.mvvm.mvvmdagger.ui.base

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel(
    private val mCompositeDisposable: CompositeDisposable = CompositeDisposable(),
    private val mIsLoading: ObservableBoolean = ObservableBoolean(false)
) : ViewModel() {



    override fun onCleared() {
        mCompositeDisposable.dispose()
        super.onCleared()
    }

    fun getCompositeDisposable(): CompositeDisposable {
        return mCompositeDisposable
    }

    fun getIsLoading(): ObservableBoolean {
        return mIsLoading
    }

    fun setIsLoading(isLoading: Boolean) {
        mIsLoading.set(isLoading)
    }
}
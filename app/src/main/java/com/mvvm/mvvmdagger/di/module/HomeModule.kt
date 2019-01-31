package com.mvvm.mvvmdagger.di.module

import com.mvvm.mvvmdagger.data.db.AppDbHelper
import com.mvvm.mvvmdagger.data.remote.ApiInterface
import com.mvvm.mvvmdagger.ui.home.HomeViewModel
import dagger.Module
import dagger.Provides

@Module
class HomeModule {

    @Provides
    fun getHomeViewModel(mApiInterface: ApiInterface, mAppDbHelper: AppDbHelper): HomeViewModel {
        return HomeViewModel(mApiInterface, mAppDbHelper)
    }
}
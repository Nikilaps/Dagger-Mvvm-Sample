package com.mvvm.mvvmdagger.di.module

import com.mvvm.mvvmdagger.data.db.AppDbHelper
import com.mvvm.mvvmdagger.data.remote.ApiInterface
import com.mvvm.mvvmdagger.ui.login.LoginViewModel
import dagger.Module
import dagger.Provides

@Module
class LoginModule {

    @Provides
    fun getLoginViewModel(mApiInterface: ApiInterface, mAppDbHelper: AppDbHelper): LoginViewModel {
        return LoginViewModel(mApiInterface, mAppDbHelper)
    }
}
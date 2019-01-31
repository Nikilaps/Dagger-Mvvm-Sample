package com.mvvm.mvvmdagger.di.builder

import com.mvvm.mvvmdagger.di.module.HomeModule
import com.mvvm.mvvmdagger.di.module.LoginModule
import com.mvvm.mvvmdagger.ui.home.HomeActivity
import com.mvvm.mvvmdagger.ui.login.LoginActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [LoginModule::class])
    abstract fun buildLoginActivity(): LoginActivity

    @ContributesAndroidInjector(modules = [HomeModule::class])
    abstract fun buildHomeActivity(): HomeActivity
}
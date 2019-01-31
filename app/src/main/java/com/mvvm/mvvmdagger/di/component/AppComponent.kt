package com.mvvm.mvvmdagger.di.component

import android.app.Application
import com.mvvm.mvvmdagger.MvvmApp
import com.mvvm.mvvmdagger.di.builder.ActivityBuilder
import com.mvvm.mvvmdagger.di.module.AppModule
import com.mvvm.mvvmdagger.di.module.NetWorkModule
import com.mvvm.mvvmdagger.data.remote.ApiInterface
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModule::class, ActivityBuilder::class, NetWorkModule::class])
interface AppComponent {

    fun inject(app: MvvmApp)

    fun getApiInterface(): ApiInterface

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}
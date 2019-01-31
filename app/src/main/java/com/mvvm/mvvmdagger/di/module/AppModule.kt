package com.mvvm.mvvmdagger.di.module

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import com.mvvm.mvvmdagger.data.db.AppDatabase
import com.mvvm.mvvmdagger.data.db.dao.UserDao
import com.mvvm.mvvmdagger.di.anno.DbName
import com.mvvm.mvvmdagger.utils.DB_NAME
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@DbName dbName: String, context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, dbName)
            .fallbackToDestructiveMigration()
//            .allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun provideUserDao(database: AppDatabase): UserDao = database.userDao()

    @Provides
    @DbName
    fun provideDatabaseName(): String {
        return DB_NAME
    }

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application
    }

}
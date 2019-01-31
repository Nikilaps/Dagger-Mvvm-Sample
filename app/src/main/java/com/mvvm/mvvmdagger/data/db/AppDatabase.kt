package com.mvvm.mvvmdagger.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.mvvm.mvvmdagger.data.db.dao.UserDao
import com.mvvm.mvvmdagger.data.model.db.User

@Database(entities = [User::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
}